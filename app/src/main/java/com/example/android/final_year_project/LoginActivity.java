package com.example.android.final_year_project;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import butterknife.ButterKnife;
import butterknife.InjectView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import prefs.UserInfo;
import prefs.UserSession;

import static com.example.android.final_year_project.MemberPanel.CATEGORY;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 1;
    private UserSession session;
    private UserInfo userInfo;
    private ProgressDialog progressDialog;
    TextView t;


    @InjectView(R.id.input_email)
    EditText _emailText;
    @InjectView(R.id.input_password)
    EditText _passwordText;
    @InjectView(R.id.btn_login)
    Button _loginButton;
    @InjectView(R.id.link_signup)
    TextView _signupLink;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String newstring;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
               newstring= null;
            } else {
                newstring = extras.getString(CATEGORY);
            }
        } else {
            newstring= (String) savedInstanceState.getSerializable(CATEGORY);
        }
        final String category = newstring;
        session         = new UserSession(this);
        userInfo        = new UserInfo(this);
        progressDialog  = new ProgressDialog(this, R.style.EcadroidOrange);

        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        t = (TextView) findViewById(R.id.tv1);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/cac_champagne.ttf");
        t.setTypeface(myCustomFont);

        if(session.isUserLoggedin()){
            startActivity(new Intent(this, homescreen.class));
            finish();
        }

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login(category);
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                intent.putExtra(CATEGORY, category);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login(final String category) {
        Log.d(TAG, "Login");
        String tag_string_req = "req_login";
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        if (!validate()) {
            if(progressDialog.isShowing())
                progressDialog.dismiss();
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Utils.LOGIN_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (error == false) {
                        // Now store the user in SQLite
                        JSONObject user = jObj.getJSONObject("user");
                        String email = user.getString("mail");
                        String category = user.getString("category");

                        // Inserting row in users table
                        userInfo.setEmail(email);
                        userInfo.setCategory(category);
                        session.setLoggedin(true);
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                        onLoginSuccess();
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                        toast(errorMsg);
                        onLoginFailed();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                    toast("Json error: " + e.getMessage());
                    onLoginFailed();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                toast("Volley Error" + error.getMessage());
                if(progressDialog.isShowing())
                    progressDialog.dismiss();
                onLoginFailed();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();
                params.put("mail", email);
                params.put("password", password);
                params.put("category", category);

                return params;
            }
        };

        // Adding request to request queue
        LoginController.getInstance().addToRequestQueue(strReq, tag_string_req);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        //if (progressDialog.isShowing()) progressDialog.dismiss();
                    }
                }, 3000);
    }

    private void toast(String x){
        Toast.makeText(this, x, Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                session.setLoggedin(true);
                onLoginSuccess();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        startActivity(new Intent(LoginActivity.this, homescreen.class));
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }


}


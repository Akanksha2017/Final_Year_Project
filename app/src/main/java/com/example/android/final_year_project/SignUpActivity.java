package com.example.android.final_year_project;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import prefs.UserInfo;
import prefs.UserSession;

import static com.example.android.final_year_project.MemberPanel.CATEGORY;
import static java.lang.Integer.parseInt;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    TextView t;
    @InjectView(R.id.input_name)
    EditText _nameText;
    @InjectView(R.id.input_dpt)
    EditText _dptText;
    @InjectView(R.id.input_year)
    EditText _yearText;
    @InjectView(R.id.input_email)
    EditText _emailText;
    @InjectView(R.id.input_password)
    EditText _passwordText;
    @InjectView(R.id.btn_signup)
    Button _signupButton;
    @InjectView(R.id.link_login)
    TextView _loginLink;
    private UserSession session;
    private UserInfo userInfo;
    private ProgressDialog progressDialog;

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
        setContentView(R.layout.activity_sign_up);
        t = (TextView) findViewById(R.id.tv1);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/cac_champagne.ttf");
        t.setTypeface(myCustomFont);
        ButterKnife.inject(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup(category);
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup(final String category) {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this);
        String tag_string_req = "req_signup";
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        final String name = _nameText.getText().toString();
        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();
        final String department = _dptText.getText().toString();
        final String year = _yearText.getText().toString();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Utils.REGISTER_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // Now store the user in SQLite
                        JSONObject user = jObj.getJSONObject("user");

                        if (progressDialog!=null && progressDialog.isShowing()) progressDialog.dismiss();
                        toast(jObj.getString("message"));
                        session.setLoggedin(true);
                        onSignupSuccess(user);
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        if (progressDialog.isShowing()) progressDialog.dismiss();
                        toast(errorMsg);
                        onSignupFailed();;
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    //if (progressDialog.isShowing()) progressDialog.dismiss();
                    toast("Json error: " + e.getMessage());
                    onSignupFailed();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.toString());
                toast("volley error" + error.toString());
                //if (progressDialog.isShowing()) progressDialog.dismiss();
                onSignupFailed();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("mail", email);
                params.put("password", password);
                params.put("year", year);
                params.put("deptt", department);
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
                        if (progressDialog!=null && progressDialog.isShowing()) progressDialog.dismiss();
                        //onSignupSuccess(user);
                        // onLoginFailed();

                    }
                }, 3000);
    }

    private void toast(String x) {
        Toast.makeText(this, x, Toast.LENGTH_SHORT).show();
    }




    public void onSignupSuccess(JSONObject user) {
        _signupButton.setEnabled(true);
        Intent returnIntent = getIntent();
        try {
            returnIntent.putExtra("mail", user.getString("category"));
            returnIntent.putExtra("category", user.getString("category"));

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        setResult(RESULT_OK,returnIntent);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String year =  _yearText.getText().toString();
        int year_num = parseInt(year);
        String dept = _dptText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

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

        if (year.isEmpty() || year_num > 4 || year_num < 1) {
            _yearText.setError("years 1 to 4");
            valid = false;
        } else {
            _yearText.setError(null);
        }

        if (dept.isEmpty()) {
            _dptText.setError("Please enter your department");
            valid = false;
        } else {
            _dptText.setError(null);
        }
        return valid;
    }
}

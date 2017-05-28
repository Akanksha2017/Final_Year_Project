package com.example.android.final_year_project;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import prefs.UserInfo;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    //final android.support.v7.widget.Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    private static final String TAG = "ProfileFragment";
    //rivate UserSession session;
    private UserInfo userInfo;
    public ProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.profile);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState){
        Context context = getActivity();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.profile);
        final TextView name = (TextView) getView().findViewById(R.id.profile_name);
        final TextView id = (TextView) getView().findViewById(R.id.profile_id);
        final TextView year = (TextView) getView().findViewById(R.id.profile_year);
        final TextView dept = (TextView) getView().findViewById(R.id.profile_dept);
        final TextView mail = (TextView) getView().findViewById(R.id.profile_mail);
        final TextView post = (TextView) getView().findViewById(R.id.profile_pos);
        //session         = new UserSession(context);
        userInfo        = new UserInfo(context);

        final String email = userInfo.getKeyEmail();
        mail.setText(email);
        final String category = userInfo.getKeyCategory();
        String tag_string_req = "req_login";
        if(userInfo.getKeyId()!=""){
            name.setText(userInfo.getKeyName());
            id.setText(userInfo.getKeyId());
            year.setText(userInfo.getKeyYear());
            dept.setText(userInfo.getKeyDept());
            post.setText(userInfo.getKeyPost());
        }
        else{
        StringRequest strReq = new StringRequest(Request.Method.POST,
                Utils.PROFILE_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Server Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (error == false) {
                        // Now store the user in SQLite
                        //String mail1 = jObj.getString("mail");
                        String id1 = jObj.getString("id");
                        String year1= jObj.getString("year");
                        String name1= jObj.getString("name");
                        String dept1= jObj.getString("deptt");
                        String log1 = jObj.getString("log");
                        String post1 = jObj.getString("position");

                        //mail.setText(mail1);
                        id.setText(id1);
                        name.setText(name1);
                        id.setText(id1);
                        year.setText(year1);
                        dept.setText(dept1);
                        post.setText(post1);

                        // Inserting row in users table
                        //userInfo.setEmail(mail1);
                        userInfo.setYear(year1);
                        userInfo.setDept(dept1);
                        userInfo.setName(name1);
                        userInfo.setLog(log1);
                        userInfo.setId(id1);
                        userInfo.setPost(post1);

                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        toast(errorMsg);
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    toast("Json error: " + e.getMessage());
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.toString());
                toast("Volley Error" + error.toString());
            }
        }){

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();
                params.put("mail", email);
                params.put("category", category);

                return params;
            }
        };

        // Adding request to request queue
        LoginController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }}
    private void toast(String x){
        Toast.makeText(getActivity(), x, Toast.LENGTH_SHORT).show();
    }

}

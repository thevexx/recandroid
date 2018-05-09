package com.example.admin.tutoserevices;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();


    private static String KEY_UID = "uid";
    private static String KEY_NAME = "name";
    private static String KEY_EMAIL = "email";
    private static String KEY_CREATED_AT = "created_at";

    private Button btnLogin, btnLinkToRegister, btnForgotPass;
    private EditText inputEmail, inputPassword;
    private ProgressDialog pDialog;

    private SessionManager session;
    private DatabaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        inputEmail = (EditText) findViewById(R.id.lEditEmail);
        inputPassword = (EditText) findViewById(R.id.lEditPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        btnForgotPass = (Button) findViewById(R.id.btnForgotPassword);

        inputEmail.setText("nour@gmail.com");
        inputPassword.setText("nour");

        // Progress dialog
//        pDialog = new ProgressDialog(this);
//        pDialog.setCancelable(false);
//
//
//        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
//        if (session.isLoggedIn()) {
//            // User is already logged in. Take him to main activity
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//
//        }

        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                // Check for empty data in the form
                if (!email.isEmpty() && !password.isEmpty()) {

                    Log.v("MY_TAG", "login is valid");

                    // login user
                    checkLogin(email, password);

//                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(intent);
                } else {

                    Log.v("MY_TAG", "login not valid");
                    Toast.makeText(getApplicationContext(), "Email is not valid!", Toast.LENGTH_SHORT).show();

                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(), "Please enter the credentials!", Toast.LENGTH_LONG).show();
                }
            }

        });

        // Link to Register Screen
        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), inscription.class);
                startActivity(i);
                finish();
            }
        });

    }


    void toast(){
        Toast.makeText(getApplicationContext(), "Email is not valid!", Toast.LENGTH_LONG).show();
    }

    /**
     * function to verify login details in mysql db
     */
    private void checkLogin(final String username, final String pass) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            String URL = "http://10.0.2.2/reclam/auth.php?auth=login";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/x-www-form-urlencoded");
                    return params;
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", username);
                    params.put("password", pass);
                    return params;
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        for (int i = 0; i < response.data.length; i++) {
                            char c = (char) response.data[i];
                            responseString += c;
                        }
                    }
                    try {
                        JSONObject repJson = new JSONObject(responseString);
                        String msg = repJson.getString("msg");
                        Log.v("MY_LOG", repJson.toString());
                        if (msg.equals("true")){
                            String id_c = repJson.getJSONObject("data").getString("id_c");
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("idc", id_c);
                            editor.apply();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
//                                finish();
                        }else{
                            toast();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        } catch (Exception e) {
            Log.v("MY_LOG", "ok31");

            e.printStackTrace();
            Log.v("MY_LOG", "ok3");

        } finally {

        }
    }

}
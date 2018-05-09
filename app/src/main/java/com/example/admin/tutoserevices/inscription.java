package com.example.admin.tutoserevices;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class inscription extends AppCompatActivity {
    private static final String TAG = inscription.class.getSimpleName();
    private Button btnRegister, btnLinkToLogin, valider, Annuller;
    private EditText emailEd;
    private EditText passwordEd;
    private EditText nomEd;
    private EditText prenomEd;
    private EditText telEd;
    private EditText cinEd;
    private ProgressDialog pDialog;

    public inscription() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        nomEd = (EditText) findViewById(R.id.Nomins);
        prenomEd = (EditText) findViewById(R.id.prénomins);
        emailEd = (EditText) findViewById(R.id.Emailins);
        telEd = (EditText) findViewById(R.id.Tel);
        cinEd = (EditText) findViewById(R.id.cin);
        passwordEd = (EditText) findViewById(R.id.textPasswordins);
        final Button valider = (Button) findViewById(R.id.Validerins);
        final Button Annuller = (Button) findViewById(R.id.Annulerins);
        final Button btnRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        final Button btnLinkToLogin = (Button) findViewById(R.id.btnLogin);
        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Hide Keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        init();
    }

    private void init() {
        // Login button Click Event
        btnRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                // Hide Keyboard
                Functions.hideSoftKeyboard(inscription.this);

                String namee = nomEd.getText().toString().trim();
                String emails = emailEd.getText().toString().trim();
                String passwordE = passwordEd.getText().toString().trim();
                String telf = telEd.getText().toString().trim();
                String cins = telEd.getText().toString().trim();
                String prenomE = prenomEd.getText().toString().trim();


                // Check for empty data in the form
                if (!namee.isEmpty() && !emails.isEmpty() && !passwordE.isEmpty() && !telf.isEmpty() && !cins.isEmpty() && !prenomE.isEmpty()) {
                    if (Functions.isValidEmailAddress(emails)) {
                        registerUser(namee, prenomE, emails, passwordE, telf, cins);
                    } else {
                        Toast.makeText(getApplicationContext(), "Email is not valid!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter your details!", Toast.LENGTH_LONG).show();
                }
            }

        });

        // Link to Register Screen
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent i = new Intent(inscription.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        valider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Functions.hideSoftKeyboard(inscription.this);

                Intent i = new Intent(inscription.this, LoginActivity.class);
                startActivity(i);
                finish();
                String namee = nomEd.getText().toString().trim();
                String emails = emailEd.getText().toString().trim();
                String passwordE = passwordEd.getText().toString().trim();
                String telf = telEd.getText().toString().trim();
                String cins = telEd.getText().toString().trim();
                String prenomE = prenomEd.getText().toString().trim();


            }

        });

    }

    private void registerUser(final String name, final String email, final String password, final String prenom, final String cin, final String tel) {
        // Tag used to cancel the request
        final String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        //showDialog();
        final HashMap<String, String> params = new HashMap<String, String>();




                try {
                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("name", name);
                    jsonBody.put("prenoms", prenom);
                    jsonBody.put("email", email);
                    jsonBody.put("password", password);
                    jsonBody.put("tel", tel);
                    jsonBody.put("Cin", cin);

                    final String mRequestBody = jsonBody.toString();

                    StringRequest strReq = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            Log.i("VOLLEY", response);
                            Log.d("**********", "FETCHING IN VOLLEY REQ" + response.toString());


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
                        public byte[] getBody() throws AuthFailureError {
                            try {
                                return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                            } catch (UnsupportedEncodingException uee) {
                                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                                return null;
                            }
                        }


                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String, String> headers = new HashMap<String, String>();
                            headers.put("Content-Type", "multipart/form-data");
                            return headers;
                        }

                        @Override
                        protected Response<String> parseNetworkResponse(NetworkResponse response) {
                            String responseString = "";
                            if (response != null) {
                                responseString = String.valueOf(response.statusCode);
                                // can get more details such as response.headers
                            }
                            return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                        }
                    };

                    MyApplication.getInstance().addToRequestQueue(strReq, tag_string_req);
                    // getRequestOtpPage().addToRequestQueue(stringRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                    JSONObject jObj = new JSONObject(response);
//                    boolean error = jObj.getBoolean("error");
//                    if (!error) {
//                        Functions logout = new Functions();
//                        logout.logoutUser(getApplicationContext());
//
//                        Bundle b = new Bundle();
//                        b.putString("email", email);
//                        Intent i = new Intent(inscription.this, EmailVerify.class);
//                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        i.putExtras(b);
//                        startActivity(i);
//                        pDialog.dismiss();
//                        finish();
//
//                    } else {
//                        // Error occurred in registration. Get the error
//                        // message
//                        String errorMsg = jObj.getString("message");
//                        Toast.makeText(getApplicationContext(),errorMsg, Toast.LENGTH_LONG).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, "Registration Error: " + error.getMessage());
//                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//                hideDialog();
//            }
//        }) {

//            @Override
//            protected Map<String, String> getParams() {
//                // Posting params to register url
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("name", name);
//                params.put("prenoms",prenom);
//                params.put("email", email);
//                params.put("password", password);
//                params.put("tel",tel);
//                params.put("Cin",cin);
//
//
//                return params;
//            }
//
//        };

                // Adding request to request queue
//        MyApplication.getInstance().addToRequestQueue(strReq, tag_string_req);
//    }


                // private void hideDialog {
                if (pDialog.isShowing())
                    pDialog.dismiss();
                //}
            }


            // private void showDialog() {
            //if(!pDialog.isShowing())
            // pDialog.show();
            //}
            // }
        }


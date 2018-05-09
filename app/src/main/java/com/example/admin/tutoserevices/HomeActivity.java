package com.example.admin.tutoserevices;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    String msg ="oncreate";
    // Log.e("Test message : ", msg.toString());


    public void Entrer(View view)
    {
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    public void Fermer(View view)
    {
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
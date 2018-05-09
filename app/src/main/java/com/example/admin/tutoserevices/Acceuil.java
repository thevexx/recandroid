package com.example.admin.tutoserevices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Acceuil extends AppCompatActivity {
    CardView mycard ;
    Intent i ;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);


            ll = (LinearLayout) findViewById(R.id.ll);
            mycard = (CardView) findViewById(R.id.bankcardId);
            i = new Intent(this,LoginActivity.class);
            mycard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(i);
                }
            });
    }
}
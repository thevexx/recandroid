package com.example.admin.tutoserevices;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class DetailleRec extends AppCompatActivity {

    private Button fermer;
//String id;

    private TextView tvTitre, tvTheme, tvEtat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaille_rec);
//        this.getIntent().getStringExtra("id");
        tvEtat = (TextView)findViewById(R.id.TvEtat);
        tvTitre = (TextView) findViewById(R.id.TvTitre);
        tvTheme = (TextView) findViewById(R.id.TvTheme);

        Bundle extras = getIntent().getExtras();
        String id;
        String theme;
        String libelle;
        String etat;

        if (extras != null) {
            id = extras.getString("id");
            libelle = extras.getString("libelle");
            theme = extras.getString("theme");
            etat = extras.getString("etat");
            // and get whatever type user account id is
            Log.v("LOG", "_________________________" + id + "__" + libelle + "__" + theme + "__" + etat);
            tvEtat.setText(etat);
            tvTitre.setText(libelle);
            tvTheme.setText(theme);
        }


        fermer = (Button) findViewById(R.id.button4);
        fermer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }


}

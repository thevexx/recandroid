package com.example.admin.tutoserevices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView list;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Intent intent = getIntent();

         //String id = intent.getStringExtra("id_c");
       // String name = intent.getStringExtra("nom_c");
        //  Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();
         // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       //  setSupportActionBar(toolbar);

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */

        // TextView welcome =(TextView)findViewById(R.id.Welcome);
      /*  if(getIntent().getExtras().getString("Email")!= null){
            welcome.setText(getIntent().getExtras().getString("Email"));
        }
        else
        {*/
        //  welcome.setText("no");
        //   }

        Button bt_listRec = (Button) findViewById(R.id.inscritreb);
        Button bt_AjouterRec = (Button) findViewById(R.id.intentsb);
        Button bt_notification = (Button) findViewById(R.id.connecterb);
        Button bt_Fermer = (Button) findViewById(R.id.Fermerb);

        ListView lv = (ListView) findViewById(android.R.id.list);

        bt_listRec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intents = new Intent(MainActivity.this, ListeElementActivity.class);
                startActivity(intents);
            }
        });

        bt_AjouterRec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AjouterReclamation.class);
                startActivity(intent);
            }
        });

        bt_notification.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Notification.class);
                startActivity(intent);
            }
        });

        bt_Fermer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                session.setLogin(false);
                Intent intenst = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intenst);
            }
        });


    }

    @Override
   public void onStop() {

        session = new SessionManager(getApplicationContext());
        super.onStop();
        session.setLogin(false);


    }
//    public void onResume() {
//        super.onResume();
//        session = new SessionManager(getApplicationContext());
//        if (!session.isLoggedIn()) {
//            Intent intentss = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(intentss);
//        }
//
//    }
}






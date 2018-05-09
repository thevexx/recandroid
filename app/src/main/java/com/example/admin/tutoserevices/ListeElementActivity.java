package com.example.admin.tutoserevices;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ParseException;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.admin.tutoserevices.Functions.GETLIST_URL;

public class ListeElementActivity extends AppCompatActivity {
    private SessionManager session;
    // private static final String  url = "http://192.168.143.52/reclamamtionprojectdev/app/serviceslogin.php?op=getliste";
    // private static final String  url = "http://192.168.1.27/reclamamtionprojectdev/app/serviceslogin.php?op=getliste";
    private static final String TAG = ListeElementActivity.class.getSimpleName();
    private List<reclamation> recList = new ArrayList<reclamation>();
    // private ListView listView;
    TextView libelle ,etat,image,titre ;
    private ListView maListView;
    private ProgressDialog pDialog;
    private CustomListAdapter adapter;
    private String id_c;
//    Bundle b = getIntent().getExtras();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_element);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        id_c = preferences.getString("idc", "");
        ListView maListView = (ListView) findViewById(R.id.Listeelement);


        getReclamations();
        //Array du Strings
      /*  String[] Jours = {"lundi", "Mardi", "Mercredi", "Jeudi", "Mardi", "Mardi", "Mercredi", "Jeudi", "Mardi", "Mardi", "Mercredi", "Jeudi", "Mardi", "Mardi"};

        //Adapter qui utilise l'élement par default simple_liste_item1_1
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                android.R.id.text1,Jours);

        //Affecter à la liste view l'adaptateur déja crée
        maListView.setAdapter(adapter);*/

        //Création de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> list_des_pays = new ArrayList<HashMap<String, String>>();


//optimisation du code








    String[] libelle = {"reclamation 1", "rec2", "rec3"};
        String[] etat = {"Terminer", "en cours", "Terminer"};
        int[] icone = {R.drawable.iamgerec1, R.drawable.iamgerec1, R.drawable.iamgerec1};
//On déclare la HashMap qui contiendra les informations pour un item
//Création d'une HashMap pour insérer les informations du premier item de notre listView
// HashMap<String, String> map = new HashMap<String, String>();
//on insère un élément titre que l'on récupérera dans le textView titre créé dans le fichier affichageitem.xml
        for (int i = 0; i < libelle.length; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("libelle", libelle[i]);
            map.put("etat", etat[i]);
            map.put("icone", String.valueOf(icone));
            list_des_pays.add(map);
        }


        maListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)

            {
                Intent intent = new Intent(ListeElementActivity.this, DetailleRec.class);
                startActivity(intent);
                Toast.makeText(ListeElementActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

//Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (list_des_pays dans la vue affichageitem
        SimpleAdapter adapter = new SimpleAdapter(this.getBaseContext(), list_des_pays, R.layout.libelle,
                new String[]{"libelle", "etat", "icone",}, new int[]{R.id.libelle, R.id.Etat, R.id.icone});
        maListView.setAdapter(adapter);
    }
    private void getReclamations() {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(ListeElementActivity.this);
            String URL = "http://10.0.2.2/reclam/reclamation.php?id="+id_c;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray arr = new JSONArray(response);
                        for(int i=0 ;i<arr.length();i++) {
//                            reclamation rec = new reclamation();
//                            rec.setLibelle_r(arr.getString(Integer.parseInt("libelle")));
//                            rec.setEtat_r(arr.getString(Integer.parseInt("etat")));
//                            rec.setImage_r(arr.getString(Integer.parseInt("icone")));

//                            recList.add(rec);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Log.i("MY_LOG", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            });
            requestQueue.add(stringRequest);
        } catch (Exception e) {
            Log.v("MY_LOG", "ok31");

            e.printStackTrace();
            Log.v("MY_LOG", "ok3");

        } finally {

        }
    }

}

    //@Override
//   public void onStop() {

    //      session = new SessionManager(getApplicationContext());
    //   super.onStop();
    //session.setLogin(false);
    //}


    //public void onResume() {
    //  super.onResume();
    //  session = new SessionManager(getApplicationContext());
    //if (!session.isLoggedIn()) {
    //  Intent intentss = new Intent(ListeElementActivity.this,LoginActivity.class);
    //startActivity(intentss);
    //}
    //}


package com.example.admin.tutoserevices;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListeElementActivity extends AppCompatActivity {
    // private static final String  url = "http://192.168.143.52/reclamamtionprojectdev/app/serviceslogin.php?op=getliste";
    // private static final String  url = "http://192.168.1.27/reclamamtionprojectdev/app/serviceslogin.php?op=getliste";
    private static final String TAG = ListeElementActivity.class.getSimpleName();
    // private ListView listView;
    TextView libelle, etat, image, titre;
    private SessionManager session;
    private List<reclamation> recList = new ArrayList<reclamation>();
    private ProgressDialog pDialog;
    private String id_c;
    private SimpleAdapter adapter;
    private ListView maListView;
    private ArrayList<HashMap<String, String>> list_des_pays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_element);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        id_c = preferences.getString("idc", "");
        maListView = (ListView) findViewById(R.id.Listeelement);

        getReclamations();

        list_des_pays = new ArrayList<HashMap<String, String>>();

        int[] icone = {R.drawable.iamgerec1, R.drawable.iamgerec1, R.drawable.iamgerec1};
//On déclare la HashMap qui contiendra les informations pour un item
//Création d'une HashMap pour insérer les informations du premier item de notre listView
//on insère un élément titre que l'on récupérera dans le textView titre créé dans le fichier affichageitem.xml
        maListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)

            {

                Intent intent = new Intent(ListeElementActivity.this, DetailleRec.class);
                intent.putExtra("id",list_des_pays.get(position).get("id"));
                intent.putExtra("libelle",list_des_pays.get(position).get("libelle"));
                intent.putExtra("theme",list_des_pays.get(position).get("theme"));
                intent.putExtra("etat",list_des_pays.get(position).get("etat"));
                startActivity(intent);

                Toast.makeText(ListeElementActivity.this, "" + list_des_pays.get(position).get("id"), Toast.LENGTH_SHORT).show();
            }
        });
//Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (list_des_pays dans la vue affichageitem
        adapter = new SimpleAdapter(this.getBaseContext(), list_des_pays, R.layout.libelle,
                new String[]{"libelle", "etat", "icone",}, new int[]{R.id.libelle, R.id.Etat, R.id.icone});
        maListView.setAdapter(adapter);
    }

    private void getReclamations() {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(ListeElementActivity.this);
            String URL = "http://10.0.2.2/reclam/reclamation.php?id=09958664"; // + id_c;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                            HashMap<String, String> map = new HashMap<String, String>();
                            JSONObject reclamation = jsonArray.getJSONObject(i);
                            map.put("libelle", reclamation.getString("libelle_r"));
                            map.put("id", reclamation.getString("id_r"));
                        map.put("etat", reclamation.getString("etat_r"));
                        map.put("theme", reclamation.getString("theme_r"));
                            map.put("icone", reclamation.getString("image_r"));
                            list_des_pays.add(map);
                    }
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                    adapter = new SimpleAdapter(ListeElementActivity.this, list_des_pays, R.layout.libelle,
                            new String[]{"libelle", "etat", "icone",}, new int[]{R.id.libelle, R.id.Etat, R.id.icone});
                    maListView.setAdapter(adapter);
                    maListView.invalidateViews();
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

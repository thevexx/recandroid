package com.example.admin.tutoserevices;

import android.*;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AjouterReclamation   extends Activity implements AdapterView.OnItemSelectedListener {
Button button1v ,button22,button3 ,button2A;
    private Button buttonChoose;
    private Button buttonUpload;
    private EditText editText;
    private ImageView imageView;
    //Image request code
    private int PICK_IMAGE_REQUEST = 1;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;

    //Bitmap to get image from gallery
    private Bitmap bitmap;
    private String id_c;

    //Uri to store the image uri
    private Uri filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_reclamation);
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        Spinner spinnerS= (Spinner) findViewById(R.id.spinner2);
        button1v = (Button) findViewById(R.id.button1);
        button22  =(Button) findViewById(R.id.button22);
        button2A =(Button) findViewById(R.id.button2A);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        id_c = preferences.getString("idc", "");

        button1v.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           // affichage des champs dans le Log
                                          //ajoutReclamations();
                                           // another comment
                                       }
                                   });
        Toast.makeText(getApplicationContext(), "Ajout avec succes", Toast.LENGTH_SHORT).show();

        button22.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AjouterReclamation.this, MainImage.class);
                startActivity(intent);
               // Toast.makeText(getApplicationContext(), "select avec succes", Toast.LENGTH_SHORT).show();

            }
        });
         Toast.makeText(getApplicationContext(), "select avec succes", Toast.LENGTH_SHORT).show();


        button2A.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(AjouterReclamation.this, MainActivity.class);
                startActivity(intent);
                // Toast.makeText(getApplicationContext(), "upload avec succes", Toast.LENGTH_SHORT).show();

            }
        });
        //Toast.makeText(getApplicationContext(), "upload avec succes", Toast.LENGTH_SHORT).show();

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        spinnerS.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> Theme = new ArrayList<String>();
        Theme.add("Habitat");
        Theme.add("Batiment civile");
        Theme.add("Urbanisme");
        Theme.add("Amenagement du territoire");
        Theme.add("pont et route");


        // Spinner Drop down elements
        List<String> Lieux = new ArrayList<String>();
        Lieux.add("Tunis");
        Lieux.add("Bizerte");
        Lieux.add("Ariana");
        Lieux.add("Manaouba");
        Lieux.add("Gafsa");
        Lieux.add("Zaghouan");
        Lieux.add("Tataouin");
        Lieux.add("Mounastir");
        Lieux.add("Mahdia");
        Lieux.add("Sousse");
        Lieux.add("Kairoun");
        Lieux.add("Gabes");
        Lieux.add("Nabeul");
        Lieux.add("Sfax");
        Lieux.add("Touzeur");
        Lieux.add("Kef");
        Lieux.add("Jandouba");
        Lieux.add("Kebeli");
        Lieux.add("Beja");
        Lieux.add("Siliana");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Theme);

        ArrayAdapter<String> dataAdapterS = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Lieux);

        // Drop down layout style - list view with radio button
        dataAdapterS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinnerS.setAdapter(dataAdapterS);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    public void uploadMultipart() {
        //getting name for the image
        String name = editText.getText().toString().trim();

        //getting the actual path of the image
        String path = getPath(filePath);

        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request
            new MultipartUploadRequest(this, uploadId, Constants.UPLOAD_URL)
                    .addFileToUpload(path, "image") //Adding file
                    .addParameter("name", name) //Adding text parameter to the request
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload(); //Starting the upload

        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    //method to show file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    //handling the image chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //method to get the file path from uri
    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }


    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onClick(View v) {
        if (v == buttonChoose) {
            showFileChooser();
        }
        if (v == buttonUpload) {
            uploadMultipart();
        }
    }

    private void ajoutReclamations() {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(AjouterReclamation.this);
            String URL = "http://10.0.2.2/reclam/reclamation.php?id="+id_c;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
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

            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

//                    params.put("username", username);
//                    params.put("password", pass);
                    return params;
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

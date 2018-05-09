package com.example.admin.tutoserevices;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class listRec extends AppCompatActivity {
    ListView listview;



    String[] countries = new String[] {

            "Samsung",

            "Nokia",

            "Blackberyy",

            "LG",

            "Motorolla",

            "Apple",

            "Karbon",

            "Micromax",

            "Zen",

    };
    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    //listview=getListView();

    // Declaring ArrayAdapter for the default listview

    ArrayAdapter<String> listadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,countries);



    // Setting ArrayAdapter for the default listview

        listview.setAdapter(listadapter);



    // Defining ItemClick event Listener







           //OnItemClickListener listener = new OnItemClickListener() {



    // This method will be triggered when an item in the listview is clicked ( or touched )

   listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        @Override

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Toast.makeText(getBaseContext(), "You selected : " + countries[i], Toast.LENGTH_SHORT).show();

        }

    });

    // Setting an ItemClickEvent Listener for the listview

    // In this example we are making use the default listview

    //getListView().setOnItemClickListener(listener);

}
}
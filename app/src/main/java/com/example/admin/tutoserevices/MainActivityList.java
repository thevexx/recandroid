package com.example.admin.tutoserevices;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityList extends ListActivity {
    private String[] reclamation = {"rec1", "rec2", "rec3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, reclamation);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView parent, View v, int position, long id) {
        super.onListItemClick(parent, v, position, id);
        Toast msg = Toast.makeText(this, reclamation[position], Toast.LENGTH_LONG);
        msg.show();

    }


}















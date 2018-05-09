package com.example.admin.tutoserevices;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SingleListItem extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_list_item);
        this.setContentView(R.layout.activity_single_list_item);

        TextView txtProduct = (TextView) findViewById(R.id.product_label);

        Intent i = getIntent();
        // getting attached intent data
        String product = i.getStringExtra("product");
        // displaying selected product name
        txtProduct.setText(product);

    }
    }


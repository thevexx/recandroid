package com.example.admin.tutoserevices;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<reclamation> recItems;
   // ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<reclamation> recItems) {
        this.activity = activity;
        this.recItems = recItems;
    }

    @Override
    public int getCount() {
        return recItems.size();
    }

    @Override
    public Object getItem(int location) {
        return recItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.activity_custom_list_adapter, null);

      /*  if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);*/
        TextView libelle = (TextView) convertView.findViewById(R.id.libelle);
        TextView theme = (TextView) convertView.findViewById(R.id.theme);
        TextView lieu = (TextView) convertView.findViewById(R.id.lieu);
        TextView image_r= (TextView) convertView.findViewById(R.id.image_r);
        TextView etat= (TextView) convertView.findViewById(R.id.etat);

        // getting movie data for the row
        reclamation m = recItems.get(position);

        // thumbnail image
       // thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // title
        libelle.setText(m.getLibelle_r());

        // etat
        etat.setText("etat: " + String.valueOf(m.getEtat_r()));
//
        // theme

        theme.setText(m.getTheme_r());

        //String themeStr = "";
       // for (String str : m.getTheme_r()) {
           // themeStr += str + ", ";
       // }
        //themeStr = themeStr.length() > 0 ? themeStr.substring(0,
               // themeStr.length() - 2) : themeStr;
       // theme.setText(themeStr);
        // lieu
        lieu.setText(m.getLieu_r());

        return convertView;
    }

}

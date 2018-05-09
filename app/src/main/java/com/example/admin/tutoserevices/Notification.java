package com.example.admin.tutoserevices;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Model> imageModelArrayList;
    private Adapter adapter;

   // private int[] myImageList = new int[]{R.drawable.benz, R.drawable.bike,
           // R.drawable.car,R.drawable.carrera
          //  ,R.drawable.ferrari,R.drawable.harly,
           // R.drawable.lamborghini,R.drawable.silver};
    private String[] myImageNameList = new String[]{"Benz", "Bike",
            "Car","Carrera"
            ,"Ferrari","Harly",
            "Lamborghini","Silver"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificatin);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        imageModelArrayList = populateList();
        Log.d("hjhjh", imageModelArrayList.size() + "");
        adapter = new Adapter(this,imageModelArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {

            @Override
            public void onClick(View view, int position) {
            //    Toast.makeText(MainActivity.this, imageModelArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
    private ArrayList<Model> populateList(){

        ArrayList<Model> list = new ArrayList<>();

        for(int i = 0; i < 8; i++){
            Model imageModel = new Model();
            imageModel.setName(myImageNameList[i]);
          //  imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}

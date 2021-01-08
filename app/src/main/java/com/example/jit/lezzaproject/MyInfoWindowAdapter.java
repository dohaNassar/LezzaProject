package com.example.jit.lezzaproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

public class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    Context context;
    Resturant r;

    public MyInfoWindowAdapter(Context context, Resturant r){
        this.context = context;
        this.r = r;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        final View view =  LayoutInflater.from(context).inflate(R.layout.marker_widget, null);

        view.setLayoutParams(new LinearLayout.LayoutParams(500, 625));

        ImageView iv = view.findViewById(R.id.imageView);
        Picasso.get().load(r.getPhoto()).into(iv);

        TextView resName = view.findViewById(R.id.resName);
        resName.setText(r.getRestaurant());

        TextView descTv = view.findViewById(R.id.descTv);
        descTv.setText(r.getDescrip());

        return view;
    }
}

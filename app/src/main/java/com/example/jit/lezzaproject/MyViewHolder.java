package com.example.jit.lezzaproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView photoIm;
    TextView nameTv;
    TextView priceTv;
    Button addBtn;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        photoIm=itemView.findViewById(R.id.itemPhotoIm);
        nameTv=itemView.findViewById(R.id.itemNameTv);
        priceTv=itemView.findViewById(R.id.itemPriceTv);
        addBtn = itemView.findViewById(R.id.addBtn);

    }
}
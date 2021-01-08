package com.example.jit.lezzaproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListAdapter  extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    ArrayList<Meal> meals;
    int itemLayout;
    String resturantName;

    public ListAdapter (Context context, ArrayList<Meal> meals,int itemLayout, String resturantName){
        this.meals = meals;
        this.itemLayout = itemLayout;
        this.resturantName = resturantName;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);
        view.setBackgroundResource(R.drawable.rectagle_shape);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {
        final Meal meal = meals.get(i);
        //holder.photoIm.setImageResource(meal.getImage());
        Picasso.get().load(meal.getImage()).into(holder.photoIm);
        holder.nameTv.setText(meal.getName());
        holder.priceTv.setText(meal.getPrice()+" NIS");
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t= Toast.makeText(holder.itemView.getContext(),meal.getName(),Toast.LENGTH_LONG);
                t.show();
                Intent i = new Intent(holder.itemView.getContext(), OrderActivity.class);
                i.putExtra("resturantName", resturantName);
                i.putExtra("orderName", meal.getName());
                i.putExtra("orderPrice", meal.getPrice());
                context.startActivity(i);
            }
        });

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t= Toast.makeText(holder.itemView.getContext(),meal.getName(),Toast.LENGTH_LONG);
                t.show();
                Intent i = new Intent(holder.itemView.getContext(), OrderActivity.class);
                i.putExtra("resturantName", resturantName);
                i.putExtra("orderName", meal.getName());
                i.putExtra("orderPrice", meal.getPrice());
                context.startActivity(i);

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }


}

package com.example.jit.lezzaproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class OrderAdapter extends BaseAdapter {

    Context context;
    ArrayList<Order> orders;
    int itemLayout;

    Order o;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    public OrderAdapter(Context context, ArrayList<Order> orders, int itemLayout) {
        this.context = context;
        this.orders = orders;
        this.itemLayout = itemLayout;
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(itemLayout, null);
            TextView orderNameTv = convertView.findViewById(R.id.orderName);
            TextView orderPriceTv = convertView.findViewById(R.id.orderPrice);
            TextView amountOrdersTv = convertView.findViewById(R.id.amountOrders);
            ViewHolder vh = new ViewHolder();
            vh.orderNameTv = orderNameTv;
            vh.orderPriceTv = orderPriceTv;
            vh.amountOrdersTv = amountOrdersTv;
            convertView.setTag(vh);
        }

        ViewHolder vh = (ViewHolder)convertView.getTag();
        o = orders.get(position);
        vh.orderNameTv.setText(o.getName());
        int finalPrice = o.getPrice()*o.getAmount();
        vh.orderPriceTv.setText("Final Price: "+finalPrice);
        vh.amountOrdersTv.setText("Amount: "+o.getAmount());



        return convertView;
    }

    public class ViewHolder{
        TextView orderNameTv;
        TextView orderPriceTv;
        TextView amountOrdersTv;
    }

}

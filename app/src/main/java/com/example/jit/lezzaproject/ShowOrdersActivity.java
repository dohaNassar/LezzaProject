package com.example.jit.lezzaproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShowOrdersActivity extends GroupActivity {

    ArrayList<Order> orders;
    FirebaseFirestore firestore;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_orders);

        Toolbar toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        TextView titleET = findViewById(R.id.toobartitle);
        titleET.setText("My Orders");

        firestore = FirebaseFirestore.getInstance();

        orders = new ArrayList<>();

        CollectionReference cr = firestore.collection("orders");
        Task<QuerySnapshot> task = cr.get();
        task.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Iterator<QueryDocumentSnapshot> iterator = queryDocumentSnapshots.iterator();

                while(iterator.hasNext()){
                    Order order = iterator.next().toObject(Order.class);
                    orders.add(order);
                }

                list = findViewById(R.id.listView);
                OrderAdapter adapter = new OrderAdapter(getApplicationContext(),orders, R.layout.item_order);
                list.setAdapter(adapter);


                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String orderName = orders.get(position).getName();
                        Intent i = new Intent(view.getContext().getApplicationContext(), OrderDetailsActivity.class);
                        i.putExtra("OrderName", orderName);
                        startActivity(i);
                    }
                });


            }
        });





    }
}

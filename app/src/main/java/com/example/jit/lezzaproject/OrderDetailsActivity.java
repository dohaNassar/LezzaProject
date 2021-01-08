package com.example.jit.lezzaproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Iterator;
import java.util.List;

public class OrderDetailsActivity extends GroupActivity {
    FirebaseFirestore firestore;
    TextView orderNameTv , addresTV, amountIV ,priceTV , finalPriceIV ;
    Button deleteBtn, updateBtn;
    String nameO ,amountO ,priceO ,addressO ;
    String IDDoc="";
    Order  order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        orderNameTv = findViewById(R.id.orderName);
        addresTV = findViewById(R.id.addressTv);
        amountIV= findViewById(R.id.amountTv);
        priceTV= findViewById(R.id.priceTv);
        finalPriceIV = findViewById(R.id.finalPriceTv);
        deleteBtn = findViewById(R.id.deleteBtn);
        updateBtn = findViewById(R.id.updateBtn);


        firestore = FirebaseFirestore.getInstance();

        Intent i = getIntent();
        String name = i.getStringExtra("OrderName");

        orderNameTv.setText(name);
        firestore = FirebaseFirestore.getInstance();

        CollectionReference dref = firestore.collection("orders");
        Task<QuerySnapshot> task = dref.whereEqualTo("name",name) .get();
        task.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Iterator<QueryDocumentSnapshot> iter = queryDocumentSnapshots.iterator();
                List<DocumentSnapshot> idL=queryDocumentSnapshots.getDocuments();
                IDDoc =idL.get(0).getId();
                Log.d("ttt",IDDoc);
                while (iter.hasNext()){
                    order = iter.next().toObject(Order.class);

                    orderNameTv.setText(order.getName());
                    addresTV.setText(order.getAddress());
                    amountIV.setText(order.getAmount()+"");
                    priceTV.setText(order.getPrice()+"");
                    int finalP = order.getPrice()*order.getAmount();
                    finalPriceIV.setText(finalP+"");

                    Log.d("ttt", "hello: "+order.getAddress()+" "+order.getAmount()+" "+order.getPrice()+" ");

                }

            }
        });

        nameO = orderNameTv.getText().toString();
        amountO = amountIV.getText().toString();
        priceO = priceTV.getText().toString();
        addressO = addresTV.getText().toString();


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), UpdateOrderActivity.class);
                i.putExtra("OrderName", nameO);
                i.putExtra("OrderAmount", amountO);
                i.putExtra("OrderPrice", priceO);
                i.putExtra("OrderAdress", addressO);
                i.putExtra("IDDoc", IDDoc);
                startActivity(i);
            }

        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestore.collection("orders").document(IDDoc).delete();
                Toast.makeText(getApplicationContext(), "done!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),ShowOrdersActivity.class);
                startActivity(i);
            }
        });

    }
}

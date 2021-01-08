package com.example.jit.lezzaproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;

public class MenuActivity extends GroupActivity {

    RecyclerView mealLv ;
    RecyclerView dessertLv;
    RecyclerView saladLv;
    RecyclerView drinkLv;

    FirebaseFirestore firestore;

    ArrayList<Meal> meals ;
    ArrayList<Meal> salads ;
    ArrayList<Meal> drinks ;
    ArrayList<Meal> desserts ;

    String resName;
    String resImg;

    TextView resNameTv;
    ImageView resIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        resNameTv = findViewById(R.id.resName);
        resIV = findViewById(R.id.resImg);
        Intent i = getIntent();
        resName = i.getStringExtra("resName");
        resImg = i.getStringExtra("img");
        resNameTv.setText(resName);
        Picasso.get().load(resImg).into(resIV);

        Toolbar toolBar = findViewById (R.id.toolbar);
        setSupportActionBar(toolBar);
        TextView titleET = findViewById(R.id.toobartitle);
        titleET.setText("Menu");

        firestore = FirebaseFirestore.getInstance();

        meals = new ArrayList<>();
        salads = new ArrayList<>();
        drinks = new ArrayList<>();
        desserts = new ArrayList<>();

        CollectionReference cr = firestore.collection("meals");
        Task<QuerySnapshot> task = cr.get();
        task.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Iterator<QueryDocumentSnapshot> iterator = queryDocumentSnapshots.iterator();
                while(iterator.hasNext()){
                    Meal meal = iterator.next().toObject(Meal.class);
                    meals.add(meal);
                    Log.d("ttt", meal.getName());
                }

                mealLv = findViewById(R.id.mealLV);
                ListAdapter mealAdapter = new ListAdapter( getApplicationContext(), meals, R.layout.item_design, resName);
                LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
                llm.setOrientation(LinearLayoutManager.HORIZONTAL);
                mealLv.setAdapter(mealAdapter);
                mealLv.setLayoutManager(llm);

                DividerItemDecoration did = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.HORIZONTAL);
                mealLv.addItemDecoration(did);
            }
        });

        CollectionReference cr1 = firestore.collection("drinks");
        Task<QuerySnapshot> task1 = cr1.get();
        task1.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Iterator<QueryDocumentSnapshot> iterator = queryDocumentSnapshots.iterator();
                while(iterator.hasNext()){
                    Meal drink = iterator.next().toObject(Meal.class);
                    drinks.add(drink);
                }

                drinkLv = findViewById(R.id.drinkLV);
                ListAdapter mealAdapter = new ListAdapter( getApplicationContext(),drinks, R.layout.item_design, resName);
                LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
                llm.setOrientation(LinearLayoutManager.HORIZONTAL);
                drinkLv.setAdapter(mealAdapter);
                drinkLv.setLayoutManager(llm);

                DividerItemDecoration did = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.HORIZONTAL);
                drinkLv.addItemDecoration(did);
            }
        });

        CollectionReference cr2 = firestore.collection("salads");
        Task<QuerySnapshot> task2 = cr2.get();
        task2.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Iterator<QueryDocumentSnapshot> iterator = queryDocumentSnapshots.iterator();
                while(iterator.hasNext()){
                    Meal salad = iterator.next().toObject(Meal.class);
                    salads.add(salad);
                }

                saladLv = findViewById(R.id.saladLV);
                ListAdapter mealAdapter = new ListAdapter( getApplicationContext(),salads, R.layout.item_design, resName);
                LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
                llm.setOrientation(LinearLayoutManager.HORIZONTAL);
                saladLv.setAdapter(mealAdapter);
                saladLv.setLayoutManager(llm);

                DividerItemDecoration did = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.HORIZONTAL);
                saladLv.addItemDecoration(did);
            }
        });

        CollectionReference cr3 = firestore.collection("desserts");
        Task<QuerySnapshot> task3 = cr3.get();
        task3.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Iterator<QueryDocumentSnapshot> iterator = queryDocumentSnapshots.iterator();
                while(iterator.hasNext()){
                    Meal dessert = iterator.next().toObject(Meal.class);
                    desserts.add(dessert);
                }

                dessertLv = findViewById(R.id.dessertLV);
                ListAdapter mealAdapter = new ListAdapter( getApplicationContext(),desserts, R.layout.item_design, resName);
                LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
                llm.setOrientation(LinearLayoutManager.HORIZONTAL);
                dessertLv.setAdapter(mealAdapter);
                dessertLv.setLayoutManager(llm);

                DividerItemDecoration did = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.HORIZONTAL);
                dessertLv.addItemDecoration(did);
            }
        });





    }
}

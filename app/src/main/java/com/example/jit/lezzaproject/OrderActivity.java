package com.example.jit.lezzaproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class OrderActivity extends GroupActivity {

    TextView mealNameTV ;
    TextView mealPriceTV ;
    int amount = 1;
    TextView amountTv;
    ImageButton plusBtn, minBtn;
    EditText dateET, mapET, phoneNumbET;
    String  address,phoneNumb, resturantName, newDate, email;
    int d,y,m;
    long date;
    Button dateBtn , requestBtn;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Toolbar toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        TextView titleET = findViewById(R.id.toobartitle);
        titleET.setText("Order");

        auth  = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        email = user.getEmail();

        Intent i = getIntent();

        //Resturant Name
        resturantName = i.getStringExtra("resturantName");

        //Order Name
        mealNameTV = findViewById(R.id.mealNameTV);
        mealNameTV.setText(i.getStringExtra("orderName"));

        //Order Price
        mealPriceTV = findViewById(R.id.mealPriceTV);
        mealPriceTV.setText(i.getIntExtra("orderPrice",-1 )+"");

        //To Make The Amount
        amountTv = findViewById(R.id.amountTv);

        plusBtn = findViewById(R.id.plusIMGBtn);
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount ++;
                amountTv.setText(amount+"");
            }
        });

        minBtn = findViewById(R.id.minasImgBtn);
        minBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount != 1 ){
                    amount --;
                    amountTv.setText(amount+"");
                }}
        });

        //To Get The Date
        dateET = findViewById(R.id.dateET);
        dateBtn = findViewById(R.id.dateBtn);
        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                int currentYear = c.get(Calendar.YEAR);
                int currentMonth = c.get(Calendar.MONTH);
                int currentDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dpd = new DatePickerDialog(OrderActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        y = year;
                        m = month;
                        d = dayOfMonth;

                    }
                }, currentYear, currentMonth, currentDay);
                dpd.show();
                dateET.setText(d + "/" + (m + 1) + "/" + y);

            }
        });


        //To Get The Location
        /*mapBtn = findViewById(R.id.mapBtn);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri loc = Uri.parse("geo:31.4167,34.3333?z=10");
                intent.setData(loc);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);

                }
            }
        });*/

        //To Save an Order
        firestore = FirebaseFirestore.getInstance();
        requestBtn = findViewById(R.id.requestBtn);
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mapET = findViewById(R.id.mapET);
                address = mapET.getText().toString();

                phoneNumbET = findViewById(R.id.phoneNum);
                phoneNumb = phoneNumbET.getText().toString();

                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR, y);
                c.set(Calendar.MONTH, m);
                c.set(Calendar.DAY_OF_MONTH, d);
                date = c.getTimeInMillis();
                newDate = date+"";

                //add Orders to database
                CollectionReference cr = firestore.collection("orders");
                Order order = new Order();
                order.setName(mealNameTV.getText().toString());
                order.setPrice(Integer.parseInt(mealPriceTV.getText().toString()));
                order.setResturantName(resturantName);
                order.setAmount(Integer.parseInt(amountTv.getText().toString()));
                order.setPhoneNumber(phoneNumb);
                order.setAddress(address);
                order.setDate(newDate);
                order.setEmail(email);

                Task<DocumentReference> t = cr.add(order).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getApplicationContext(), "Orders ADD Successfully", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(getApplicationContext(), OrderSuccessfullyActivity.class);
                                startActivity(i);
                            }
                        });

                        t.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Orders Add Filed: "+e.getMessage(), Toast.LENGTH_LONG).show();
                                Log.d("ttt", e.getMessage());

                            }
                        });





            }
        });

        //Go Back And Cancel The Order
        Button cancel = findViewById(R.id.cancelBTN);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}

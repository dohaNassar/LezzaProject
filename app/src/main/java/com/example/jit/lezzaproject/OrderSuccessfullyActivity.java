package com.example.jit.lezzaproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class OrderSuccessfullyActivity extends GroupActivity {

    Button show, continueBtn, chat;
    FirebaseAuth auth ;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_successfully);

        auth = FirebaseAuth.getInstance();

        user = auth.getCurrentUser();

        Toolbar toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        TextView titleET = findViewById(R.id.toobartitle);
        titleET.setText("Operation Done Successfully");


        show = findViewById(R.id.showBtn);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ShowOrdersActivity.class);
                startActivity(i);
                // Toast.makeText(getApplicationContext(), "show as list ", Toast.LENGTH_SHORT).show();
            }
        });

        continueBtn = findViewById(R.id.continueBtn);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(i);
            }
        });

        chat = findViewById(R.id.chatBtn);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    goToChatActivity(user.getEmail());
                }
            }
        });

    }


    private void goToChatActivity(String email){

        Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);

    }
}

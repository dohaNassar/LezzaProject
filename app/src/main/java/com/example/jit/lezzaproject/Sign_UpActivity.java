package com.example.jit.lezzaproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Sign_UpActivity extends GroupActivity {

    EditText emailET, passwordET, ConfermPass;
    Button loginBtn;
    String password, confPassword, email;

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);

        auth = FirebaseAuth.getInstance();

        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Log_inActivity.class);
                startActivity(i);
            }
        });

        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        ConfermPass = findViewById(R.id.confPasswordET);


        Button signUp = findViewById(R.id.signupBtn);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                password = passwordET.getText().toString();
                confPassword = ConfermPass.getText().toString();
                email = emailET.getText().toString();


                if (password != null && password.equals(confPassword) && email != null && !email.isEmpty()) {

                    Task<AuthResult> task = auth.createUserWithEmailAndPassword(email, password);
                    task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = auth.getCurrentUser();
                                String email = user.getEmail();
                                Toast.makeText(getApplicationContext(), "Welcome " + email, Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                                startActivity(i);



                            }
                        }
                    });


                    task.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            TextView logInTV =   findViewById(R.id.LogInTV);
                            logInTV.setText(e.getLocalizedMessage());
                            Toast.makeText(getApplicationContext(), "Error Message", Toast.LENGTH_SHORT).show();

                        }
                    });


                } else {
                    Toast.makeText(getApplicationContext(), "Password do not match!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

        }




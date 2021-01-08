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

public class Log_inActivity extends GroupActivity {

    EditText emailEt, passwordEt;
    Button loginBtn, signUp, resrtPassword;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        auth = FirebaseAuth.getInstance();


        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            Intent i = new Intent(getApplicationContext(), MapsActivity.class);
            i.putExtra("user", user);
            startActivity(i);
        }

        signUp = findViewById(R.id.signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Sign_UpActivity.class);
                startActivity(i);
            }
        });

        resrtPassword = findViewById(R.id.helpSigninBtn);
        resrtPassword.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ResetPasswordActivity.class);
                startActivity(i);
            }
        });


        emailEt = findViewById(R.id.emailET);
        passwordEt = findViewById(R.id.passwordET);
        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEt.getText().toString();
                String password = passwordEt.getText().toString();

                if (password != null && !password.isEmpty() && email != null && !email.isEmpty()) {

                    Task<AuthResult> task = auth.signInWithEmailAndPassword(email, password);
                    task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = auth.getCurrentUser();
                                String email = user.getEmail();
                                Toast.makeText(getApplicationContext(), "Successfully Login" + email, Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                                startActivity(i);

                            }

                        }
                    });

                    task.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            //goToChatActivity(authResult.getUser().getEmail());


                        }
                    });

                    task.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            TextView logInTV = findViewById(R.id.LogInTV);
                            logInTV.setText(e.getLocalizedMessage());
                            Toast.makeText(getApplicationContext(), "Error Message", Toast.LENGTH_SHORT).show();

                        }
                    });
                }


            }
        });


    }


}

package com.example.jit.lezzaproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText emailET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password2);

        emailET= findViewById(R.id.emailET);
        findViewById(R.id.reSetBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= emailET.getText().toString();
                if(email != null && ! email.trim().equalsIgnoreCase("")) {

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("plain/text");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[] { email });
                    intent.putExtra(Intent.EXTRA_SUBJECT, "reset password ");
                    intent.putExtra(Intent.EXTRA_TEXT, "mail body...");
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }

                    Toast.makeText(ResetPasswordActivity.this, "Check Your Email to Reset Your Password!", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Toast.makeText(getApplicationContext(), "Enter your Email!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Sign_UpActivity.class);
                startActivity(i);
                finish();

            }
        });


    }
}

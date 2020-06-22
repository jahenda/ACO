package com.jahendamercy.com.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jahendamercy.com.DatabaseConn;
import com.jahendamercy.com.PrivacyPolicy;
import com.jahendamercy.com.R;
import com.jahendamercy.com.TermofUse;

public class Registration extends AppCompatActivity {
    DatabaseConn db;
    EditText FullNameTextView;
    EditText phoneNumTextPhone;
    EditText editTextPostalAddress;
    TextView occupationTextView;
    EditText editTextTextEmailAddress;
    TextView reenterEmailTextView;
    TextView termofUse;
    TextView privacyPolicy;
    Button submitButton;
    Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new DatabaseConn(this);
        FullNameTextView = (EditText)findViewById(R.id.fullNameTextView);
        phoneNumTextPhone = (EditText)findViewById(R.id.phoneNumTextPhone);
        editTextPostalAddress = (EditText)findViewById(R.id.editTextPostalAddress);
        occupationTextView = (TextView) findViewById(R.id.occupationTextView);
        editTextTextEmailAddress = (EditText)findViewById(R.id.editTextTextEmailAddress);
        reenterEmailTextView = (TextView) findViewById(R.id.reenterEmailTextView);
        termofUse = (TextView) findViewById(R.id.termsofUse);
        privacyPolicy = (TextView) findViewById(R.id.privacyPolicy);
        submitButton = (Button) findViewById(R.id.submitButton);
        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(Registration.this, MainActivity.class);
                startActivity(loginIntent);
            }


        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = FullNameTextView.getText().toString().trim();
                String pwd = editTextTextEmailAddress.getText().toString().trim();
                String cfg_pwd = reenterEmailTextView.getText().toString().trim();

                if(pwd.equals(cfg_pwd)){
                    long val = db.addUser(user, pwd);
                    if(val > 0){
                        Toast.makeText(Registration.this,"You have registered", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(Registration.this, Login.class);
                        startActivity(moveToLogin);
                    }
                    else{
                        Toast.makeText(Registration.this,"Registration error", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(Registration.this,"Password is not matching", Toast.LENGTH_SHORT).show();
                }
            }
        });

        termofUse.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent termsofuseIntent = new Intent(Registration.this, TermofUse.class);
                startActivity(termsofuseIntent);
            }
        });

        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent privacypolicyIntent = new Intent(Registration.this, PrivacyPolicy.class);
                startActivity(privacypolicyIntent);
            }
        });
    }
}
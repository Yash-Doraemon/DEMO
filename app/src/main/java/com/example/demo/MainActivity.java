package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    EditText id,pass;
    String email,password;
    Button sign;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();
        id=findViewById(R.id.name);
        pass=findViewById(R.id.password);
        sign=findViewById(R.id.login);
        email=id.toString();
        password=pass.toString();
        progressDialog = new ProgressDialog(this);


        if(firebaseAuth.getCurrentUser() != null){
            //close this activity
            //finish();
            //opening profile activity\
            Toast.makeText(this, "Start new activity", Toast.LENGTH_SHORT).show();
           // startActivity(new Intent(getApplicationContext(), home.class));
        }
    }

    public void onClick(View view) {
        if(view == sign)
            login();


    }

    public void login() {
        String email = id.getText().toString().trim();
        String password  = pass.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Signing In");
        progressDialog.show();

        //logging in the user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successfull
                        if(task.isSuccessful()){
                            //start the 1st page activity
                            Toast.makeText(MainActivity.this, "Sign in Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), next.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void signup(View view) {

    }
}

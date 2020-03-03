package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class next extends AppCompatActivity {

    private FirebaseAuth firebaseAuth  = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText name,add;
    String names,adds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        name=findViewById(R.id.name);
        add=findViewById(R.id.add);
    }

    public void upload(View view) {
        names=name.getText().toString();
        adds=add.getText().toString();


        Map<String, Object> user = new HashMap<>();
        user.put("Name",names);
        user.put("Address",adds);
        db.collection("Demo").document(firebaseAuth.getUid())
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(next.this,"Data successfully written!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(next.this, "Error in writing data", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    public void retrive(View view) {
        Intent ret=new Intent(this,retrive.class);
        startActivity(ret);

    }
}

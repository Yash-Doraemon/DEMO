package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class retrive extends AppCompatActivity {
    TextView name,add;
    private FirebaseAuth firebaseAuth  = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrive);
        name=findViewById(R.id.Name);
        add=findViewById(R.id.Add);
//
//        final DocumentReference docRef = db.collection("Demo").document(firebaseAuth.getUid());
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        Toast.makeText(retrive.this, "Doc there", Toast.LENGTH_SHORT).show();
//                        name.setText(document.get("Name").toString());
//                        add.setText(document.get("Address").toString());
//                    } else {
//                        Toast.makeText(retrive.this, "No such doc", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(retrive.this, "couldn't connect", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


        db.collection("Demo")
                .whereEqualTo("Name", "Yash N Shah")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                           for (QueryDocumentSnapshot document : task.getResult())
                            {

                                    name.setText(document.get("Name").toString().toUpperCase());
                                    add.setText(document.get("Address").toString());
                            }
                        }
                    }
                });



    }
}

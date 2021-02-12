package com.CityHeros.ABCD_FragOfMenu;

import android.os.Bundle;

import android.widget.TextView;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.CityHeros.firstProject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class  Account extends AppCompatActivity {

    private TextView name, email, age, city;
    private String UID  ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_1_frag);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.Account);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");




        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        age = findViewById(R.id.age);
        city = findViewById(R.id.city);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        UID = user.getUid();



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String _name = dataSnapshot.child(UID).child("name").getValue(String.class);
                    name.setText(_name);
                    String _email = dataSnapshot.child(UID).child("email").getValue(String.class);
                    email.setText(_email);
                    String _age = dataSnapshot.child(UID).child("age").getValue(String.class);
                    age.setText(_age);
                    String _city = dataSnapshot.child(UID).child("city").getValue(String.class);
                    city.setText(_city);





            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








    }


    }


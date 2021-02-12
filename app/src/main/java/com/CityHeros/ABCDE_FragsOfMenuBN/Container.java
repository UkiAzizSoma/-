package com.CityHeros.ABCDE_FragsOfMenuBN;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;


import com.CityHeros.firstProject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Container extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nb_controler);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.nb_books);

        BottomNavigationView navigationView = findViewById(R.id.nav_new);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_frag_new, new NB_Books()).commit();


        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_frag_new,new NB_WorldRec()).commit();
            navigationView.setSelectedItemId(R.id.rec);

        }

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.books:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_frag_new, new NB_Books()).commit();
                        getSupportActionBar().setTitle(R.string.nb_books);
                        break;

                    case R.id.fame:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_frag_new, new NB_Fame()).commit();
                        getSupportActionBar().setTitle(R.string.nb_fame);
                        break;

                    case R.id.rec:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_frag_new, new NB_WorldRec()).commit();
                        getSupportActionBar().setTitle(R.string.nb_rec);
                        break;
                }

                return true;
            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
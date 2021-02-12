package com.CityHeros.A_CodeForIraq;



import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;

import android.view.View;
import android.widget.Toast;


import com.CityHeros.ABCDE_FragsOfMenuBN.Container;
import com.CityHeros.ABCD_FragOfMenu.AboutApp;
import com.CityHeros.ABCD_FragOfMenu.AboutCodeForIraq;
import com.CityHeros.ABCD_FragOfMenu.Account;
import com.CityHeros.ABCD_FragOfMenu.Study;
import com.CityHeros.ABCD_FragOfMenu.Team;

import com.CityHeros.AB_Beginning.A_Login_form;


import com.CityHeros.ABCD_FragOfMenu.FragVideos;
import com.CityHeros.firstProject.R;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class B_Sucka extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView ;
    Toolbar toolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_sucka);
        drawerLayout = findViewById(R.id.drawer_lay3ot);


        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle(R.string.frag_videos);

        setSupportActionBar(toolbar);






        //ActionBarDrawerToggle var = new Ac...(this,drawerlayout,toolbar,@string1,@string2)

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout , toolbar ,R.string.toggle1 , R.string.toggle2);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();




        //draw.. .addDrawerListener(toggle);
        //toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
           navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new FragVideos()).commit();
            navigationView.setCheckedItem(R.id.videos);
        }


        drawerLayout.isDrawerOpen(GravityCompat.START);




    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
       switch (menuItem.getItemId()){

            case R.id.videos:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment , new FragVideos()).commit();
               toolbar.setTitle(R.string.frag_videos);
            break;



           case R.id.study:
               Intent study = new Intent(B_Sucka.this , Study.class);
               startActivity(study);

            break;

           case R.id.lab:
            Intent continer = new Intent(B_Sucka.this , Container.class);

            startActivity(continer);

            break;

           case R.id.account:
               Intent account = new Intent(B_Sucka.this , Account.class);

               startActivity(account);
               break;

           case R.id.request:
            Intent Aboutapp = new Intent(B_Sucka.this , AboutApp.class);

            startActivity(Aboutapp);
            break;

           case R.id.about:
           Intent aboutCode = new Intent(B_Sucka.this , AboutCodeForIraq.class);

               startActivity(aboutCode);
           break;

           case R.id.team:
           Intent intetn = new Intent(B_Sucka.this , Team.class);

           startActivity(intetn);
           break;

           case R.id.signout:
              FirebaseAuth.getInstance().signOut();
               finish();
               Intent exit = new Intent( getApplicationContext(), A_Login_form.class);
               startActivity(exit);
               Toast.makeText(getApplicationContext(),R.string.logout,Toast.LENGTH_SHORT).show();

               break;

       }
       //
        // drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }



}


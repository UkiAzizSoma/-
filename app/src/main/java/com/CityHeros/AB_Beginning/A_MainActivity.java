package com.CityHeros.AB_Beginning;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;


import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import android.widget.TextView;


import com.CityHeros.A_CodeForIraq.B_Sucka;
import com.CityHeros.firstProject.R;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class A_MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth ;
    private  ViewPager viewPager;
    private LinearLayout dotss;
    private  TextView[] mDots ;
    private Button button1, button2;
    private  int mCurrent;
    A_Intro intero;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_sliders_main);

        firebaseAuth=FirebaseAuth.getInstance();


        viewPager = findViewById(R.id.viewPager);

        dotss= findViewById(R.id.dots);

        button1 = findViewById(R.id.next);
        button1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (mCurrent != 2){
                   viewPager.setCurrentItem(mCurrent +1);
               }else {
                Intent intent = new Intent(getApplicationContext(), A_Login_form.class);
                startActivity(intent);

               }






           }
       });

        button2 = findViewById(R.id.prev);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(mCurrent - 1);
            }
        });


         intero = new A_Intro(this);
         viewPager.setAdapter(intero);

          addFots(0);
          viewPager.addOnPageChangeListener(onPageChangeListener);

    }
    public void addFots(int position){
        mDots = new TextView[3];
        dotss.removeAllViews();


        for (int a = 0; a < mDots.length ;a++ ){
            mDots[a] = new TextView(this);
            mDots[a].setText(Html.fromHtml("&#8226;"));
            mDots[a].setTextSize(35);
            mDots[a].setTextColor(Color.LTGRAY);
            dotss.addView(mDots[a]);

        }
       if (mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.htmlColor));
        }
    }
    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
       public void onPageSelected(int i) {
           addFots(i);
           mCurrent = i;
           if (mCurrent ==0 ){
               button1.setEnabled(true);
               button2.setEnabled(false);
               button2.setVisibility(View.INVISIBLE);

               button1.setText("التالي");
               button2.setText("");
           }
           else if (i == mDots.length - 1 ){
               button1.setEnabled(true);

               button2.setEnabled(true);
               button2.setVisibility(View.VISIBLE);

               button1.setText("التالي");
               button2.setText("السابق");
           }else{
               button1.setEnabled(true);

               button2.setEnabled(true);
               button2.setVisibility(View.VISIBLE);
               button1.setText("التالي");
               button2.setText("السابق");


           }



        }

       @Override
       public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null){
            finish();
            Intent intent = new Intent(this , B_Sucka.class);
            startActivity(intent);


        }

    }


}
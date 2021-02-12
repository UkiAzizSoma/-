package com.CityHeros.ABCD_FragOfMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.CityHeros.firstProject.R;



public class B_CInformations extends AppCompatActivity {

    TextView title , disOne , titleTwo , disTwo ;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_b_control);

        getSupportActionBar().setTitle("قسم الدراسة");

        title = findViewById(R.id.study_title);
        disOne = findViewById(R.id.study_dis);
        titleTwo = findViewById(R.id.study_titleTwo);
        disTwo = findViewById(R.id.study_disTwo);


        TextView view = findViewById(R.id.goToWeb);
           final Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            int alpha = bundle.getInt("titleOne");
            title.setText(alpha);

            int a = bundle.getInt("disOne");
            disOne.setText(a);
            int b = bundle.getInt("titleTwo");
            titleTwo.setText(b);
            int c = bundle.getInt("disTwo");
            disTwo.setText(c);

            view.setOnClickListener(new View.OnClickListener() {



                @Override
                public void onClick(View view) {
                    Uri viewWebPage = Uri.parse(bundle.getString("onClick"));
                    Intent chooser = new Intent(Intent.ACTION_VIEW,viewWebPage);
                    startActivity(chooser);


                }


            });


        }
    }
}

package com.CityHeros.ABCD_FragOfMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.CityHeros.firstProject.R;


public class A_CInformations extends AppCompatActivity {

    ImageView view ;
    TextView titleOne , disOne , titleTwo , disTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_a_control);
        getSupportActionBar().setTitle(R.string.arbic_people);

        titleOne = findViewById(R.id.titleOne);
        titleTwo = findViewById(R.id.titleTwo);
        disOne = findViewById(R.id.disOne);
        disTwo = findViewById(R.id.disTwo);

        view = findViewById(R.id.youTubeb);

        final Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            int a = bundle.getInt("title");
            titleOne.setText(a);

            int b = bundle.getInt("dis");
            disOne.setText(b);

            int a1 = bundle.getInt("titleTwo");
            titleTwo.setText(a1);

            int b1 = bundle.getInt("disTwo");
            disTwo.setText(b1);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   Uri uri = Uri.parse(bundle.getString("clickButton"));
                   Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                   startActivity(intent);
                }
            });

        }

     }
}

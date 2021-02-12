package com.CityHeros.ABCD_FragOfMenu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.CityHeros.firstProject.R;

public class Study extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_7_frag);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.frag_study);

        //String arrays
        String[] sevenTitle = {"العلوم","الآداب","الفلسفة","النقد ","الفن","الدين","التأريخ"};
        String[] sevenContent = {"نظر عامة عن العلوم وتعريفها","نظرة عامة عن الآداب وتعريفها","الفلسفة وتاريخها واهم الفلاسفة "," كثير من الاشخاص يبحثون عن تطوير شخصيانهم من خلال النقد البناء","الفنون تعريفها واختلافها بين الثقافات","عالم الدين المفيد والعقيدة الصحيحة","متى بدأ التاريخ ومن هم اشهر الاشخاص في التاريخ"};
        //integer array
        int[] videosImages= {R.drawable.ic_notebook,R.drawable.ic_notebook,R.drawable.ic_notebook
                ,R.drawable.ic_notebook,R.drawable.ic_notebook,R.drawable.ic_notebook,R.drawable.ic_notebook};



        ListView videoss ;
        Study.MyAdapterFrag myAdapterFrag = new Study.MyAdapterFrag(getApplicationContext(), sevenTitle , sevenContent , videosImages );
        videoss = findViewById(R.id.listViewFrags);
        videoss.setAdapter(myAdapterFrag);
        videoss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0){
                    Intent intent = new Intent(getApplicationContext(),B_CInformations.class);
                    intent.putExtra("titleOne",R.string.title_study_section);
                    intent.putExtra("disOne",R.string.study_title);
                    intent.putExtra("titleTwo",R.string.study_title_two);
                    intent.putExtra("disTwo",R.string.study_dis_two);
                    intent.putExtra("onClick","https://mawdoo3.com/%D8%AA%D8%B9%D8%B1%D9%8A%D9%81_%D8%A7%D9%84%D8%B9%D9%84%D9%85#:~:text=%20%D9%8A%D9%8F%D9%82%D8%B3%D9%8E%D9%85%20%D8%A7%D9%84%D8%B9%D9%84%D9%85%20%D8%A7%D8%B9%D8%AA%D9%85%D8%A7%D8%AF%D8%A7%D9%8B%20%D8%B9%D9%84%D9%89%20%D8%A3%D8%B3%D8%A7%D8%B3%20%D8%A7%D9%84%D9%85%D8%AC%D8%A7%D9%84%D8%A7%D8%AA%20%D8%A7%D9%84%D8%AA%D9%8A,14%20%D8%A7%D9%84%D8%B9%D9%84%D9%88%D9%85%20%D8%A7%D9%84%D8%B7%D8%A8%D9%8A%D8%B9%D9%8A%D8%A9%3A%20%28%D8%A8%D8%A7%D9%84%D8%A5%D9%86%D8%AC%D9%84%D9%8A%D8%B2%D9%8A%D8%A9%3A%20Natural%20Science%29%D8%8C...%20More%20");

                    startActivity(intent);
                }
                if (i == 1){
                    Intent intent = new Intent(getApplicationContext(),B_CInformations.class);
                    intent.putExtra("titleOne",R.string.study_title1);
                    intent.putExtra("disOne",R.string.study_dis1);
                    intent.putExtra("titleTwo",R.string.study_title2);
                    intent.putExtra("disTwo",R.string.study_dis2);
                    intent.putExtra("onClick","https://mawdoo3.com/%D8%AA%D8%B9%D8%B1%D9%8A%D9%81_%D8%A7%D9%84%D8%A3%D8%AF%D8%A8");
                    startActivity(intent);
                }
                if (i == 2){
                    Intent intent = new Intent(getApplicationContext(),B_CInformations.class);
                    intent.putExtra("titleOne",R.string.study_title3);
                    intent.putExtra("disOne",R.string.study_dis3);
                    intent.putExtra("titleTwo",R.string.study_title4);
                    intent.putExtra("disTwo",R.string.study_dis4);
                    intent.putExtra("onClick","https://mawdoo3.com/%D8%AA%D8%B9%D8%B1%D9%8A%D9%81_%D8%A7%D9%84%D9%81%D9%84%D8%B3%D9%81%D8%A9");
                    startActivity(intent);
                }
                if (i == 3){
                    Intent intent = new Intent(getApplicationContext(),B_CInformations.class);
                    intent.putExtra("titleOne",R.string.study_title5);
                    intent.putExtra("disOne",R.string.study_dis5);
                    intent.putExtra("titleTwo",R.string.study_title6);
                    intent.putExtra("disTwo",R.string.study_dis6);
                    intent.putExtra("onClick","https://mawdoo3.com/%D8%AA%D8%B9%D8%B1%D9%8A%D9%81_%D8%A7%D9%84%D9%81%D9%84%D8%B3%D9%81%D8%A9");

                    startActivity(intent);
                }
                if (i == 4){
                    Intent intent = new Intent(getApplicationContext(),B_CInformations.class);
                    intent.putExtra("titleOne",R.string.study_title9);
                    intent.putExtra("disOne",R.string.study_dis9);
                    intent.putExtra("titleTwo",R.string.empty);
                    intent.putExtra("disTwo",R.string.empty);
                    intent.putExtra("onClick","https://mawdoo3.com/%D8%AA%D8%B9%D8%B1%D9%8A%D9%81_%D8%A7%D9%84%D9%81%D9%86");

                    startActivity(intent);
                }
                if (i == 5){
                    Intent intent = new Intent(getApplicationContext(),B_CInformations.class);
                    intent.putExtra("titleOne",R.string.study_title10);
                    intent.putExtra("disOne",R.string.study_dis10);
                    intent.putExtra("titleTwo",R.string.study_title11);
                    intent.putExtra("disTwo",R.string.study_dis11);
                    intent.putExtra("onClick","https://mawdoo3.com/%D8%AA%D8%B9%D8%B1%D9%8A%D9%81_%D8%A7%D9%84%D8%AF%D9%8A%D9%86");

                    startActivity(intent);
                }
                if (i == 6){
                    Intent intent = new Intent(getApplicationContext(),B_CInformations.class);
                    intent.putExtra("titleOne",R.string.study_title7);
                    intent.putExtra("disOne",R.string.study_dis7);
                    intent.putExtra("titleTwo",R.string.study_title8);
                    intent.putExtra("disTwo",R.string.study_dis8);
                    intent.putExtra("onClick","https://mawdoo3.com/%D9%85%D9%81%D9%87%D9%88%D9%85_%D8%A7%D9%84%D8%AA%D8%A7%D8%B1%D9%8A%D8%AE");

                    startActivity(intent);
                }
            }
        });

    }
    static class MyAdapterFrag extends ArrayAdapter<String> {
        //Start of Fields
        Context context;
        String[] mvideosTitle;
        String[]  mvideosContent;
        int[] mvideosImages;
        //End

        //Constructor
        private MyAdapterFrag(Context c , String[] titles , String[] content , int[] images){
            super(c , R.layout.c_7_frag, R.id.video_title,titles);
            this.context=c ;
            this.mvideosTitle = titles;
            this.mvideosContent = content;
            this.mvideosImages = images;
        }
        //End of constructor




        //getView method
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rows = inflater.inflate(R.layout.c_7_custom,parent,false);
            TextView textView = rows.findViewById(R.id.title_seven);
            TextView textView1 = rows.findViewById(R.id.clearify_seven);
            ImageView imageView = rows.findViewById(R.id.seven);

            textView.setText(mvideosTitle[position]);
            textView1.setText(mvideosContent[position]);
            imageView.setImageResource(mvideosImages[position]);

            return rows;
        }
        //End of getView
    }
}

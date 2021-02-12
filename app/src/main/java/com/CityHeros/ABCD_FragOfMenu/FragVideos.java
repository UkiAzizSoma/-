package com.CityHeros.ABCD_FragOfMenu;

import android.content.Context;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.CityHeros.firstProject.R;




/**
 * A simple {@link Fragment} subclass.
 */
public class FragVideos extends Fragment  {



    public FragVideos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //String arrays

        String[] videosTitle = {"كيف ابدأ من الصفر","اساسيات عالم البرمجة","تطوير الشخصية","الدين ","معلومات عامة","الثقافة وحب المعرفة","صناعة المحتوى"};
        String[] videosContent = {"د.أبراهيم الفقي","أ.محمد عيسى","كاريزما بالعربي","د.عبدالله رشدي","مساحة"," بيت القصص","سكوري"};
        //integer array
        int[] videosImages= {R.drawable.ic_video_circle,R.drawable.ic_video_circle,R.drawable.ic_video_circle
                ,R.drawable.ic_video_circle,R.drawable.ic_video_circle,R.drawable.ic_video_circle,R.drawable.ic_video_circle};


        ListView videos ;




        View news = inflater.inflate(R.layout.c_frag_videos,container,false);
        videos = news.findViewById(R.id.listViewFrag);
        MyAdapterFrag myAdapterFrag = new MyAdapterFrag( getContext(), videosTitle , videosContent , videosImages );






        videos.setAdapter(myAdapterFrag);

        videos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            if (i == 0){
                    Intent intent = new Intent(getContext(), A_CInformations.class);
                    intent.putExtra("title",R.string.dr_name);
                    intent.putExtra("dis",R.string.disOne);
                    intent.putExtra("titleTwo",R.string.titleTwo);
                    intent.putExtra("disTwo",R.string.disTwo);
                    intent.putExtra("clickButton","https://www.youtube.com/results?search_query=%D8%AF+%D8%A7%D8%A8%D8%B1%D8%A7%D9%87%D9%8A%D9%85+%D8%A7%D9%84%D9%81%D9%82%D9%89");

                    startActivity(intent);


                }else if (i == 1){
                Intent intent = new Intent(getContext(), A_CInformations.class);
                intent.putExtra("title",R.string.MuhammedEssa);
                intent.putExtra("dis",R.string.dis1);
                intent.putExtra("titleTwo",R.string.titleTwo);
                intent.putExtra("disTwo",R.string.dis2);
                intent.putExtra("clickButton","https://www.youtube.com/watch?v=fY2LZUqGXPk&list=PLMYF6NkLrdN817O88GXt4xAPmM2bD7pLN");

                startActivity(intent);
            }
            else if (i == 2){
                Intent intent = new Intent(getContext(), A_CInformations.class);
                intent.putExtra("title",R.string.Karisma);
                intent.putExtra("dis",R.string.dis3);
                intent.putExtra("titleTwo",R.string.titleTwo);
                intent.putExtra("disTwo",R.string.dis4);
                intent.putExtra("clickButton","https://www.youtube.com/watch?v=YUBbd9A9beE");

                startActivity(intent);
            }
            else if (i == 3){
                Intent intent = new Intent(getContext(), A_CInformations.class);
                intent.putExtra("title",R.string.Abdullah);
                intent.putExtra("dis",R.string.dis5);
                intent.putExtra("titleTwo",R.string.titleTwo);
                intent.putExtra("disTwo",R.string.dis6);
                intent.putExtra("clickButton","https://www.youtube.com/watch?v=eSnkb3ueBXk&list=PLkEApMuxh3Gf1XiPuPPojq1Dus9Wwg2nC");

                startActivity(intent);
            }
            else if (i == 4){
                Intent intent = new Intent(getContext(), A_CInformations.class);
                intent.putExtra("title",R.string.Misaha);
                intent.putExtra("dis",R.string.dis7);
                intent.putExtra("titleTwo",R.string.titleTwo);
                intent.putExtra("disTwo",R.string.dis8);
                intent.putExtra("clickButton","https://www.youtube.com/watch?v=JFZ2seS43Ps&list=PLWBXmDFbQd0h82XtkVhcaYPjMkwQnv6HI");
                startActivity(intent);
            }
            else if (i == 5){
                Intent intent = new Intent(getContext(), A_CInformations.class);
                intent.putExtra("title",R.string.Abdalkhaliq);
                intent.putExtra("dis",R.string.dis9);
                intent.putExtra("titleTwo",R.string.titleTwo);
                intent.putExtra("disTwo",R.string.dis10);
                intent.putExtra("clickButton","https://www.youtube.com/watch?v=v4k4AaRQa0o&list=PLX9HA3bjE4Pyyt3KYcWy5Elx3bjUrM7h4");


                startActivity(intent);
            }
            else if (i == 6){
                Intent intent = new Intent(getContext(), A_CInformations.class);
                intent.putExtra("title",R.string.Scoory);
                intent.putExtra("dis",R.string.dis11);
                intent.putExtra("titleTwo",R.string.titleTwo);
                intent.putExtra("disTwo",R.string.dis12);
                intent.putExtra("clickButton","https://www.youtube.com/watch?v=0jv5-dwWhqY&list=PLo-iEZl9ONdiVumERDWVrFA75q6mUi1Hk");

                startActivity(intent);
            }



            }
        });


        return news;

    }



    static class MyAdapterFrag extends ArrayAdapter<String>{
        //Start of Fields
        Context context;
        String[] mvideosTitle;
        String[]  mvideosContent;
        int[] mvideosImages;
        //End

        //Constructor
        private MyAdapterFrag(Context contxet , String[] titles , String[] content , int[] images){
            super(contxet, R.layout.c_frag_videos_custom, R.id.video_title,titles);
           this.mvideosTitle = titles;
           this.mvideosContent = content;
           this.mvideosImages = images;
           this.context=contxet;
        }
        //End of constructor




        //getView method
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rows = inflater.inflate(R.layout.c_frag_videos_custom,parent,false);
            TextView textView = rows.findViewById(R.id.video_title);
            TextView textView1 = rows.findViewById(R.id.video_content);
            ImageView imageView = rows.findViewById(R.id.video_image);

            textView.setText(mvideosTitle[position]);
            textView1.setText(mvideosContent[position]);
            imageView.setImageResource(mvideosImages[position]);
            return rows;
        }
        //End of getView
    }
}

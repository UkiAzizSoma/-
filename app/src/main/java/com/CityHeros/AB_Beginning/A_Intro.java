package com.CityHeros.AB_Beginning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;


import androidx.annotation.NonNull;

import androidx.viewpager.widget.PagerAdapter;

import com.CityHeros.firstProject.R;


public class A_Intro extends PagerAdapter {
    private final Context mContext;


    A_Intro(Context mContext) {
        this.mContext = mContext;
    }

    private final int[] strings= {R.string.title,R.string.title2,R.string.title3};
    private final int[] strings1= {R.string.dic_one,R.string.dic_two,R.string.dis_three};
    private final int [] Ss = { R.drawable.ic_pic_fornow,R.drawable.ic_lab,R.drawable.ic_study_pic};


    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View view = layoutInflater.inflate(R.layout.a_slide_page, container,false);

        ImageView ss = view.findViewById(R.id.img1);
        TextView textView = view.findViewById(R.id.labtitle);
        TextView textView1 = view.findViewById(R.id.discrption);

        ss.setImageResource(Ss[position]);
        textView.setText(strings[position]);
        textView1.setText(strings1[position]);
        container.addView(view);

       return view;


    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

    }
}


package com.CityHeros.ABCDE_FragsOfMenuBN;


import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.CityHeros.firstProject.R;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NB_Fame extends Fragment {


    public NB_Fame() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.nb_fame, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.fame_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<NB_FameModle> nb_fameModles = new ArrayList<>();

            NB_FameModle moduel =  new NB_FameModle("agatha_christie", "اضغط للأطلاع", R.drawable.agatha_christie,"https://ar.wikipedia.org/wiki/%D8%A3%D8%AC%D8%A7%D8%AB%D8%A7_%D9%83%D8%B1%D9%8A%D8%B3%D8%AA%D9%8A");

            NB_FameModle moduel1 =  new NB_FameModle("dame_barbara", "اضغط للأطلاع", R.drawable.dame_barbara_cartland_allan_warren , "https://ar.wikipedia.org/wiki/%D8%A8%D8%A7%D8%B1%D8%A8%D8%B1%D8%A7_%D9%83%D8%A7%D8%B1%D8%AA%D9%84%D9%86%D8%AF");
            NB_FameModle moduel2 =  new NB_FameModle("georges_simenon", "اضغط للأطلاع", R.drawable.georges_simenon , "https://ar.wikipedia.org/wiki/%D8%AC%D9%88%D8%B1%D8%AC_%D8%B3%D9%8A%D9%85%D9%86%D9%88%D9%86");
            NB_FameModle moduel3 =  new NB_FameModle("harold_robbins", "اضغط للأطلاع", R.drawable.harold_robbins , "https://ar.wikipedia.org/wiki/%D9%87%D8%A7%D8%B1%D9%88%D9%84%D8%AF_%D8%B1%D9%88%D8%A8%D9%86%D8%B3%D9%88%D9%86\n");
            NB_FameModle moduel4 =  new NB_FameModle("shakespeare", "اضغط للأطلاع", R.drawable.shakespeare , "https://ar.wikipedia.org/wiki/%D9%88%D9%84%D9%8A%D9%85_%D8%B4%D9%83%D8%B3%D8%A8%D9%8A%D8%B1");
            nb_fameModles.add(moduel);
            nb_fameModles.add(moduel1);
            nb_fameModles.add(moduel2);
            nb_fameModles.add(moduel3);
            nb_fameModles.add(moduel4);


        RecyclerView.Adapter adapter = new NB_FameAdapter(getContext(), nb_fameModles);
        recyclerView.setAdapter(adapter);
        return view;
    }

}

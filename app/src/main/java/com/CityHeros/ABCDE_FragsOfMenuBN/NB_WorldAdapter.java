package com.CityHeros.ABCDE_FragsOfMenuBN;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.CityHeros.firstProject.R;

import com.squareup.picasso.Picasso;


import java.util.List;

public class NB_WorldAdapter  extends RecyclerView.Adapter<NB_WorldAdapter.WorldHolder> {

    private final Context context ;
    private final List<NB_WorldModel> list ;

    public NB_WorldAdapter(Context context, List<NB_WorldModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public WorldHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.nb_world_custom , parent , false);
        return new WorldHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull WorldHolder holder, int position) {
          NB_WorldModel moduel = list.get(position) ;
          holder.name.setText(moduel.getName());
          Picasso.with(context)
                .load(moduel.getmImage())

                .fit()
                .centerCrop()
                .into(holder.admin);






    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WorldHolder extends RecyclerView.ViewHolder {
        public TextView name;
        private ImageView admin ;
        public WorldHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.Author);
            admin = itemView.findViewById(R.id.AuthorImages);
        }
    }
}

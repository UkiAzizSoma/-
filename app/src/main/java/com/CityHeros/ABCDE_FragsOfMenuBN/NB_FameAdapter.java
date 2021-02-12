package com.CityHeros.ABCDE_FragsOfMenuBN;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.CityHeros.firstProject.R;


import java.util.List;

public class NB_FameAdapter extends RecyclerView.Adapter<NB_FameAdapter.HolderData> {


    private final Context context ;
    private final List<NB_FameModle> list;

    public NB_FameAdapter(Context context, List<NB_FameModle> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.nb_fame_recycler , parent , false);
       return new HolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
    NB_FameModle modle =list.get(position);
    holder.name.setText(modle.getName());
    holder.tap.setImageResource(R.drawable.ic_tap);
    holder.image.setImageResource(modle.getImage());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class HolderData extends RecyclerView.ViewHolder
        implements View.OnClickListener
    {
        public TextView name ;

        public ImageView image ,tap;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            name = itemView.findViewById(R.id.auth_name);
            tap = itemView.findViewById(R.id.tap);
            image= itemView.findViewById(R.id.auth_image);



        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            NB_FameModle nbFameModle = list.get(position);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(nbFameModle.getUrl()));
            context.startActivity(intent);

        }
    }
}

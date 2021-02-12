package com.CityHeros.ABCDE_FragsOfMenuBN;

import android.annotation.SuppressLint;
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

public class NB_AdapterRecycle extends RecyclerView.Adapter<NB_AdapterRecycle.Holder> {
    private final Context context ;
    private final List<NB_Moduel> list;

    public NB_AdapterRecycle(Context context, List<NB_Moduel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.nb_card_view_recycler , parent , false);
        return  new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
     NB_Moduel moduel = list.get(position);
     holder.name.setText(moduel.getName());
     holder.view.setImageResource(R.drawable.ic_file_pdf);
     holder.down.setImageResource(R.drawable.ic_downlaodarrow);






    }

    @Override
    public int getItemCount() {
        return list.size();
    }

public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView view , down ;
    public TextView name  ;




    public Holder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        view = itemView.findViewById(R.id.books_images);
        name = itemView.findViewById(R.id.book_names);
        down = itemView.findViewById(R.id.down);






    }

    @SuppressLint("IntentReset")
    @Override
    public void onClick(View view) {
        int position = getAdapterPosition();
        NB_Moduel nb_moduel = list.get(position);


        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setType("application/pdf");
        intent.setData(Uri.parse(nb_moduel.getAnyUri()));
        context.startActivity(intent);
    }
}
}

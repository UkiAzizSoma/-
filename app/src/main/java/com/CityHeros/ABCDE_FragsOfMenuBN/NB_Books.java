package com.CityHeros.ABCDE_FragsOfMenuBN;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.CityHeros.firstProject.R;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class NB_Books extends Fragment {
    private static final int REQUSTCODE = 12 ;
    private RecyclerView recyclerView;
    private NB_AdapterRecycle nb_adapterRecycle;
    private List<NB_Moduel> list ;
    private ProgressBar progressBar;



    public NB_Books() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("upload");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.nb_books, container, false);

        progressBar = view.findViewById(R.id.progressBar5);
        recyclerView = view.findViewById(R.id.recycle_lab);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    NB_Moduel moduel  = snapshot1.getValue(NB_Moduel.class);
                     list.add(moduel);
                }
                nb_adapterRecycle = new NB_AdapterRecycle(getContext() , list);
                progressBar.setVisibility(View.INVISIBLE);
                recyclerView.setAdapter(nb_adapterRecycle);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();

                progressBar.setVisibility(View.INVISIBLE);
                //toast

            }
        });









        FloatingActionButton button = view.findViewById(R.id.buttonFloting);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET) +
                        ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.INTERNET)
                            || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("تأكيد الأذن")
                                .setMessage("امنح الموافقة اذا اردت رفع البيانات")
                                .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest
                                                .permission.INTERNET, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUSTCODE);
                                        Intent intent = new Intent(getContext(), NB_Upload.class);
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("لا", null);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    } else {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.INTERNET,
                                Manifest.permission.READ_EXTERNAL_STORAGE}, REQUSTCODE);
                    }


                }else {
                    Toast.makeText(getContext(),"تم اخذ الأذن مسبقا",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), NB_Upload.class);
                    startActivity(intent);
                }
            }
        });



        return view;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUSTCODE ){
            if ((grantResults.length > 0) && (grantResults[0]) + grantResults[1] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getContext(),"تم الموافقة",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), NB_Upload.class);
                startActivity(intent);

            }else {
                Toast.makeText(getContext(),"تم الرفض",Toast.LENGTH_SHORT).show();

            }

        }
    }




}

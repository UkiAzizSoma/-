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

/**
 * A simple {@link Fragment} subclass.
 */
public class NB_WorldRec extends Fragment {
    private static final int REQUSTCODE = 1 ;
     private RecyclerView recyclerView ;
     private NB_WorldAdapter  adapter ;
     private List<NB_WorldModel> nb_worldModels ;


    private ProgressBar progressBar;




    public NB_WorldRec() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {




        //Firebase Database
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("uploadsAdmin");

        //Floating ActionBar


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.nb__world_rec, container, false);

        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
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
                    Intent intent = new Intent(getActivity(), NB_Uplaod_Admin.class);
                    startActivity(intent);
                }

            }
        });

        progressBar = view.findViewById(R.id.progressBar4);
        recyclerView = view.findViewById(R.id.recyclerWorld);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        nb_worldModels = new ArrayList<>();


         myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        NB_WorldModel a = snapshot1.getValue(NB_WorldModel.class);

                        nb_worldModels.add(a);



                }

                adapter = new NB_WorldAdapter(getContext(),nb_worldModels);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });









        return view;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUSTCODE ){
            if (grantResults.length > 0 && grantResults[0] +grantResults[1] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getContext(),"تم الموافقة",Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(getContext(),"تم الرفض",Toast.LENGTH_SHORT).show();

            }

        }
    }
}

package com.CityHeros.ABCDE_FragsOfMenuBN;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.CityHeros.firstProject.R;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;


public class NB_Upload extends AppCompatActivity {

    private static final int PICK_PDF = 1;

    private TextView text_refear;
    private Uri pdfUD;
    private ProgressBar progressBar ;


    private EditText bookName;
    private StorageTask storageTask;
    private StorageReference mStorageRef;
    private DatabaseReference myRef;
    private ImageView imageshow;
    //private StorageTask storageTask ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nb_upload);


        //Button connection
        Button add_file_btn = findViewById(R.id.add_file_btn);
        Button upload = findViewById(R.id.upload);


        // TextView connection
        text_refear = findViewById(R.id.text_refear);
        text_refear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_refear.setText("  <-- اضغط على زر الاضافة");
            }
        });

         //progressBar3
        progressBar = findViewById(R.id.progressBar3);

        // EditText connection
        bookName = findViewById(R.id.defultName);

        //buttons Events
        add_file_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPdfFile();
            }
        });


        //imageshow
        imageshow = findViewById(R.id.imageShow);


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload();
            }
        });

        //FireBase Stuff
        myRef = FirebaseDatabase.getInstance().getReference("upload");

        mStorageRef = FirebaseStorage.getInstance().getReference("upload");


    }

    private void upload() {
        if (pdfUD != null) {
            StorageReference storageReference = mStorageRef.child("upload" + System.currentTimeMillis() + ".pdf");
            storageTask = storageReference.putFile(pdfUD).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot snapshot) {

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(0);
                        }
                    }, 500);
                    Task<Uri> uriTask = snapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isComplete()) ;
                    pdfUD = uriTask.getResult();
                    Toast.makeText(getApplicationContext(), "تم تحميل الملف", Toast.LENGTH_SHORT).show();
                    NB_Moduel nb_moduel = new NB_Moduel(bookName.getText().toString().trim(), pdfUD.toString());
                    String s = myRef.push().getKey();
                    myRef.child(s).setValue(nb_moduel);
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(getApplicationContext(),Container.class);
                    startActivity(intent);





                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress = (100 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress((int) progress);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }else{
            Toast.makeText(getApplicationContext(),"الرجاء اضافة ملفPDF",Toast.LENGTH_SHORT).show();
        }
    }

    private void openPdfFile() {
        Intent pdf = new Intent();
        pdf.setType("application/pdf");
        pdf.setAction(Intent.ACTION_GET_CONTENT);
        startActivityIfNeeded(pdf, PICK_PDF);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PDF && resultCode == RESULT_OK && data != null && data.getData() != null) {
            pdfUD = data.getData();
           // Toast.makeText(getApplicationContext(),"تم تجهيز الملف",Toast.LENGTH_LONG).show();
            // text_refear.setText(data.getDataString().substring(data.getDataString().lastIndexOf("/") + PICK_PDF));
          //  text_refear.setText("تم تجهيز الملف");
            imageshow.setImageResource(R.drawable.ic_file_pdf);
        }
    }
}
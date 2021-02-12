package com.CityHeros.ABCDE_FragsOfMenuBN;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
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
import com.squareup.picasso.Picasso;

public class NB_Uplaod_Admin extends AppCompatActivity {

    private ImageView imageAdmin;
    private EditText name;
    private Uri imageUD;
    private TextView textView ;
    private StorageReference mStorageRef;
    private DatabaseReference myRef;
    private  static final int PICK_IMAGE = 1;
    private ProgressBar progressBar3;
    private StorageTask storageTask ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nb_uplaod__admin);


        //Firebase Database
        myRef = FirebaseDatabase.getInstance().getReference("uploadsAdmin");

        mStorageRef = FirebaseStorage.getInstance().getReference("uploadsAdmin");

        //EditText
        name = findViewById(R.id.book_name_admin);



        //button add
        Button button = findViewById(R.id.add_admin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });


        Button uplaod = findViewById(R.id.upload_admin);
        uplaod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (storageTask != null && storageTask.isInProgress()){
                    Toast.makeText(getApplicationContext(),"الملف قيد الرفع",Toast.LENGTH_SHORT).show();
                }else{
                    uploadFile();
                }

            }
        });


        imageAdmin = findViewById(R.id.imageAdmin);
        //TextView
        textView=findViewById(R.id.add_photo_admin);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("  <-- اضغط على زر الاضافة");
            }
        });
        //progressBar3
        progressBar3 = findViewById(R.id.progressBar3);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUD = data.getData();
            Picasso.with(this).load(imageUD).into(imageAdmin);
        }

    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityIfNeeded(intent,PICK_IMAGE);



    }
    private void uploadFile() {
        if (imageUD != null ){
            final StorageReference storageReference = mStorageRef.child( System.currentTimeMillis()
                    +"."+getEX(imageUD));

            storageTask =   storageReference.putFile(imageUD).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot snapshot) {

                    Task<Uri> uriTask = snapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isComplete());
                    imageUD = uriTask.getResult();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar3.setProgress(0);
                        }
                    }, 500);
                    Toast.makeText(getApplicationContext(),"تم تحميل الملف",Toast.LENGTH_SHORT).show();



                    String s = myRef.push().getKey();
                    NB_WorldModel moduel = new NB_WorldModel(s,name.getText().toString().trim()
                            ,imageUD.toString());
                  //  myRef.child(myRef.push().getKey()).setValue(moduel);

                    myRef.child(s).setValue(moduel);
                    progressBar3.setVisibility(View.GONE);
                    Intent intent = new Intent(getApplicationContext(),Container.class);
                    intent.putExtra("KEYNAV","WorldNav");
                    startActivity(intent);





            }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();


                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                               double  progress =(100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                               progressBar3.setProgress((int)progress);
                            progressBar3.setVisibility(View.VISIBLE);
                        }
                    });
        }else{
            Toast.makeText(getApplicationContext(),"الرجاء اضافة صورة",Toast.LENGTH_SHORT).show();
        }
    }




    private String getEX(Uri uri){
        ContentResolver resolver = getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(resolver.getType(uri));

    }

}

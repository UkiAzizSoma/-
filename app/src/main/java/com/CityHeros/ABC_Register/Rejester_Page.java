package com.CityHeros.ABC_Register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.CityHeros.A_CodeForIraq.B_Sucka;
import com.CityHeros.firstProject.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Objects;

public class Rejester_Page extends AppCompatActivity {



    //Edit text s
    private EditText name,email,password,confirm,city,age;

    //button
    Button Rejes ;

    //Type of user






    //FierBase
    private FirebaseAuth myAuth ;
    private FirebaseAuth.AuthStateListener listener;
    private DatabaseReference reference;
    private FirebaseUser user;
    //progress bar
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_rejester__page);

        //For firebase
        myAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");


        //Firebase
        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Intent toIntro = new Intent(Rejester_Page.this, B_Sucka.class);
                    startActivity(toIntro);


                }
            }
        };


        //Action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.makeAccount_text);


        //Edit text connection
        name = findViewById(R.id.name_rj);
        email = findViewById(R.id.email_rj);
        password = findViewById(R.id.pass_word_rj);
        confirm = findViewById(R.id.confirm_password_rj);
        city = findViewById(R.id.city_rj);
        age = findViewById(R.id.age_rj);
        //End of Edit Text

        //Button connection
        Rejes = findViewById(R.id.rj_buuton);

        Rejes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validation(view);

            }
        });
        //End of Button connection


        //ProgressBar
        progressBar = findViewById(R.id.progressBar2);









    }






    //Validation Method
    public void validation(View view){

        progressBar.setVisibility(View.VISIBLE);
        if (checkName(name) && checkedAge(age) && checkEmail(email)  && checkedconfirm(confirm)
                && checkedCity(city) && checkedPassword(password) ) {

            //String time
            String emailEnd = email.getText().toString();
            String passEnd = password.getText().toString();
            //End of Strings


            //Fire Base Auth
            myAuth.createUserWithEmailAndPassword(emailEnd,passEnd).addOnCompleteListener(
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                addUser();
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(),R.string.create_account,Toast.LENGTH_SHORT).show();
                                Intent toIntro = new Intent(Rejester_Page.this, B_Sucka.class);
                                startActivity(toIntro);





                            }else if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(getApplicationContext(),R.string.account_exisit,Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                            }else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(),R.string.check_problem,Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
            );
            //End of Fire base



        }else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void addUser() {
        final String emailEnd = email.getText().toString();
        final String passEnd = password.getText().toString();
        String names = name.getText().toString();
        String ages = age.getText().toString();
        String citys = city.getText().toString();
        String Id = user.getUid();

        UserInformation information = new UserInformation(Id,names,emailEnd,citys,ages,passEnd);




        reference.child(Id).setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),R.string.data_saved,Toast.LENGTH_LONG).show();
                }

            }
        });












    }


    //End of Validation Method

    @Override
    protected void onStart() {
        super.onStart();
        myAuth.addAuthStateListener(listener);
    }




    //Boolean Time
    private boolean checkName(EditText name ){

        String nameIn = name.getText().toString().trim();


        if(!nameIn.isEmpty() && nameIn.matches("^[\\u0621-\\u064Aa-zA-Z\\d\\-_\\s]+$") )
            return true;

        else{
            name.setError("كتابة الاسم بالحروف فقط");
            name.requestFocus();

            return false;
        }

    }

    private boolean checkedPassword(EditText password){

        String pass_wordIn = password.getText().toString().trim();
        if (!pass_wordIn.isEmpty() && pass_wordIn.length() > 4 ){
            return true;

        }else {
            password.setError("كلمة المرور يجب ان تحتوي 4 ارقام");
            password.requestFocus();
            return false;
        }


    }

    private boolean checkedconfirm(EditText confirm){
        String confirmIn = confirm.getText().toString().trim();
        if (!confirmIn.isEmpty() && confirmIn.equals(password.getText().toString()) ){
            return true ;


        }else{
            confirm.setError("يجب ان تكون كلمة المرور متطابقة");
            confirm.requestFocus();
            return false;

        }



    }

    private boolean checkEmail( EditText email ){
        String emailIn = email.getText().toString().trim();





        if (!emailIn.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailIn).matches()) {


            return true;
        }else{

            email.setError("يجب ان يكون البريد الالكتروني موجودا");
            email.requestFocus();

            return false;
        }




    }

    private boolean checkedAge(EditText age){
        String ageIn = age.getText().toString().trim();
        if (!ageIn.isEmpty()&& ageIn.matches("\\d{0,2}(\\.\\d{1,2})?")){
            return true;

        }else {
            age.setError("ادخل عمرك");
            return false;
        }


    }

    private boolean checkedCity(EditText city){
        String cityIn = city.getText().toString().trim();
        if (!cityIn.isEmpty() && cityIn.matches("^[\\u0621-\\u064Aa-zA-Z\\d\\-_\\s]+$")){
            return true ;
        }else{
            city.setError("يجب ان تكون المدينة حروفا فقط");
            return false;
        }
    }
    //Boolean Time End


}




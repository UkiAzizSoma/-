package com.CityHeros.AB_Beginning;
//ImportSection Start fromHere

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



import com.CityHeros.ABC_Register.Rejester_Page;
import com.CityHeros.A_CodeForIraq.B_Sucka;
import com.CityHeros.firstProject.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import java.util.Objects;
//End of Import Section














public class A_Login_form extends AppCompatActivity {



    private static final int RC_SIGN_IN =123 ;
    private  EditText email_parm,pass_word_parm;
    private  ProgressBar progressBar;

    private FirebaseAuth firebaseAuth ;

    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth.AuthStateListener listener;





    //Main method
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_login_form);
        google();


        Objects.requireNonNull(getSupportActionBar()).setTitle("تسجيل الدخول");

        //EditText
        email_parm = findViewById(R.id.email);
        pass_word_parm = findViewById(R.id.pass_word);

        //Button
        Button button = findViewById(R.id.login);
        Button google = findViewById(R.id.googleButton);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();

            }
        });


        //fire base
        firebaseAuth=FirebaseAuth.getInstance();

        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    finish();
                    Intent intent = new Intent(getApplicationContext(), B_Sucka.class);

                    startActivity(intent);


                }
            }
        };





        //TextView
        TextView createAccount;
        createAccount = findViewById(R.id.make_anAccount);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createAnAccount = new Intent(A_Login_form.this , Rejester_Page.class);
                startActivity(createAnAccount);
            }
        });



        //passWord TextView
        TextView password_restore;
        password_restore = findViewById(R.id.password_restore);
        password_restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText resetMail = new EditText(view.getContext());




                final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("استعادة كلمة المرور");
                builder.setMessage("ضع عنوان البريد المراد استعادة رمزه");
                builder.setView(resetMail);


                builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String mail = resetMail.getText().toString();

                        if (!mail.isEmpty() &&  Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
                            firebaseAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext()," تم ارسال الرابط الى بريدك لأستعادة الرمز",Toast.LENGTH_SHORT).show();
                                }
                            });

                        }else {
                            Toast.makeText(getApplicationContext(),"حاول مرة اخرى",Toast.LENGTH_SHORT).show();

                        }
                    }
                });

                builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            }
        });




        //progressBar
        progressBar = findViewById(R.id.progressBar);

        //Input








        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EndGame(view);



            }
        });

    }










    //Small method of val (firstPart)
    private boolean checkEmail(EditText email_parm){
        String emailFire = email_parm.getText().toString().trim();

        if (!emailFire.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailFire).matches()){
            return true;
        }else{
            email_parm.setError(" يجب ان يكون البريد الالكتروني صحيح ولا يجب ترك الحقل فارغ");
            email_parm.requestFocus();
            return false;
        }


    }
    private boolean checkpassWord(EditText pass_word_parm){
        String passWordFire = pass_word_parm.getText().toString().trim();
        if (!passWordFire.isEmpty()){
            return true;
        }else{
            pass_word_parm.setError(" كلمة المرور غير صحيحة");
            pass_word_parm.requestFocus();
            return false;
        }


    }
    //End of val




    //button ClickEvent
    public void EndGame(View view){

        progressBar.setVisibility(View.VISIBLE);

        if (checkEmail(email_parm) && checkpassWord(pass_word_parm)){
            String E = email_parm.getText().toString();
            String P = pass_word_parm.getText().toString();

            firebaseAuth.signInWithEmailAndPassword(E,P).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {



                    if (task.isSuccessful()){


                        Toast.makeText(getApplicationContext(),"تم تسجيل الدخول بنجاح",Toast.LENGTH_SHORT).show();


                        Intent  toIntro = new Intent(A_Login_form.this , B_Sucka.class);
                        startActivity(toIntro);



                    }else if (task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),R.string.account_exisit,Toast.LENGTH_SHORT).show();

                    }else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),R.string.check_problem,Toast.LENGTH_SHORT).show();

                    }
                }
            });

        }else {
            progressBar.setVisibility(View.GONE);
        }


    }









    //google things
    private void google(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.de))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }
    private void signIn() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityIfNeeded(signInIntent, RC_SIGN_IN);


    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN ) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);



            try {
                // Google Sign In was successful, authenticate with Firebase




                GoogleSignInAccount account = task.getResult(ApiException.class);
                assert account != null;
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException ignored) {

            }
        }


    }





    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {


                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(),"تم تسجيل الدخول بنجاح",Toast.LENGTH_SHORT).show();


                            Intent intent = new Intent(A_Login_form.this , B_Sucka.class);
                            intent.putExtra("GOOGLEKEY","GOOGLE");
                            startActivity(intent);

                        }

                        else {


                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(),"حاول مجددا",Toast.LENGTH_SHORT).show();

                        }


                        // ...
                    }
                });


    }
    //google things end





    @Override
    public void onBackPressed() {




        //super.onBackPressed();



    }
    @Override
    protected void onStart() {
        super.onStart();

            firebaseAuth.addAuthStateListener(listener);




    }

    @Override
    protected void onStop() {
        super.onStop();
        if (listener != null){
            firebaseAuth.removeAuthStateListener(listener);
        }
    }
    //End of class
}







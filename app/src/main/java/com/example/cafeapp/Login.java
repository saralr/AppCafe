package com.example.cafeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    public EditText emailId, password;
    Button btnSignIn;
    TextView tvSignUp;
    TextView ForgotPassword;
    CheckBox showpass;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editText);
        emailId.setAutofillHints(View.AUTOFILL_HINT_EMAIL_ADDRESS);
        password = findViewById(R.id.editText5);
        password.setAutofillHints(View.AUTOFILL_HINT_PASSWORD);
        btnSignIn = findViewById(R.id.button3);
        tvSignUp = findViewById(R.id.textView);
        ForgotPassword = findViewById(R.id.Forgotpass);
        showpass = findViewById(R.id.showpass);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser != null){
                    Toast.makeText(Login.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, ConnexionScreen.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(Login.this, "Please log in", Toast.LENGTH_SHORT).show();
                }
            }
        };


        showpass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if (email.isEmpty()){
                    emailId.setError("Please enter a valid email");
                    emailId.requestFocus();
                }
                else if(pwd.isEmpty()){
                    password.setError("Please enter a valid password");
                    password.requestFocus();
                }
                else if (email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(Login.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && pwd.isEmpty()))
                {
                    //Check if the task is completed or not
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Login.this, "Login Error! Please try again", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent intHome = new Intent(Login.this, ConnexionScreen.class);
                                startActivity(intHome);
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(Login.this, "Error Occured", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Forgot Password
        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intForgotPass =  new Intent(Login.this, ForgotPassword.class);
               startActivity(intForgotPass);
            }
        });

        //Not registred yet? Sign up here!
        tvSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intSignUp = new Intent(Login.this, HomeActivity.class);
                startActivity(intSignUp);
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}

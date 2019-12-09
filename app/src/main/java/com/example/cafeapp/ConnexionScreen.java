package com.example.cafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ConnexionScreen extends AppCompatActivity {

    Button btnLogout;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion_screen);

        btnLogout = findViewById(R.id.logOut);

        btnLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FirebaseAuth.getInstance().signOut();
                Intent intToLogin = new Intent(ConnexionScreen.this, Login.class);
                onBackPressed();
                intToLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //to clear all previous activities
                startActivity(intToLogin);
            }
        });
    }
    /*@Override
    public void onBackPressed(){
        Toast.makeText(ConnexionScreen.this, "Please Login again!", Toast.LENGTH_SHORT).show();

    }*/
}

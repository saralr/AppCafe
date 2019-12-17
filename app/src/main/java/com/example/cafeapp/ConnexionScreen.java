package com.example.cafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConnexionScreen extends AppCompatActivity {

    Button btnLogout;
    Button btnPlus;
    Button btnMoins;
    RecyclerView mRecyclerView;
    FirebaseAuth mFirebaseAuth;
    DatabaseReference mFireBaseRef;
    TextView aNumber;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private String mail;
    private String key;
    private User aUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion_screen);
        aNumber = findViewById(R.id.textView3);
        FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("mail").equalTo(FirebaseAuth.getInstance().getCurrentUser().getEmail())
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        aUser = dataSnapshot.getValue(User.class);
                        aNumber.setText(aUser.getNumber().toString());
                    }

                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        aUser = dataSnapshot.getValue(User.class);
                        aNumber.setText(aUser.getNumber().toString());
                    }


                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                    }

                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    }

                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

        btnLogout = findViewById(R.id.logOut);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToLogin = new Intent(ConnexionScreen.this, Login.class);
                onBackPressed();
                intToLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //to clear all previous activities
                startActivity(intToLogin);
            }
        });
        btnPlus = findViewById(R.id.button);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aUser.setNumber(aUser.getNumber() + 1);
                FirebaseDatabase.getInstance().getReference().child("Users").push().setValue(aUser);
            }
        });

        btnMoins = findViewById(R.id.button2);
        btnMoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aUser.setNumber(aUser.getNumber() - 1);
                FirebaseDatabase.getInstance().getReference().child("Users").push().setValue(aUser);
            }
        });
    }
}
    /*@Override
    public void onBackPressed(){
        Toast.makeText(ConnexionScreen.this, "Please Login again!", Toast.LENGTH_SHORT).show();

    }*/


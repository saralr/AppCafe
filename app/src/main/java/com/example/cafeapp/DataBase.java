package com.example.cafeapp;

import com.example.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataBase {

    private static FirebaseUser getCurrentUser()
    {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public static void insererUtilisateur(String nom)
    {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.push().setValue(getCurrentUser().getEmail());
    }
    public static void parier(String paye)
    {
        int nbParis;
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        //nbParis = db.child(getCurrentUser().getEmail()).child(paye).updateChildren();
        db.push().setValue(getCurrentUser().getDisplayName());
    }
    public static void decrementer(User aUser)
    {

    }
    public static void getListeParis()
    {
        FirebaseDatabase.getInstance().getReference().child(getCurrentUser().getEmail())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            System.out.println(snapshot.getKey());
                            System.out.println(snapshot.getValue());
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }
}

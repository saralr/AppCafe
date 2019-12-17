package com.example.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    private String mail;
    private String nom;
    private Integer number;
    private Map<User, Integer> paris;


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Map<User, Integer> getParis() {
        return paris;
    }

    public void incrementeNbParis(User aUser) {
        paris.put(aUser, paris.getOrDefault(aUser, 0) + 1);
    }

    public void decrementeNbParis(User aUser) {
        if(paris.get(aUser)> 0)
            paris.put(aUser, paris.get(aUser) - 1);
    }

    public User()
    {
        paris = new HashMap<User, Integer>();
    }


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}

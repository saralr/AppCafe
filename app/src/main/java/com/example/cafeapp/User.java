package com.example.cafeapp;

import com.example.api.UserHelper;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User {

    private String uid;
    private String username;
    private List<String> cafes;

    public User() { }

    public User(String uid, String username) {
        this.uid = uid;
        this.username = username;
        this.cafes = new ArrayList<String>();
    }

    // --- GETTERS ---
    public String getUid() { return uid; }
    public String getUsername() { return username; }
    public List getIsMentor() { return cafes; }

    // --- SETTERS ---
    public void setUsername(String username) { this.username = username; }
    public void setUid(String uid) { this.uid = uid; }
    public void setIsMentor(List<String> lesCafes) { cafes = lesCafes; }

}
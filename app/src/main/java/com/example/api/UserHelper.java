package com.example.api;

import android.annotation.SuppressLint;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.example.cafeapp.User;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class UserHelper {

    private static final String COLLECTION_NAME = "users";

    // --- COLLECTION REFERENCE ---

    public static CollectionReference getUsersCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    public static String getAllUsers(){
        String s = "";
        QuerySnapshot querySnapshot = FirebaseFirestore.getInstance().collection("users").get().getResult();
        List<DocumentSnapshot> documents = querySnapshot.getDocuments();
        for (DocumentSnapshot document : documents) {
            s += ", " + document.getString("id");
        }
        return s;
    }

    // --- CREATE ---

    public static Task<Void> createUser(String uid, String username, String urlPicture) {
        @SuppressLint("RestrictedApi")
        User userToCreate = new User(uid, username);
        return UserHelper.getUsersCollection().document(uid).set(userToCreate);
    }

    // --- GET ---

    public static Task<DocumentSnapshot> getUser(String uid){
        return UserHelper.getUsersCollection().document(uid).get();
    }

    // --- UPDATE ---

    public static Task<Void> updateUsername(String username, String uid) {
        return UserHelper.getUsersCollection().document(uid).update("username", username);
    }

    public static Task<Void> updateIsMentor(String uid, List<String> cafes) {
        return UserHelper.getUsersCollection().document(uid).update("isMentor", cafes);
    }

    // --- DELETE ---

    public static Task<Void> deleteUser(String uid) {
        return UserHelper.getUsersCollection().document(uid).delete();
    }

}
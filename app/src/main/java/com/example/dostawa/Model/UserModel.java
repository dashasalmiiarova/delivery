package com.example.dostawa.Model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserModel {
    private String uid, email;
    public UserModel() {
    }

    public UserModel(String uid, String email) {
        this.uid = uid;
        this.email = email;
//        this.address = address;
    }

    public String getUid(String uid) {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail(String email) {
//        email = user.getEmail();
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
}

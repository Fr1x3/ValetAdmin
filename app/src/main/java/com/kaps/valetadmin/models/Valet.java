package com.kaps.valetadmin.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Valet {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("first_name")
    @Expose
    private String fistName;

    @SerializedName("last_name")
    @Expose
    private String lastName;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("type")
    @Expose
    private String role;

    public String getRole() {
        return role;
    }



    public Valet(String fistName, String lastName, String email, String password, String role) {
        this.fistName = fistName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getFistName() {
        return fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getId(){ return id; }
}

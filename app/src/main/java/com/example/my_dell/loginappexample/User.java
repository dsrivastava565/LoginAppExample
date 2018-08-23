package com.example.my_dell.loginappexample;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("response")
    private String ussresponse;
    @SerializedName("name")
    private String Name;

    public String getResponse() {
        return ussresponse;
    }

    public String getName() {
        return Name;
    }
}

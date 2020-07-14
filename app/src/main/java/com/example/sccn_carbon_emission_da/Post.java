package com.example.sccn_carbon_emission_da;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class Post {

    private String error,message;
    private JsonObject user;
    private JsonArray data;

    @SerializedName("body")


    public String getError() {
        return error;
    }
    public String getMessage() {
        return message;
    }

    public JsonObject getuser() {
        return user;
    }
    public JsonArray getdata(){
        return data;
    }
}
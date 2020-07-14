package com.example.sccn_carbon_emission_da;

import com.google.gson.JsonArray;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface jsonPlaceHolderApi {


    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @POST("posts")
    Call<JsonArray> createPost();
}
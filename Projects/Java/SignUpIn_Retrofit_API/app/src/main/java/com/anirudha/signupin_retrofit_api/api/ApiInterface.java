package com.anirudha.signupin_retrofit_api.api;

import com.anirudha.signupin_retrofit_api.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("your_php_file.php?apicall=signup")
    Call<UserResponse> signUp(
            @Field("username") String username,
            @Field("mobile") String mobile,
            @Field("email") String email,
            @Field("address") String address,
            @Field("passwd") String password
    );

    @FormUrlEncoded
    @POST("your_php_file.php?apicall=login")
    Call<UserResponse> login(
            @Field("mobile") String mobile,
            @Field("passwd") String password
    );
}


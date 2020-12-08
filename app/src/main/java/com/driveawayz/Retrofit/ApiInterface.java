package com.driveawayz.Retrofit;

import com.driveawayz.SignUp.response.SignUp1Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("/api/v1/auth/signup")
    Call<SignUp1Response> signUp (
        @Field("name") String name,
        @Field("email") String email,
        @Field("password") String password,
        @Field("dateOfBirth") String dateOfBirth,
        @Field("street") String street,
        @Field("address") String address,
        @Field("phoneNumber") String phoneNumber
    );

}

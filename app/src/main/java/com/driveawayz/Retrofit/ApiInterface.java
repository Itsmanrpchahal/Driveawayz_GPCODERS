package com.driveawayz.Retrofit;

import com.driveawayz.OTPScreen.response.NumberVerifyResponse;
import com.driveawayz.SignUp.response.SignUp1Response;
import com.driveawayz.SignUp.signupphone.response.SignUpPhoneNoResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("/api/v1/auth/sendMessage")
    Call<SignUpPhoneNoResponse> signUpPhone (
            @Field("mobileNumber") String mobileNumber,
            @Field("channel") String channel
    );

    @FormUrlEncoded
    @POST("/api/v1/auth/verification")
    Call<NumberVerifyResponse> verifyPhone (
            @Field("mobileNumber") String mobileNumber,
            @Field("code") String code
    );


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

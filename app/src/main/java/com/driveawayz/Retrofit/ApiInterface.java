package com.driveawayz.Retrofit;

import com.driveawayz.Login.response.LoginResponse;
import com.driveawayz.OTPScreen.response.NumberVerifyResponse;
import com.driveawayz.SignUp.response.SignUp1Response;
import com.driveawayz.SignUp.signupphone.response.AddVehiclesResponse;
import com.driveawayz.SignUp.signupphone.response.SignUp1User;
import com.driveawayz.SignUp.signupphone.response.SignUpPhoneNoResponse;
import com.driveawayz.SignUp.signupphone.response.UpdateAddress;
import com.driveawayz.splashScreen.MeResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
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
    Call<SignUp1User> signUp (
        @Field("name") String name,
        @Field("email") String email,
        @Field("password") String password,
        @Field("dateOfBirth") String dateOfBirth,
        @Field("phoneNumber") String phoneNumber,
        @Field("phoneVerified") Boolean phoneVerified
    );

    @FormUrlEncoded
    @POST("/api/v1/address")
    Call<UpdateAddress> updateAddress (
            @Header("Authorization") String token,
            @Field("street") String street,
            @Field("address") String address
    );

    @FormUrlEncoded
    @POST("/api/v1/auth/login")
    Call<LoginResponse> login (
      @Field("email") String email,
      @Field("password") String password
    );

    @GET("/api/v1/user/me")
    Call<MeResponse> me (
      @Header("Authorization") String token
    );

    @FormUrlEncoded
    @POST("/api/v1/vehicle")
    Call<AddVehiclesResponse> addVehicle (
            @Header("Authorization") String token,
            @Field("make") String make,
            @Field("type") String type,
            @Field("year") String year,
            @Field("transmission") String transmission,
            @Field("licensePlate") String licensePlate,
            @Field("state") String state
    );

}

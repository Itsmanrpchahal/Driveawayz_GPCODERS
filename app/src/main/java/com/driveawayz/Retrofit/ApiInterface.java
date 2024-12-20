package com.driveawayz.Retrofit;

import com.driveawayz.Login.response.ForgotResponse;
import com.driveawayz.Login.response.LoginResponse;
import com.driveawayz.OTPScreen.response.NumberVerifyResponse;
import com.driveawayz.SignUp.signupphone.response.AddNewAddressResponse;
import com.driveawayz.SignUp.signupphone.response.AddVehiclesResponse;
import com.driveawayz.SignUp.signupphone.response.SignUp1user;
import com.driveawayz.SignUp.signupphone.response.SignUpPhoneNoResponse;
import com.driveawayz.dashboard.DashboardResponse;
import com.driveawayz.dashboard.homeFrag.response.BookRide;
import com.driveawayz.dashboard.homeFrag.response.MyVehicleRateResponse;
import com.driveawayz.dashboard.mydriveFrag.CompleteRideResponse;
import com.driveawayz.dashboard.mydriveFrag.DeleteRideResponse;
import com.driveawayz.dashboard.mydriveFrag.MyDrivesResponse;
import com.driveawayz.dashboard.notificationFrag.ClearNotificationsResponse;
import com.driveawayz.dashboard.notificationFrag.NotificationsResponse;
import com.driveawayz.dashboard.response.FeedbackResponse;
import com.driveawayz.dashboard.setiingFrag.response.ChangePasswordResponse;
import com.driveawayz.dashboard.setiingFrag.response.DeleteAddressResponse;
import com.driveawayz.dashboard.setiingFrag.response.DeleteVehicleReponse;
import com.driveawayz.dashboard.setiingFrag.response.MyAddessesResponse;
import com.driveawayz.dashboard.setiingFrag.response.MyVehiclesResponse;
import com.driveawayz.dashboard.setiingFrag.response.UpdateAddressResponse;
import com.driveawayz.dashboard.setiingFrag.response.UpdateProfileResponse;
import com.driveawayz.dashboard.setiingFrag.response.UploadImageResponse;
import com.driveawayz.dashboard.supportFrag.SupportResponse;
import com.driveawayz.splashScreen.MeResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("/api/v1/auth/sendMessage")
    Call<SignUpPhoneNoResponse> signUpPhone(
            @Field("mobileNumber") String mobileNumber,
            @Field("channel") String channel
    );

    @FormUrlEncoded
    @POST("/api/v1/auth/verification")
    Call<NumberVerifyResponse> verifyPhone(
            @Field("mobileNumber") String mobileNumber,
            @Field("code") String code
    );


    @FormUrlEncoded
    @POST("/api/v1/auth/signup")
    Call<SignUp1user> signUp(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("dateOfBirth") String dateOfBirth,
            @Field("phoneNumber") String phoneNumber,
            @Field("phoneVerified") Boolean phoneVerified
    );

    @FormUrlEncoded
    @POST("/api/v1/address")
    Call<AddNewAddressResponse> AddNewAddress(
            @Header("Authorization") String token,
            @Field("street") String street,
            @Field("address") String address
    );

    @FormUrlEncoded
    @POST("/api/v1/auth/login")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("/api/v1/user/me")
    Call<MeResponse> me(
            @Header("Authorization") String token
    );

    @FormUrlEncoded
    @PUT("/api/v1/user/me")
    Call<UpdateProfileResponse> updateProfile(
            @Header("Authorization") String token,
            @Field("name") String name,
            @Field("email") String email,
            @Field("dateOfBirth") String dateOfBirth
    );

    @FormUrlEncoded
    @POST("/api/v1/vehicle")
    Call<AddVehiclesResponse> addVehicle(
            @Header("Authorization") String token,
            @Field("make") String make,
            @Field("type") String type,
            @Field("year") String year,
            @Field("transmission") String transmission,
            @Field("licensePlate") String licensePlate,
            @Field("state") String state
    );

    @GET("/api/v1/vehicles")
    Call<List<MyVehiclesResponse>> myVehicles(
            @Header("Authorization") String token
    );

    @GET("/api/v1/addresses")
    Call<List<MyAddessesResponse>> myAddresses(
            @Header("Authorization") String token
    );

    @FormUrlEncoded
    @PUT("/api/v1/updateAddress/{input}")
    Call<UpdateAddressResponse> updateAddress(
            @Header("Authorization") String token,
            @Path("input") String id,
            @Field("street") String street,
            @Field("address") String address
    );

    @GET("/api/v1/vehicle/{input}")
    Call<MyVehicleRateResponse> rate(
            @Header("Authorization") String token,
            @Path("input") String id
    );

    @FormUrlEncoded
    @POST("/api/v1/ride")
    Call<BookRide> bookRide(
            @Header("Authorization") String token,
            @Field("pickLocation") String pickLocation,
            @Field("destination") String destination,
            @Field("destinationLatLong") String destinationLatLong,
            @Field("pickUpLatLong") String pickUpLatLong,
            @Field("numberOfGuests") String numberOfGuests,
            @Field("numberOfHours") String numberOfHours,
            @Field("pickDt") String pickDate,
            @Field("vehicleId") Integer vehicleId,
            @Field("rideCharge") String rideCharge,
            @Field("distance") String distance
    );

    @GET("/api/v1/myRides")
    Call<MyDrivesResponse> myDrives(
            @Header("Authorization") String token
    );

    @DELETE("/api/v1/address/{input}")
    Call<DeleteAddressResponse> deleteAddress(
            @Header("Authorization") String token,
            @Path("input") String id
    );


    @DELETE("/api/v1/vehicle/{input}")
    Call<DeleteVehicleReponse> deleteVehicle(
            @Header("Authorization") String token,
            @Path("input") String id
    );

    @Multipart
    @POST("/api/v1/user/upload")
    Call<UploadImageResponse> uploadImage(
            @Header("Authorization") String token,
            @Part MultipartBody.Part image);

    @DELETE("/api/v1/ride/{input}")
    Call<DeleteRideResponse> deleteRide(
            @Header("Authorization") String token,
            @Path("input") String id
    );

    @FormUrlEncoded
    @POST("/api/v1/charge-manual")
    Call<List<CompleteRideResponse>> completeRide(
            @Header("Authorization") String token,
            @Field("amount") Double amount,
            @Field("rideId") String id
    );

    @FormUrlEncoded
    @POST("/api/v1/user/forgotPass")
    Call<ForgotResponse> forgotpassword(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("/api/v1/user/changePass")
    Call<ChangePasswordResponse> changePassword(
            @Header("Authorization") String token,
            @Field("currentPass") String currentPass,
            @Field("newPass") String newPass
    );

    @FormUrlEncoded
    @POST("/api/v1/feedback")
    Call<FeedbackResponse> feedback(
            @Header("Authorization") String token,
            @Field("name") String name,
            @Field("description") String description
    );

    @FormUrlEncoded
    @POST("/api/v1/support")
    Call<SupportResponse> support(

            @Header("Authorization") String token,
            @Field("question") String question,
            @Field("description") String description

    );

    @POST("/api/v1/user/dashboard")
    Call<DashboardResponse> dashboard(
            @Header("Authorization") String token
    );
    @GET("/api/v1/notifications")
    Call<List<NotificationsResponse>> notifications(
            @Header("Authorization") String token
    );

    @PUT("/api/v1/notifications")
    Call<ClearNotificationsResponse> clearNotifications(
            @Header("Authorization") String token
    );

}

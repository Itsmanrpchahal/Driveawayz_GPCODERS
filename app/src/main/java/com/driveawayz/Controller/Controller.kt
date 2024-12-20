package com.driveawayz.Controller

import com.driveawayz.Login.response.ForgotResponse
import com.driveawayz.Login.response.LoginResponse
import com.driveawayz.OTPScreen.response.NumberVerifyResponse
import com.driveawayz.Retrofit.WebAPI
import com.driveawayz.SignUp.signupphone.response.AddVehiclesResponse
import com.driveawayz.SignUp.signupphone.response.SignUpPhoneNoResponse
import com.driveawayz.SignUp.signupphone.response.AddNewAddressResponse
import com.driveawayz.SignUp.signupphone.response.SignUp1user
import com.driveawayz.dashboard.DashboardResponse
import com.driveawayz.dashboard.homeFrag.response.BookRide
import com.driveawayz.dashboard.homeFrag.response.MyVehicleRateResponse
import com.driveawayz.dashboard.mydriveFrag.CompleteRideResponse
import com.driveawayz.dashboard.mydriveFrag.DeleteRideResponse
import com.driveawayz.dashboard.mydriveFrag.MyDrivesResponse
import com.driveawayz.dashboard.notificationFrag.ClearNotificationsResponse
import com.driveawayz.dashboard.notificationFrag.NotificationsResponse
import com.driveawayz.dashboard.response.FeedbackResponse
import com.driveawayz.dashboard.setiingFrag.response.*
import com.driveawayz.dashboard.supportFrag.SupportResponse
import com.driveawayz.splashScreen.MeResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Controller {

    var webAPI: WebAPI? = null
    var signUp1API: SignUp1API? = null
    var signUpPhoneAPI: SignUpPhoneAPI? = null
    var phoneNumberAPI: VerifyPhoneAPI? = null
    var loginAPI: LoginAPI? = null
    var meAPI: MeAPI? = null
    var addNewAddressAPI: AddNewAddress? = null
    var addVehiclesAPI: AddVehiclesAPI? = null
    var myVehiclesAPI: MyVehiclesAPI? = null
    var myAdderessAPI: MyAdderessAPI? = null
    var updateAddressAPI: UpdateAddressAPI? = null
    var rateAPI: RateAPI? = null
    var bookRideAPI: BookRideAPI? = null
    var myDrivesAPI: MyDrivesAPI? = null
    var deleteAddressAPI: DeleteAddressAPI? = null
    var deleteVehiclesAPI: DeleteVehicleAPI? = null
    var uploadImageAPI: UploadImageAPI? = null
    var updateProfileAPI : UpdateProfileAPI? = null
    var deleteRideAPI : DeleteRideAPI? = null
    var completeRideAPI : CompleteRideAPI? = null
    var forgotPasswordAPI: ForgotPasswordAPI? = null
    var changePasswordAPI : ChangePasswordAPI? = null
    var feedbackAPI: FeedbackAPI? = null
    var supportAPI : SupportAPI? = null
    var dashboardAPI:DashboardAPI? = null
    var notificationsAPI:NotificationsAPI? = null
    var clearNotificationsAPI:ClearNotificationsAPI?=null

    fun Controller(signUpPhone: SignUpPhoneAPI) {
        signUpPhoneAPI = signUpPhone
        webAPI = WebAPI()
    }

    fun Controller(signUpPhone: SignUpPhoneAPI, verifyPhone: VerifyPhoneAPI) {
        signUpPhoneAPI = signUpPhone
        phoneNumberAPI = verifyPhone
        webAPI = WebAPI()
    }

    fun Controller(signUp1: SignUp1API, addNewAddress: AddNewAddress) {
        signUp1API = signUp1
        addNewAddressAPI = addNewAddress
        webAPI = WebAPI()
    }

    fun Controller(login: LoginAPI, me: MeAPI,forgotPassword: ForgotPasswordAPI) {
        loginAPI = login
        meAPI = me
        forgotPasswordAPI = forgotPassword
        webAPI = WebAPI()
    }

    fun Controller(me: MeAPI) {
        meAPI = me
        webAPI = WebAPI()
    }

    fun Controller(addVehicle: AddVehiclesAPI) {
        addVehiclesAPI = addVehicle
        webAPI = WebAPI()
    }

    fun Controller(myDrives: MyDrivesAPI,deleteRide : DeleteRideAPI,completeRide : CompleteRideAPI) {
        myDrivesAPI = myDrives
        deleteRideAPI = deleteRide
        completeRideAPI = completeRide
        webAPI = WebAPI()
    }

    fun Controller(  myAdderess: MyAdderessAPI)
    {
        myAdderessAPI = myAdderess
        webAPI = WebAPI()
    }

    fun Controller(
        myVehicles: MyVehiclesAPI,
        addVehicle: AddVehiclesAPI,
        me: MeAPI,
        myAdderess: MyAdderessAPI,
        addNewAddress: AddNewAddress,
        updateAdrress: UpdateAddressAPI,
        deleteAddress: DeleteAddressAPI,
        deleteVehicle: DeleteVehicleAPI,
        uploadImage: UploadImageAPI,
        updateProfile : UpdateProfileAPI,
        changePassword : ChangePasswordAPI
    ) {
        myVehiclesAPI = myVehicles
        addVehiclesAPI = addVehicle
        meAPI = me
        myAdderessAPI = myAdderess
        addNewAddressAPI = addNewAddress
        updateAddressAPI = updateAdrress
        deleteAddressAPI = deleteAddress
        deleteVehiclesAPI = deleteVehicle
        uploadImageAPI = uploadImage
        updateProfileAPI = updateProfile
        changePasswordAPI = changePassword
        webAPI = WebAPI()
    }

    fun Controller(myVehicles: MyVehiclesAPI, rate: RateAPI, bookRide: BookRideAPI) {
        myVehiclesAPI = myVehicles
        rateAPI = rate
        bookRideAPI = bookRide
        webAPI = WebAPI()
    }

    fun Controller(feedback: FeedbackAPI,dashboard: DashboardAPI)
    {
        feedbackAPI = feedback
        dashboardAPI = dashboard
        webAPI = WebAPI()
    }

    fun Controller(support: SupportAPI)
    {
        supportAPI = support
        webAPI = WebAPI()
    }

    fun Controller(notifications: NotificationsAPI,clearNotifications: ClearNotificationsAPI)
    {
        notificationsAPI = notifications
        clearNotificationsAPI = clearNotifications
        webAPI = WebAPI()
    }

    fun SignUpPhone(mobileNumber: String, channel: String) {
        webAPI?.api?.signUpPhone(mobileNumber, channel)
            ?.enqueue(object : Callback<SignUpPhoneNoResponse> {
                override fun onResponse(
                    call: Call<SignUpPhoneNoResponse>,
                    response: Response<SignUpPhoneNoResponse>
                ) {
                    signUpPhoneAPI?.onSignUpPhoneSuccess(response)
                }

                override fun onFailure(call: Call<SignUpPhoneNoResponse>, t: Throwable) {
                    signUpPhoneAPI?.onError(t.message.toString())
                }

            })
    }

    fun VerifyPhone(mobileNumber: String, code: String) {
        webAPI?.api?.verifyPhone(mobileNumber, code)
            ?.enqueue(object : Callback<NumberVerifyResponse> {
                override fun onResponse(
                    call: Call<NumberVerifyResponse>,
                    response: Response<NumberVerifyResponse>
                ) {
                    phoneNumberAPI?.onVerifyPhoneSuccess(response)
                }

                override fun onFailure(call: Call<NumberVerifyResponse>, t: Throwable) {
                    phoneNumberAPI?.onError(t.localizedMessage)
                }

            })
    }

    fun SignUp1(
        name: String,
        email: String,
        password: String,
        dateOfBirth: String,
        phoneNumber: String,
        phoneVerified: Boolean
    ) {
        webAPI?.api?.signUp(name, email, password, dateOfBirth, phoneNumber, phoneVerified)
            ?.enqueue(object : Callback<SignUp1user> {
                override fun onResponse(
                    call: Call<SignUp1user>,
                    response: Response<SignUp1user>
                ) {
                    signUp1API?.onSignUpSuccess(response)
                }

                override fun onFailure(call: Call<SignUp1user>, t: Throwable) {
                    t.message?.let { signUp1API?.onError(it) }
                }

            })
    }

    fun AddNewAddress(token: String, street: String, address: String) {
        webAPI?.api?.AddNewAddress(token, street, address)
            ?.enqueue(object : Callback<AddNewAddressResponse> {
                override fun onResponse(
                    call: Call<AddNewAddressResponse>,
                    response: Response<AddNewAddressResponse>
                ) {
                    addNewAddressAPI?.onUpdateAddress(response)
                }

                override fun onFailure(call: Call<AddNewAddressResponse>, t: Throwable) {
                    addNewAddressAPI?.onError(t.message!!)
                }

            })
    }

    fun Login(email: String, password: String) {
        webAPI?.api?.login(email, password)?.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                loginAPI?.oLoginSucceess(response)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                t.message?.let { loginAPI?.onError(it) }
            }

        })
    }

    fun Me(token: String) {
        webAPI?.api?.me(token)?.enqueue(object : Callback<MeResponse> {
            override fun onResponse(call: Call<MeResponse>, response: Response<MeResponse>) {
                meAPI?.onMeSuccess(response)
            }

            override fun onFailure(call: Call<MeResponse>, t: Throwable) {
                t.message?.let { meAPI?.onError(it) }
            }

        })
    }

    fun AddVehicle(
        token: String,
        make: String,
        type: String,
        year: String,
        transmission: String,
        licensePlate: String,
        state: String
    ) {
        webAPI?.api?.addVehicle(token, make, type, year, transmission, licensePlate, state)
            ?.enqueue(object : Callback<AddVehiclesResponse> {
                override fun onResponse(
                    call: Call<AddVehiclesResponse>,
                    response: Response<AddVehiclesResponse>
                ) {
                    addVehiclesAPI?.onAddVehicleSuccess(response)
                }

                override fun onFailure(call: Call<AddVehiclesResponse>, t: Throwable) {
                    addVehiclesAPI?.onError(t.message!!)
                }

            })
    }

    fun MyVehicles(token: String) {
        webAPI?.api?.myVehicles(token)?.enqueue(object : Callback<List<MyVehiclesResponse>> {
            override fun onResponse(
                call: Call<List<MyVehiclesResponse>>,
                response: Response<List<MyVehiclesResponse>>
            ) {
                myVehiclesAPI?.onMyVehiclesSuccess(response)
            }

            override fun onFailure(call: Call<List<MyVehiclesResponse>>, t: Throwable) {
                myVehiclesAPI?.onError(t.message!!)
            }

        })
    }

    fun MyAddresss(token: String) {
        webAPI?.api?.myAddresses(token)?.enqueue(object : Callback<List<MyAddessesResponse>> {
            override fun onResponse(
                call: Call<List<MyAddessesResponse>>,
                response: Response<List<MyAddessesResponse>>
            ) {
                myAdderessAPI?.onMyAddressSuccess(response)
            }

            override fun onFailure(call: Call<List<MyAddessesResponse>>, t: Throwable) {
                myAdderessAPI?.onError(t.message.toString())
            }

        });
    }

    fun UpdateAddress(token: String, id: String, street: String, address: String) {
        webAPI?.api?.updateAddress(token, id, street, address)
            ?.enqueue(object : Callback<UpdateAddressResponse> {
                override fun onResponse(
                    call: Call<UpdateAddressResponse>,
                    response: Response<UpdateAddressResponse>
                ) {
                    updateAddressAPI?.onUpdateAddressSuccess(response)
                }

                override fun onFailure(call: Call<UpdateAddressResponse>, t: Throwable) {
                    updateAddressAPI?.onError(t.message!!)
                }

            })
    }

    fun Rate(token: String, id: String) {
        webAPI?.api?.rate(token, id)?.enqueue(object : Callback<MyVehicleRateResponse> {
            override fun onResponse(
                call: Call<MyVehicleRateResponse>,
                response: Response<MyVehicleRateResponse>
            ) {
                rateAPI?.onRateSuccess(response)
            }

            override fun onFailure(call: Call<MyVehicleRateResponse>, t: Throwable) {
                rateAPI?.onError(t.message!!)
            }

        })
    }

    fun RideBook(
        token: String,
        pickLocation: String,
        destination: String,
        destinationLatLong: String,
        pickUpLatLong: String,
        numberOfGuests: String,
        numberOfHours: String,
        pickDate: String,
        vehicleId: Int,
        rideCharge: String,
        distance:String
    ) {
        webAPI?.api?.bookRide(
            token,
            pickLocation,
            destination,
            destinationLatLong,
            pickUpLatLong,
            numberOfGuests,
            numberOfHours,
            pickDate,
            vehicleId,
            rideCharge,
            distance
        )?.enqueue(object : Callback<BookRide> {
            override fun onResponse(call: Call<BookRide>, response: Response<BookRide>) {
                bookRideAPI?.onBookRideSuccess(response)
            }

            override fun onFailure(call: Call<BookRide>, t: Throwable) {
                bookRideAPI?.onError(t.message!!)
            }

        })
    }

    fun MyDrives(token: String) {
        webAPI?.api?.myDrives(token)?.enqueue(object : Callback<MyDrivesResponse> {
            override fun onResponse(
                call: Call<MyDrivesResponse>,
                response: Response<MyDrivesResponse>
            ) {
                myDrivesAPI?.onMyDrivesSuccess(response)
            }

            override fun onFailure(call: Call<MyDrivesResponse>, t: Throwable) {
                myDrivesAPI?.onError(t.message!!)
            }

        })
    }

    fun DeleteAddress(token: String, id: String) {
        webAPI?.api?.deleteAddress(token, id)?.enqueue(object : Callback<DeleteAddressResponse> {
            override fun onResponse(
                call: Call<DeleteAddressResponse>,
                response: Response<DeleteAddressResponse>
            ) {
                deleteAddressAPI?.onDeleteAddrressSuccess(response)
            }

            override fun onFailure(call: Call<DeleteAddressResponse>, t: Throwable) {
                deleteAddressAPI?.onError(t.message!!)
            }

        })
    }

    fun DeleteVehicle(token: String, id: Int?) {
        webAPI?.api?.deleteVehicle(token, id.toString())
            ?.enqueue(object : Callback<DeleteVehicleReponse> {
                override fun onResponse(
                    call: Call<DeleteVehicleReponse>,
                    response: Response<DeleteVehicleReponse>
                ) {
                    deleteVehiclesAPI?.onDeleteVehcleSuccess(response)
                }

                override fun onFailure(call: Call<DeleteVehicleReponse>, t: Throwable) {
                    deleteVehiclesAPI?.onError(t.message!!)
                }

            })
    }

    fun UploadImage(token: String,part:MultipartBody.Part)
    {
        webAPI?.api?.uploadImage(token,part)?.enqueue(object :Callback<UploadImageResponse>
        {
            override fun onResponse(
                call: Call<UploadImageResponse>,
                response: Response<UploadImageResponse>
            ) {
                uploadImageAPI?.onUploadImageSuccess(response)
            }

            override fun onFailure(call: Call<UploadImageResponse>, t: Throwable) {
                uploadImageAPI?.onError(t.message!!)
            }

        })
    }

    fun UpdateProfile(token: String,username:String,email: String,dateOfBirth: String)
    {
        webAPI?.api?.updateProfile(token,username,email,dateOfBirth)?.enqueue(object :Callback<UpdateProfileResponse>
        {
            override fun onResponse(
                call: Call<UpdateProfileResponse>,
                response: Response<UpdateProfileResponse>
            ) {
                updateProfileAPI?.onUpdateProfileSuccess(response)
            }

            override fun onFailure(call: Call<UpdateProfileResponse>, t: Throwable) {
                updateProfileAPI?.onError(t.message!!)
            }

        })
    }

    fun DeleteRide(token: String,id: String)
    {
        webAPI?.api?.deleteRide(token, id)?.enqueue(object :Callback<DeleteRideResponse>
        {
            override fun onResponse(
                call: Call<DeleteRideResponse>,
                response: Response<DeleteRideResponse>
            ) {
                deleteRideAPI?.onDeleteRideSuccess(response)
            }

            override fun onFailure(call: Call<DeleteRideResponse>, t: Throwable) {
                t.message?.let { deleteRideAPI?.onError(it) }
            }

        })
    }

    fun CompleteRide(token: String,amount:Double,rideID:String)
    {
        webAPI?.api?.completeRide(token,amount,rideID)?.enqueue(object :Callback<List<CompleteRideResponse>>
        {
            override fun onResponse(
                call: Call<List<CompleteRideResponse>>,
                response: Response<List<CompleteRideResponse>>
            ) {
                completeRideAPI?.onCompleteRideSuccess(response)
            }

            override fun onFailure(call: Call<List<CompleteRideResponse>>, t: Throwable) {
                t.message?.let { completeRideAPI?.onError(it) }
            }

        })
    }

    fun ForgotPassword(email: String)
    {
        webAPI?.api?.forgotpassword(email)?.enqueue(object :Callback<ForgotResponse>
        {
            override fun onResponse(
                call: Call<ForgotResponse>,
                response: Response<ForgotResponse>
            ) {
                forgotPasswordAPI?.onForgotPasswordAPI(response)
            }

            override fun onFailure(call: Call<ForgotResponse>, t: Throwable) {
                forgotPasswordAPI?.onError(t.localizedMessage)
            }

        })
    }

    fun ChangePassword(token: String,currentPass:String,newPass:String)
    {
        webAPI?.api?.changePassword(token,currentPass, newPass)?.enqueue(object :Callback<ChangePasswordResponse>
        {
            override fun onResponse(
                call: Call<ChangePasswordResponse>,
                response: Response<ChangePasswordResponse>
            ) {
                changePasswordAPI?.onChangePassword(response)
            }

            override fun onFailure(call: Call<ChangePasswordResponse>, t: Throwable) {
                changePasswordAPI?.onError(t.localizedMessage)
            }

        })
    }

    fun Feedback(token: String,name: String,decs:String)
    {
        webAPI?.api?.feedback(token,name,decs)?.enqueue(object :Callback<FeedbackResponse>
        {
            override fun onResponse(
                call: Call<FeedbackResponse>,
                response: Response<FeedbackResponse>
            ) {
                feedbackAPI?.onFeedback(response)
            }

            override fun onFailure(call: Call<FeedbackResponse>, t: Throwable) {
               feedbackAPI?.onError(t.localizedMessage)
            }

        })
    }

    fun Support(token: String,question:String,description :String)
    {
        webAPI?.api?.support(token, question, description)?.enqueue(object :Callback<SupportResponse>
        {
            override fun onResponse(
                call: Call<SupportResponse>,
                response: Response<SupportResponse>
            ) {
                supportAPI?.onSupportSuccess(response)
            }

            override fun onFailure(call: Call<SupportResponse>, t: Throwable) {
                supportAPI?.onError(t.localizedMessage)
            }

        })
    }

    fun Dashboard(token: String)
    {
        webAPI?.api?.dashboard(token)?.enqueue(object :Callback<DashboardResponse>
        {
            override fun onResponse(
                call: Call<DashboardResponse>,
                response: Response<DashboardResponse>
            ) {
                dashboardAPI?.onDashboardSuccess(response)
            }

            override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                dashboardAPI?.onError(t.message.toString())
            }

        })
    }

    fun Notifications(token: String)
    {
        webAPI?.api?.notifications(token)?.enqueue(object :Callback<List<NotificationsResponse>>
        {
            override fun onResponse(
                call: Call<List<NotificationsResponse>>,
                response: Response<List<NotificationsResponse>>
            ) {
                notificationsAPI?.onNotifications(response)
            }

            override fun onFailure(call: Call<List<NotificationsResponse>>, t: Throwable) {
                notificationsAPI?.onError(t.message!!)
            }

        })
    }

    fun ClearNotifications(token: String)
    {
        webAPI?.api?.clearNotifications(token)?.enqueue(object :Callback<ClearNotificationsResponse>
        {
            override fun onResponse(
                call: Call<ClearNotificationsResponse>,
                response: Response<ClearNotificationsResponse>
            ) {
                clearNotificationsAPI?.onClearNotifications(response)
            }

            override fun onFailure(call: Call<ClearNotificationsResponse>, t: Throwable) {
                clearNotificationsAPI?.onError(t.message!!)
            }

        })
    }

    interface SignUpPhoneAPI {
        fun onSignUpPhoneSuccess(success: Response<SignUpPhoneNoResponse>)
        fun onError(error: String)
    }

    interface SignUp1API {
        fun onSignUpSuccess(success: Response<SignUp1user>)
        fun onError(error: String)
    }

    interface AddNewAddress {
        fun onUpdateAddress(success: Response<AddNewAddressResponse>)
        fun onError(error: String)
    }

    interface VerifyPhoneAPI {
        fun onVerifyPhoneSuccess(success: Response<NumberVerifyResponse>)
        fun onError(error: String)
    }

    interface LoginAPI {
        fun oLoginSucceess(success: Response<LoginResponse>)
        fun onError(error: String)
    }

    interface MeAPI {
        fun onMeSuccess(success: Response<MeResponse>)
        fun onError(error: String)
    }

    interface AddVehiclesAPI {
        fun onAddVehicleSuccess(success: Response<AddVehiclesResponse>)
        fun onError(error: String)
    }

    interface MyVehiclesAPI {
        fun onMyVehiclesSuccess(success: Response<List<MyVehiclesResponse>>)
        fun onError(error: String)
    }

    interface MyAdderessAPI {
        fun onMyAddressSuccess(success: Response<List<MyAddessesResponse>>)
        fun onError(error: String)
    }

    interface UpdateAddressAPI {
        fun onUpdateAddressSuccess(success: Response<UpdateAddressResponse>)
        fun onError(error: String);
    }

    interface RateAPI {
        fun onRateSuccess(success: Response<MyVehicleRateResponse>)
        fun onError(error: String)
    }

    interface BookRideAPI {
        fun onBookRideSuccess(success: Response<BookRide>)
        fun onError(error: String)
    }

    interface MyDrivesAPI {
        fun onMyDrivesSuccess(success: Response<MyDrivesResponse>)
        fun onError(error: String)
    }

    interface DeleteAddressAPI {
        fun onDeleteAddrressSuccess(success: Response<DeleteAddressResponse>)
        fun onError(error: String)
    }

    interface DeleteVehicleAPI {
        fun onDeleteVehcleSuccess(success: Response<DeleteVehicleReponse>)
        fun onError(error: String)
    }

    interface UploadImageAPI {
        fun onUploadImageSuccess(success: Response<UploadImageResponse>)
        fun onError(error: String)
    }

    interface UpdateProfileAPI {
        fun onUpdateProfileSuccess(success:Response<UpdateProfileResponse>)
        fun onError(error: String)
    }

    interface DeleteRideAPI {
        fun onDeleteRideSuccess(success:Response<DeleteRideResponse>)
        fun onError(error: String)
    }

    interface CompleteRideAPI {
        fun onCompleteRideSuccess(success:Response<List<CompleteRideResponse>>)
        fun onError(error: String)
    }

    interface ForgotPasswordAPI {
        fun onForgotPasswordAPI(success:Response<ForgotResponse>)
        fun onError(error: String)
    }

    interface ChangePasswordAPI {
        fun onChangePassword(success:Response<ChangePasswordResponse>)
        fun onError(error:String)
    }

    interface FeedbackAPI {
        fun onFeedback(success:Response<FeedbackResponse>)
        fun onError(error: String)
    }

    interface SupportAPI {
        fun onSupportSuccess(support:Response<SupportResponse>)
        fun onError(error: String)
    }

    interface DashboardAPI {
        fun onDashboardSuccess(dashboard: Response<DashboardResponse>)
        fun onError(error: String)
    }

    interface NotificationsAPI {
        fun onNotifications(notifications: Response<List<NotificationsResponse>>)
        fun onError(error: String)
    }

    interface ClearNotificationsAPI {
        fun onClearNotifications(clearNotifications:Response<ClearNotificationsResponse>)
        fun onError(error: String)
    }
}
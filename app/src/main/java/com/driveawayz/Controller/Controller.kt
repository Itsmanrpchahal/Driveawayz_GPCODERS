package com.driveawayz.Controller

import com.driveawayz.Login.response.LoginResponse
import com.driveawayz.OTPScreen.response.NumberVerifyResponse
import com.driveawayz.Retrofit.WebAPI
import com.driveawayz.SignUp.signupphone.response.AddVehiclesResponse
import com.driveawayz.SignUp.signupphone.response.SignUpPhoneNoResponse
import com.driveawayz.SignUp.signupphone.response.AddNewAddressResponse
import com.driveawayz.SignUp.signupphone.response.SignUp1user
import com.driveawayz.dashboard.homeFrag.response.BookRide
import com.driveawayz.dashboard.homeFrag.response.MyVehicleRateResponse
import com.driveawayz.dashboard.setiingFrag.response.MyAddessesResponse
import com.driveawayz.dashboard.setiingFrag.response.MyVehiclesResponse
import com.driveawayz.dashboard.setiingFrag.response.UpdateAddressResponse
import com.driveawayz.splashScreen.MeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Controller {

    var webAPI : WebAPI? = null
    var signUp1API : SignUp1API? = null
    var signUpPhoneAPI : SignUpPhoneAPI? = null
    var phoneNumberAPI : VerifyPhoneAPI? = null
    var loginAPI : LoginAPI? = null
    var meAPI : MeAPI? = null
    var addNewAddressAPI : AddNewAddress? = null
    var addVehiclesAPI : AddVehiclesAPI? = null
    var myVehiclesAPI : MyVehiclesAPI?= null
    var myAdderessAPI : MyAdderessAPI? = null
    var updateAddressAPI : UpdateAddressAPI? = null
    var rateAPI : RateAPI? = null
    var bookRideAPI : BookRideAPI? = null

    fun Controller(signUpPhone: SignUpPhoneAPI)
    {
        signUpPhoneAPI =signUpPhone
        webAPI = WebAPI()
    }

    fun Controller(signUpPhone: SignUpPhoneAPI,verifyPhone : VerifyPhoneAPI)
    {
        signUpPhoneAPI =signUpPhone
        phoneNumberAPI = verifyPhone
        webAPI = WebAPI()
    }

    fun Controller(signUp1: SignUp1API, addNewAddress: AddNewAddress){
        signUp1API = signUp1
        addNewAddressAPI = addNewAddress
        webAPI = WebAPI()
    }

    fun Controller(login : LoginAPI,me : MeAPI)
    {
        loginAPI =  login
        meAPI = me
        webAPI = WebAPI()
    }

    fun Controller(me:MeAPI)
    {
        meAPI = me
        webAPI = WebAPI()
    }

    fun Controller(addVehicle : AddVehiclesAPI)
    {
        addVehiclesAPI = addVehicle
        webAPI = WebAPI()
    }

    fun Controller(myVehicles : MyVehiclesAPI, addVehicle: AddVehiclesAPI, me: MeAPI, myAdderess: MyAdderessAPI, addNewAddress: AddNewAddress,updateAdrress : UpdateAddressAPI)
    {
        myVehiclesAPI= myVehicles
        addVehiclesAPI = addVehicle
        meAPI = me
        myAdderessAPI = myAdderess
        addNewAddressAPI = addNewAddress
        updateAddressAPI = updateAdrress
        webAPI = WebAPI()
    }

    fun Controller(myVehicles: MyVehiclesAPI,rate: RateAPI,bookRide: BookRideAPI)
    {
        myVehiclesAPI = myVehicles
        rateAPI = rate
        bookRideAPI = bookRide
        webAPI = WebAPI()
    }


    fun SignUpPhone(mobileNumber : String,channel : String)
    {
        webAPI?.api?.signUpPhone(mobileNumber,channel)?.enqueue(object :Callback<SignUpPhoneNoResponse>
        {
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

    fun VerifyPhone(mobileNumber : String,code : String)
    {
        webAPI?.api?.verifyPhone(mobileNumber,code)?.enqueue(object : Callback<NumberVerifyResponse>
        {
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

    fun SignUp1(name:String,email : String,password :String,dateOfBirth : String,phoneNumber : String,phoneVerified : Boolean)
    {
        webAPI?.api?.signUp(name, email, password, dateOfBirth, phoneNumber,phoneVerified)?.enqueue(object :Callback<SignUp1user>
        {
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

    fun AddNewAddress(token:String, street:String, address:String)
    {
        webAPI?.api?.AddNewAddress(token,street, address)?.enqueue(object :Callback<AddNewAddressResponse>
        {
            override fun onResponse(call: Call<AddNewAddressResponse>, response: Response<AddNewAddressResponse>) {
                addNewAddressAPI?.onUpdateAddress(response)
            }

            override fun onFailure(call: Call<AddNewAddressResponse>, t: Throwable) {
                addNewAddressAPI?.onError(t.message!!)
            }

        })
    }

    fun Login(email: String,password: String)
    {
        webAPI?.api?.login(email, password)?.enqueue(object : Callback<LoginResponse>
        {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                loginAPI?.oLoginSucceess(response)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                t.message?.let { loginAPI?.onError(it) }
            }

        })
    }

    fun Me(token:String)
    {
        webAPI?.api?.me(token)?.enqueue(object : Callback<MeResponse>
        {
            override fun onResponse(call: Call<MeResponse>, response: Response<MeResponse>) {
                meAPI?.onMeSuccess(response)
            }

            override fun onFailure(call: Call<MeResponse>, t: Throwable) {
                t.message?.let { meAPI?.onError(it) }
            }

        })
    }

    fun AddVehicle(token: String,make:String,type:String,year:String,transmission:String,licensePlate: String,state:String)
    {
        webAPI?.api?.addVehicle(token, make, type, year, transmission, licensePlate, state)?.enqueue(object : Callback<AddVehiclesResponse>
        {
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

    fun MyVehicles(token: String)
    {
       webAPI?.api?.myVehicles(token)?.enqueue(object : Callback<List<MyVehiclesResponse>>
       {
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

    fun MyAddresss(token: String)
    {
        webAPI?.api?.myAddresses(token)?.enqueue(object :Callback<List<MyAddessesResponse>>
        {
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

    fun UpdateAddress(token: String,id:String,street: String,address: String)
    {
        webAPI?.api?.updateAddress(token, id,street, address)?.enqueue(object :Callback<UpdateAddressResponse>
        {
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

    fun Rate(token: String,id: String)
    {
        webAPI?.api?.rate(token, id)?.enqueue(object : Callback<MyVehicleRateResponse>
        {
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

    fun RideBook(token: String,pickLocation:String,destination:String,destinationLatLong:String,pickUpLatLong:String,numberOfGuests:String,numberOfHours:String,pickDate:String,pickTime:String,vehicleId:String,rideCharge:String)
    {
        webAPI?.api?.bookRide(token, pickLocation, destination, destinationLatLong, pickUpLatLong, numberOfGuests, numberOfHours, pickDate, pickTime, vehicleId, rideCharge)?.enqueue(object : Callback<BookRide>
        {
            override fun onResponse(call: Call<BookRide>, response: Response<BookRide>) {
                bookRideAPI?.onBookRideSuccess(response)
            }

            override fun onFailure(call: Call<BookRide>, t: Throwable) {
                bookRideAPI?.onError(t.localizedMessage)
            }

        })
    }

    interface SignUpPhoneAPI {
        fun onSignUpPhoneSuccess(success: Response<SignUpPhoneNoResponse>)
        fun onError(error: String)
    }

    interface SignUp1API {
        fun onSignUpSuccess(success : Response<SignUp1user>)
        fun onError(error:String)
    }

    interface AddNewAddress{
        fun onUpdateAddress(success : Response<AddNewAddressResponse>)
        fun onError(error: String)
    }

    interface VerifyPhoneAPI {
        fun onVerifyPhoneSuccess(success: Response<NumberVerifyResponse>)
        fun onError(error: String)
    }

    interface LoginAPI {
        fun oLoginSucceess(success : Response<LoginResponse>)
        fun onError(error: String)
    }

    interface MeAPI {
        fun onMeSuccess(success: Response<MeResponse>)
        fun onError(error: String)
    }

    interface AddVehiclesAPI {
        fun onAddVehicleSuccess(success:Response<AddVehiclesResponse>)
        fun onError(error: String)
    }

    interface MyVehiclesAPI {
        fun onMyVehiclesSuccess(success:Response<List<MyVehiclesResponse>>)
        fun onError(error: String)
    }

    interface MyAdderessAPI {
        fun onMyAddressSuccess(success: Response<List<MyAddessesResponse>>)
        fun onError(error: String)
    }
    
    interface UpdateAddressAPI {
        fun onUpdateAddressSuccess(success : Response<UpdateAddressResponse>)
        fun onError(error: String);
    }

    interface RateAPI {
        fun onRateSuccess(success : Response<MyVehicleRateResponse>)
        fun onError(error: String)
    }

    interface BookRideAPI {
        fun onBookRideSuccess(success: Response<BookRide>)
        fun onError(error: String)
    }
}
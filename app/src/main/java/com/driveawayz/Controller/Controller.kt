package com.driveawayz.Controller

import com.driveawayz.Login.response.LoginResponse
import com.driveawayz.OTPScreen.response.NumberVerifyResponse
import com.driveawayz.Retrofit.WebAPI
import com.driveawayz.SignUp.response.SignUp1Response
import com.driveawayz.SignUp.signupphone.response.AddVehiclesResponse
import com.driveawayz.SignUp.signupphone.response.SignUp1User
import com.driveawayz.SignUp.signupphone.response.SignUpPhoneNoResponse
import com.driveawayz.SignUp.signupphone.response.UpdateAddress
import com.driveawayz.dashboard.setiingFrag.response.MyAddessesResponse
import com.driveawayz.dashboard.setiingFrag.response.MyVehiclesResponse
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
    var updateAddressAPI : UpdateAddressAPI? = null
    var addVehiclesAPI : AddVehiclesAPI? = null
    var myVehiclesAPI : MyVehiclesAPI?= null
    var myAdderessAPI : MyAdderessAPI? = null


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

    fun Controller(signUp1: SignUp1API,updateAddress: UpdateAddressAPI){
        signUp1API = signUp1
        updateAddressAPI = updateAddress
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

    fun Controller(myVehicles : MyVehiclesAPI,addVehicle: AddVehiclesAPI,me: MeAPI,myAdderess: MyAdderessAPI)
    {
        myVehiclesAPI= myVehicles
        addVehiclesAPI = addVehicle
        meAPI = me
        myAdderessAPI = myAdderess
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
        webAPI?.api?.signUp(name, email, password, dateOfBirth, phoneNumber,phoneVerified)?.enqueue(object :Callback<SignUp1User>
        {
            override fun onResponse(
                call: Call<SignUp1User>,
                response: Response<SignUp1User>
            ) {
                    signUp1API?.onSignUpSuccess(response)
            }

            override fun onFailure(call: Call<SignUp1User>, t: Throwable) {
                t.message?.let { signUp1API?.onError(it) }
            }

        })
    }

    fun UpdateAddress(token:String,street:String,address:String)
    {
        webAPI?.api?.updateAddress(token,street, address)?.enqueue(object :Callback<UpdateAddress>
        {
            override fun onResponse(call: Call<UpdateAddress>, response: Response<UpdateAddress>) {
                updateAddressAPI?.onUpdateAddress(response)
            }

            override fun onFailure(call: Call<UpdateAddress>, t: Throwable) {
                updateAddressAPI?.onError(t.message!!)
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

    interface SignUpPhoneAPI {
        fun onSignUpPhoneSuccess(success: Response<SignUpPhoneNoResponse>)
        fun onError(error: String)
    }

    interface SignUp1API {
        fun onSignUpSuccess(success : Response<SignUp1User>)
        fun onError(error:String)
    }

    interface UpdateAddressAPI{
        fun onUpdateAddress(success : Response<com.driveawayz.SignUp.signupphone.response.UpdateAddress>)
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
}
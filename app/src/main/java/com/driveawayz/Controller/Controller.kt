package com.driveawayz.Controller

import com.driveawayz.Login.response.LoginResponse
import com.driveawayz.OTPScreen.response.NumberVerifyResponse
import com.driveawayz.Retrofit.WebAPI
import com.driveawayz.SignUp.response.SignUp1Response
import com.driveawayz.SignUp.signupphone.response.SignUpPhoneNoResponse
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

    fun Controller(signUp1: SignUp1API){
        signUp1API = signUp1
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

    fun SignUp1(name:String,email : String,password :String,dateOfBirth : String,street : String,address :String,phoneNumber : String)
    {
        webAPI?.api?.signUp(name, email, password, dateOfBirth, street, address, phoneNumber)?.enqueue(object :Callback<SignUp1Response>
        {
            override fun onResponse(
                call: Call<SignUp1Response>,
                response: Response<SignUp1Response>
            ) {
                    signUp1API?.onSignUpSuccess(response)
            }

            override fun onFailure(call: Call<SignUp1Response>, t: Throwable) {
                t.message?.let { signUp1API?.onError(it) }
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
        webAPI?.api?.me("Bearer "+token)?.enqueue(object : Callback<MeResponse>
        {
            override fun onResponse(call: Call<MeResponse>, response: Response<MeResponse>) {
                meAPI?.onMeSuccess(response)
            }

            override fun onFailure(call: Call<MeResponse>, t: Throwable) {
                t.message?.let { meAPI?.onError(it) }
            }

        })
    }

    interface SignUpPhoneAPI {
        fun onSignUpPhoneSuccess(success: Response<SignUpPhoneNoResponse>)
        fun onError(error: String)
    }

    interface SignUp1API {
        fun onSignUpSuccess(success : Response<SignUp1Response>)
        fun onError(error:String)
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
}
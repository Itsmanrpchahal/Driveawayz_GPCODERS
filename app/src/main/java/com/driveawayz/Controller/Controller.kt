package com.driveawayz.Controller

import com.driveawayz.Retrofit.WebAPI
import com.driveawayz.SignUp.response.SignUp1Response
import com.driveawayz.SignUp.signupphone.response.SignUpPhoneNoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Controller {

    var webAPI : WebAPI? = null
    var signUp1API : SignUp1API? = null
    var signUpPhoneAPI : SignUpPhoneAPI? = null



    fun Controller(signUpPhone: SignUpPhoneAPI)
    {
        signUpPhoneAPI =signUpPhone
        webAPI = WebAPI()
    }

    fun Controller(signUp1: SignUp1API){
        signUp1API = signUp1
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
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<SignUpPhoneNoResponse>, t: Throwable) {
                TODO("Not yet implemented")
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
                    signUp1API?.onError(t.localizedMessage)
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
}
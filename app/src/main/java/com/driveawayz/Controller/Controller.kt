package com.driveawayz.Controller

import com.driveawayz.Retrofit.WebAPI
import com.driveawayz.SignUp.response.SignUp1Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Controller {

    var webAPI : WebAPI? = null
    var signUp1API : SignUp1API? = null

    fun Controller(signUp1: SignUp1API){
        signUp1API = signUp1
        webAPI = WebAPI()
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

    interface SignUp1API {
        fun onSignUpSuccess(success : Response<SignUp1Response>)
        fun onError(error:String)
    }
}
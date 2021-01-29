package com.driveawayz.SignUp.signupphone.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SignUp1user {

    @SerializedName("access_token")
    @Expose
    private var accessToken: String? = null

    fun getAccessToken(): String? {
        return accessToken
    }

    fun setAccessToken(accessToken: String?) {
        this.accessToken = accessToken
    }

}
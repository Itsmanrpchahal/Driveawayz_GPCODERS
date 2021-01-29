package com.driveawayz.dashboard.setiingFrag.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class MyAddessesResponse {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("street")
    @Expose
    var street: String? = null

    @SerializedName("address")
    @Expose
    var address: String? = null

    @SerializedName("defaultAddress")
    @Expose
    var defaultAddress: Boolean? = null

    @SerializedName("createdAt")
    @Expose
    var createdAt: String? = null

    @SerializedName("updatedAt")
    @Expose
    var updatedAt: String? = null

    @SerializedName("deletedAt")
    @Expose
    var deletedAt: Any? = null

    @SerializedName("user")
    @Expose
    var user: User? = null

    inner class User {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("email")
        @Expose
        var email: String? = null

        @SerializedName("password")
        @Expose
        var password: String? = null

        @SerializedName("dateOfBirth")
        @Expose
        var dateOfBirth: String? = null

        @SerializedName("phoneNumber")
        @Expose
        var phoneNumber: String? = null

        @SerializedName("phoneVerified")
        @Expose
        var phoneVerified: Boolean? = null

        @SerializedName("createdAt")
        @Expose
        var createdAt: String? = null

        @SerializedName("updatedAt")
        @Expose
        var updatedAt: String? = null

        @SerializedName("deletedAt")
        @Expose
        var deletedAt: Any? = null
    }
}



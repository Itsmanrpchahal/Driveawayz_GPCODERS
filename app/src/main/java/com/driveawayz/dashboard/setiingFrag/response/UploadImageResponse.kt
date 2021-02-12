package com.driveawayz.dashboard.setiingFrag.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UploadImageResponse {
    @SerializedName("fieldname")
    @Expose
    var fieldname: String? = null

    @SerializedName("originalname")
    @Expose
    var originalname: String? = null

    @SerializedName("encoding")
    @Expose
    var encoding: String? = null

    @SerializedName("mimetype")
    @Expose
    var mimetype: String? = null

    @SerializedName("destination")
    @Expose
    var destination: String? = null

    @SerializedName("filename")
    @Expose
    var filename: String? = null

    @SerializedName("path")
    @Expose
    var path: String? = null

    @SerializedName("size")
    @Expose
    var size: Int? = null

    @SerializedName("user")
    @Expose
    var user: User? = null

    @SerializedName("imageUrl")
    @Expose
    var imageUrl: String? = null

    @SerializedName("deletedAt")
    @Expose
    var deletedAt: Any? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("createdAt")
    @Expose
    var createdAt: String? = null

    @SerializedName("updatedAt")
    @Expose
    var updatedAt: String? = null

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

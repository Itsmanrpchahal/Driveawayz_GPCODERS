package com.driveawayz.SignUp.signupphone.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AddNewAddressResponse {
    @SerializedName("street")
    @Expose
    private var street: String? = null

    @SerializedName("address")
    @Expose
    private var address: String? = null

    @SerializedName("user")
    @Expose
    private var user: User? = null

    @SerializedName("deletedAt")
    @Expose
    private var deletedAt: Any? = null

    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("defaultAddress")
    @Expose
    private var defaultAddress: Boolean? = null

    @SerializedName("createdAt")
    @Expose
    private var createdAt: String? = null

    @SerializedName("updatedAt")
    @Expose
    private var updatedAt: String? = null

    fun getStreet(): String? {
        return street
    }

    fun setStreet(street: String?) {
        this.street = street
    }

    fun getAddress(): String? {
        return address
    }

    fun setAddress(address: String?) {
        this.address = address
    }

    fun getUser(): User? {
        return user
    }

    fun setUser(user: User?) {
        this.user = user
    }

    fun getDeletedAt(): Any? {
        return deletedAt
    }

    fun setDeletedAt(deletedAt: Any?) {
        this.deletedAt = deletedAt
    }

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getDefaultAddress(): Boolean? {
        return defaultAddress
    }

    fun setDefaultAddress(defaultAddress: Boolean?) {
        this.defaultAddress = defaultAddress
    }

    fun getCreatedAt(): String? {
        return createdAt
    }

    fun setCreatedAt(createdAt: String?) {
        this.createdAt = createdAt
    }

    fun getUpdatedAt(): String? {
        return updatedAt
    }

    fun setUpdatedAt(updatedAt: String?) {
        this.updatedAt = updatedAt
    }

    class User {
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
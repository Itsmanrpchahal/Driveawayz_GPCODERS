package com.driveawayz.SignUp.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SignUp1Response {
    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("email")
    @Expose
    private var email: String? = null

    @SerializedName("password")
    @Expose
    private var password: String? = null

    @SerializedName("dateOfBirth")
    @Expose
    private var dateOfBirth: String? = null

    @SerializedName("street")
    @Expose
    private var street: String? = null

    @SerializedName("address")
    @Expose
    private var address: String? = null

    @SerializedName("phoneNumber")
    @Expose
    private var phoneNumber: String? = null

    @SerializedName("deletedAt")
    @Expose
    private var deletedAt: Any? = null

    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("phoneVerified")
    @Expose
    private var phoneVerified: Boolean? = null

    @SerializedName("createdAt")
    @Expose
    private var createdAt: String? = null

    @SerializedName("updatedAt")
    @Expose
    private var updatedAt: String? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String?) {
        this.password = password
    }

    fun getDateOfBirth(): String? {
        return dateOfBirth
    }

    fun setDateOfBirth(dateOfBirth: String?) {
        this.dateOfBirth = dateOfBirth
    }

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

    fun getPhoneNumber(): String? {
        return phoneNumber
    }

    fun setPhoneNumber(phoneNumber: String?) {
        this.phoneNumber = phoneNumber
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

    fun getPhoneVerified(): Boolean? {
        return phoneVerified
    }

    fun setPhoneVerified(phoneVerified: Boolean?) {
        this.phoneVerified = phoneVerified
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

}
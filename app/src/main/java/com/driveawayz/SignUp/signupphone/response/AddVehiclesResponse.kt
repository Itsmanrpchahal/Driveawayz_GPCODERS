package com.driveawayz.SignUp.signupphone.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AddVehiclesResponse {

    @SerializedName("make")
    @Expose
    private var make: String? = null

    @SerializedName("type")
    @Expose
    private var type: String? = null

    @SerializedName("year")
    @Expose
    private var year: Int? = null

    @SerializedName("transmission")
    @Expose
    private var transmission: String? = null

    @SerializedName("licensePlate")
    @Expose
    private var licensePlate: String? = null

    @SerializedName("state")
    @Expose
    private var state: String? = null

    @SerializedName("user")
    @Expose
    private var user: User? = null

    @SerializedName("deletedAt")
    @Expose
    private var deletedAt: Any? = null

    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("createdAt")
    @Expose
    private var createdAt: String? = null

    @SerializedName("updatedAt")
    @Expose
    private var updatedAt: String? = null

    fun getMake(): String? {
        return make
    }

    fun setMake(make: String?) {
        this.make = make
    }

    fun getType(): String? {
        return type
    }

    fun setType(type: String?) {
        this.type = type
    }

    fun getYear(): Int? {
        return year
    }

    fun setYear(year: Int?) {
        this.year = year
    }

    fun getTransmission(): String? {
        return transmission
    }

    fun setTransmission(transmission: String?) {
        this.transmission = transmission
    }

    fun getLicensePlate(): String? {
        return licensePlate
    }

    fun setLicensePlate(licensePlate: String?) {
        this.licensePlate = licensePlate
    }

    fun getState(): String? {
        return state
    }

    fun setState(state: String?) {
        this.state = state
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
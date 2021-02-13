package com.driveawayz.splashScreen

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MeResponse {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

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

    @SerializedName("stripe")
    @Expose
    var stripe: Stripe? = null

    @SerializedName("address")
    @Expose
    var address: List<Address>? = null

    @SerializedName("vehicle")
    @Expose
    var vehicle: List<Vehicle>? = null

    @SerializedName("cards")
    @Expose
    var cards: List<Any>? = null

    @SerializedName("profilePic")
    @Expose
    var profilePic: ProfilePic? = null

    inner class Stripe {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("customer")
        @Expose
        var customer: String? = null

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

    inner class Address {
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
    }

    inner class Vehicle {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("make")
        @Expose
        var make: String? = null

        @SerializedName("type")
        @Expose
        var type: String? = null

        @SerializedName("year")
        @Expose
        var year: Int? = null

        @SerializedName("transmission")
        @Expose
        var transmission: String? = null

        @SerializedName("licensePlate")
        @Expose
        var licensePlate: String? = null

        @SerializedName("state")
        @Expose
        var state: String? = null

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

    inner class ProfilePic {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("imageUrl")
        @Expose
        var imageUrl: String? = null

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
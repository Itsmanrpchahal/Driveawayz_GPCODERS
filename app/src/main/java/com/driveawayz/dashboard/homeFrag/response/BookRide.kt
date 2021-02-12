package com.driveawayz.dashboard.homeFrag.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BookRide {
    @SerializedName("pickLocation")
    @Expose
    var pickLocation: String? = null

    @SerializedName("destination")
    @Expose
    var destination: String? = null

    @SerializedName("destinationLatLong")
    @Expose
    var destinationLatLong: String? = null

    @SerializedName("pickUpLatLong")
    @Expose
    var pickUpLatLong: String? = null

    @SerializedName("numberOfGuests")
    @Expose
    var numberOfGuests: Int? = null

    @SerializedName("numberOfHours")
    @Expose
    var numberOfHours: Int? = null

    @SerializedName("pickDate")
    @Expose
    var pickDate: String? = null

    @SerializedName("pickTime")
    @Expose
    var pickTime: String? = null

    @SerializedName("vehicleId")
    @Expose
    var vehicleId: Int? = null

    @SerializedName("rideCharge")
    @Expose
    var rideCharge: String? = null

    @SerializedName("user")
    @Expose
    var user: User? = null

    @SerializedName("vehicle")
    @Expose
    var vehicle: Vehicle? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

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
}

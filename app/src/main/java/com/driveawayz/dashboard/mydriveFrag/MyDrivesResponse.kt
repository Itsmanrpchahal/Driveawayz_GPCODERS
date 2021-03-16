package com.driveawayz.dashboard.mydriveFrag

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MyDrivesResponse {
    @SerializedName("futureRides")
    @Expose
    var futureRides: List<FutureRide>? = null

    @SerializedName("completedRides")
    @Expose
    var completedRides: List<CompletedRide>? = null

    inner class FutureRide {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("pickLocation")
        @Expose
        var pickLocation: String? = null

        @SerializedName("destination")
        @Expose
        var destination: String? = null

        @SerializedName("pickUpLatLong")
        @Expose
        var pickUpLatLong: String? = null

        @SerializedName("destinationLatLong")
        @Expose
        var destinationLatLong: String? = null

        @SerializedName("numberOfGuests")
        @Expose
        var numberOfGuests: Int? = null

        @SerializedName("numberOfHours")
        @Expose
        var numberOfHours: Int? = null

        @SerializedName("pickDt")
        @Expose
        var pickDate: String? = null



        @SerializedName("rideCharge")
        @Expose
        var rideCharge: String? = null

        @SerializedName("status")
        @Expose
        var status: String? = null

        @SerializedName("createdAt")
        @Expose
        var createdAt: String? = null

        @SerializedName("updatedAt")
        @Expose
        var updatedAt: String? = null

        @SerializedName("vehicle")
        @Expose
        var vehicle: Vehicle? = null

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

    inner class CompletedRide {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("pickLocation")
        @Expose
        var pickLocation: String? = null

        @SerializedName("destination")
        @Expose
        var destination: String? = null

        @SerializedName("pickUpLatLong")
        @Expose
        var pickUpLatLong: String? = null

        @SerializedName("destinationLatLong")
        @Expose
        var destinationLatLong: String? = null

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

        @SerializedName("rideCharge")
        @Expose
        var rideCharge: String? = null

        @SerializedName("status")
        @Expose
        var status: String? = null

        @SerializedName("createdAt")
        @Expose
        var createdAt: String? = null

        @SerializedName("updatedAt")
        @Expose
        var updatedAt: String? = null

        @SerializedName("vehicle")
        @Expose
        var vehicle: Vehicle_? = null

        inner class Vehicle_ {
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
}

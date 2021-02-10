package com.driveawayz.dashboard.homeFrag.response

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class MyVehicleRateResponse {
    @SerializedName("rate")
    @Expose
    private var rate: Int? = null

    fun getRate(): Int? {
        return rate
    }

    fun setRate(rate: Int?) {
        this.rate = rate
    }
}
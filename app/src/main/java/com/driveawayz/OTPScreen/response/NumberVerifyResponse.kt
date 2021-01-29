package com.driveawayz.OTPScreen.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NumberVerifyResponse {
    @SerializedName("sid")
    @Expose
    private var sid: String? = null

    @SerializedName("serviceSid")
    @Expose
    private var serviceSid: String? = null

    @SerializedName("accountSid")
    @Expose
    private var accountSid: String? = null

    @SerializedName("to")
    @Expose
    private var to: String? = null

    @SerializedName("channel")
    @Expose
    private var channel: String? = null

    @SerializedName("status")
    @Expose
    private var status: String? = null

    @SerializedName("valid")
    @Expose
    private var valid: Boolean? = null

    @SerializedName("amount")
    @Expose
    private var amount: Any? = null

    @SerializedName("payee")
    @Expose
    private var payee: Any? = null

    @SerializedName("dateCreated")
    @Expose
    private var dateCreated: String? = null

    @SerializedName("dateUpdated")
    @Expose
    private var dateUpdated: String? = null

    fun getSid(): String? {
        return sid
    }

    fun setSid(sid: String?) {
        this.sid = sid
    }

    fun getServiceSid(): String? {
        return serviceSid
    }

    fun setServiceSid(serviceSid: String?) {
        this.serviceSid = serviceSid
    }

    fun getAccountSid(): String? {
        return accountSid
    }

    fun setAccountSid(accountSid: String?) {
        this.accountSid = accountSid
    }

    fun getTo(): String? {
        return to
    }

    fun setTo(to: String?) {
        this.to = to
    }

    fun getChannel(): String? {
        return channel
    }

    fun setChannel(channel: String?) {
        this.channel = channel
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    fun getValid(): Boolean? {
        return valid
    }

    fun setValid(valid: Boolean?) {
        this.valid = valid
    }

    fun getAmount(): Any? {
        return amount
    }

    fun setAmount(amount: Any?) {
        this.amount = amount
    }

    fun getPayee(): Any? {
        return payee
    }

    fun setPayee(payee: Any?) {
        this.payee = payee
    }

    fun getDateCreated(): String? {
        return dateCreated
    }

    fun setDateCreated(dateCreated: String?) {
        this.dateCreated = dateCreated
    }

    fun getDateUpdated(): String? {
        return dateUpdated
    }

    fun setDateUpdated(dateUpdated: String?) {
        this.dateUpdated = dateUpdated
    }
}
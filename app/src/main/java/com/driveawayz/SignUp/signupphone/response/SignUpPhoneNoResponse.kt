package com.driveawayz.SignUp.signupphone.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SignUpPhoneNoResponse {
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

    @SerializedName("lookup")
    @Expose
    private var lookup: Lookup? = null

    @SerializedName("amount")
    @Expose
    private var amount: Any? = null

    @SerializedName("payee")
    @Expose
    private var payee: Any? = null

    @SerializedName("sendCodeAttempts")
    @Expose
    private var sendCodeAttempts: List<SendCodeAttempt?>? = null

    @SerializedName("dateCreated")
    @Expose
    private var dateCreated: String? = null

    @SerializedName("dateUpdated")
    @Expose
    private var dateUpdated: String? = null

    @SerializedName("url")
    @Expose
    private var url: String? = null

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

    fun getLookup(): Lookup? {
        return lookup
    }

    fun setLookup(lookup: Lookup?) {
        this.lookup = lookup
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

    fun getSendCodeAttempts(): List<SendCodeAttempt?>? {
        return sendCodeAttempts
    }

    fun setSendCodeAttempts(sendCodeAttempts: List<SendCodeAttempt?>?) {
        this.sendCodeAttempts = sendCodeAttempts
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

    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String?) {
        this.url = url
    }

    class SendCodeAttempt {
        @SerializedName("channel")
        @Expose
        var channel: String? = null

        @SerializedName("time")
        @Expose
        var time: String? = null
    }

    class Lookup {
        @SerializedName("carrier")
        @Expose
        var carrier: Carrier? = null

        inner class Carrier {
            @SerializedName("mobile_country_code")
            @Expose
            var mobileCountryCode: String? = null

            @SerializedName("type")
            @Expose
            var type: String? = null

            @SerializedName("error_code")
            @Expose
            var errorCode: Any? = null

            @SerializedName("mobile_network_code")
            @Expose
            var mobileNetworkCode: String? = null

            @SerializedName("name")
            @Expose
            var name: String? = null
        }
    }
}
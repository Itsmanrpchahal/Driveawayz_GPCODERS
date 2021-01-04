package com.driveawayz.SignUp.signupphone.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignUpPhoneNoResponse {

    @SerializedName("sid")
    @Expose
    private String sid;
    @SerializedName("serviceSid")
    @Expose
    private String serviceSid;
    @SerializedName("accountSid")
    @Expose
    private String accountSid;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("channel")
    @Expose
    private String channel;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("valid")
    @Expose
    private Boolean valid;
    @SerializedName("lookup")
    @Expose
    private Lookup lookup;
    @SerializedName("amount")
    @Expose
    private Object amount;
    @SerializedName("payee")
    @Expose
    private Object payee;
    @SerializedName("sendCodeAttempts")
    @Expose
    private List<SendCodeAttempt> sendCodeAttempts = null;
    @SerializedName("dateCreated")
    @Expose
    private String dateCreated;
    @SerializedName("dateUpdated")
    @Expose
    private String dateUpdated;
    @SerializedName("url")
    @Expose
    private String url;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getServiceSid() {
        return serviceSid;
    }

    public void setServiceSid(String serviceSid) {
        this.serviceSid = serviceSid;
    }

    public String getAccountSid() {
        return accountSid;
    }

    public void setAccountSid(String accountSid) {
        this.accountSid = accountSid;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Lookup getLookup() {
        return lookup;
    }

    public void setLookup(Lookup lookup) {
        this.lookup = lookup;
    }

    public Object getAmount() {
        return amount;
    }

    public void setAmount(Object amount) {
        this.amount = amount;
    }

    public Object getPayee() {
        return payee;
    }

    public void setPayee(Object payee) {
        this.payee = payee;
    }

    public List<SendCodeAttempt> getSendCodeAttempts() {
        return sendCodeAttempts;
    }

    public void setSendCodeAttempts(List<SendCodeAttempt> sendCodeAttempts) {
        this.sendCodeAttempts = sendCodeAttempts;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public class SendCodeAttempt {

        @SerializedName("channel")
        @Expose
        private String channel;
        @SerializedName("time")
        @Expose
        private String time;

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

    }

    public class Lookup {

        @SerializedName("carrier")
        @Expose
        private Carrier carrier;

        public Carrier getCarrier() {
            return carrier;
        }

        public void setCarrier(Carrier carrier) {
            this.carrier = carrier;
        }

        public class Carrier {

            @SerializedName("mobile_country_code")
            @Expose
            private String mobileCountryCode;
            @SerializedName("type")
            @Expose
            private String type;
            @SerializedName("error_code")
            @Expose
            private Object errorCode;
            @SerializedName("mobile_network_code")
            @Expose
            private String mobileNetworkCode;
            @SerializedName("name")
            @Expose
            private String name;

            public String getMobileCountryCode() {
                return mobileCountryCode;
            }

            public void setMobileCountryCode(String mobileCountryCode) {
                this.mobileCountryCode = mobileCountryCode;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Object getErrorCode() {
                return errorCode;
            }

            public void setErrorCode(Object errorCode) {
                this.errorCode = errorCode;
            }

            public String getMobileNetworkCode() {
                return mobileNetworkCode;
            }

            public void setMobileNetworkCode(String mobileNetworkCode) {
                this.mobileNetworkCode = mobileNetworkCode;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

        }
    }

}
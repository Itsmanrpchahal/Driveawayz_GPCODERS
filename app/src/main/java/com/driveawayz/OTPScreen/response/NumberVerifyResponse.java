package com.driveawayz.OTPScreen.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NumberVerifyResponse {

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
@SerializedName("amount")
@Expose
private Object amount;
@SerializedName("payee")
@Expose
private Object payee;
@SerializedName("dateCreated")
@Expose
private String dateCreated;
@SerializedName("dateUpdated")
@Expose
private String dateUpdated;

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

}
package com.driveawayz.dashboard.notificationFrag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClearNotificationsResponse {

@SerializedName("delete")
@Expose
private Boolean delete;
@SerializedName("message")
@Expose
private String message;

public Boolean getDelete() {
return delete;
}

public void setDelete(Boolean delete) {
this.delete = delete;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

}
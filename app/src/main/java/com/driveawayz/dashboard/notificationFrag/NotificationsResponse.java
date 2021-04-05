package com.driveawayz.dashboard.notificationFrag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationsResponse {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("message")
@Expose
private String message;
@SerializedName("createdAt")
@Expose
private String createdAt;
@SerializedName("updatedAt")
@Expose
private String updatedAt;
@SerializedName("deletedAt")
@Expose
private Object deletedAt;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

public String getUpdatedAt() {
return updatedAt;
}

public void setUpdatedAt(String updatedAt) {
this.updatedAt = updatedAt;
}

public Object getDeletedAt() {
return deletedAt;
}

public void setDeletedAt(Object deletedAt) {
this.deletedAt = deletedAt;
}

}
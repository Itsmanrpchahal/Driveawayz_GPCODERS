package com.driveawayz.dashboard.mydriveFrag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteRideResponse {

@SerializedName("deleted")
@Expose
private Boolean deleted;

public Boolean getDeleted() {
return deleted;
}

public void setDeleted(Boolean deleted) {
this.deleted = deleted;
}

}
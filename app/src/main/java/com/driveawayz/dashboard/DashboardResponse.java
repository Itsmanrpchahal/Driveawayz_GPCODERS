package com.driveawayz.dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DashboardResponse {

@SerializedName("error")
@Expose
private Boolean error;
@SerializedName("details")
@Expose
private Details details;

public Boolean getError() {
return error;
}

public void setError(Boolean error) {
this.error = error;
}

public Details getDetails() {
return details;
}

public void setDetails(Details details) {
this.details = details;
}

    public class Details {

        @SerializedName("totalRides")
        @Expose
        private Integer totalRides;
        @SerializedName("totalTime")
        @Expose
        private String totalTime;
        @SerializedName("totalDistance")
        @Expose
        private String totalDistance;

        public Integer getTotalRides() {
            return totalRides;
        }

        public void setTotalRides(Integer totalRides) {
            this.totalRides = totalRides;
        }

        public String getTotalTime() {
            return totalTime;
        }

        public void setTotalTime(String totalTime) {
            this.totalTime = totalTime;
        }

        public String getTotalDistance() {
            return totalDistance;
        }

        public void setTotalDistance(String totalDistance) {
            this.totalDistance = totalDistance;
        }

    }
}
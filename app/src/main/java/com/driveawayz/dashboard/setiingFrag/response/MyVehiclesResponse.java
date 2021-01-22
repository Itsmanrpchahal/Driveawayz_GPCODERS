package com.driveawayz.dashboard.setiingFrag.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyVehiclesResponse {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("make")
@Expose
private String make;
@SerializedName("type")
@Expose
private String type;
@SerializedName("year")
@Expose
private Integer year;
@SerializedName("transmission")
@Expose
private String transmission;
@SerializedName("licensePlate")
@Expose
private String licensePlate;
@SerializedName("state")
@Expose
private String state;
@SerializedName("createdAt")
@Expose
private String createdAt;
@SerializedName("updatedAt")
@Expose
private String updatedAt;
@SerializedName("deletedAt")
@Expose
private Object deletedAt;
@SerializedName("user")
@Expose
private User user;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getMake() {
return make;
}

public void setMake(String make) {
this.make = make;
}

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public Integer getYear() {
return year;
}

public void setYear(Integer year) {
this.year = year;
}

public String getTransmission() {
return transmission;
}

public void setTransmission(String transmission) {
this.transmission = transmission;
}

public String getLicensePlate() {
return licensePlate;
}

public void setLicensePlate(String licensePlate) {
this.licensePlate = licensePlate;
}

public String getState() {
return state;
}

public void setState(String state) {
this.state = state;
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

public User getUser() {
return user;
}

public void setUser(User user) {
this.user = user;
}

    public class User {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("dateOfBirth")
        @Expose
        private String dateOfBirth;
        @SerializedName("phoneNumber")
        @Expose
        private String phoneNumber;
        @SerializedName("phoneVerified")
        @Expose
        private Boolean phoneVerified;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Boolean getPhoneVerified() {
            return phoneVerified;
        }

        public void setPhoneVerified(Boolean phoneVerified) {
            this.phoneVerified = phoneVerified;
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

}
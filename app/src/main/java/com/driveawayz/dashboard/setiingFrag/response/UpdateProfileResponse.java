package com.driveawayz.dashboard.setiingFrag.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateProfileResponse {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("email")
@Expose
private String email;
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
@SerializedName("stripe")
@Expose
private Stripe stripe;
@SerializedName("address")
@Expose
private List<Address> address = null;
@SerializedName("vehicle")
@Expose
private List<Vehicle> vehicle = null;
@SerializedName("cards")
@Expose
private List<Object> cards = null;
@SerializedName("profilePic")
@Expose
private ProfilePic profilePic;

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

public Stripe getStripe() {
return stripe;
}

public void setStripe(Stripe stripe) {
this.stripe = stripe;
}

public List<Address> getAddress() {
return address;
}

public void setAddress(List<Address> address) {
this.address = address;
}

public List<Vehicle> getVehicle() {
return vehicle;
}

public void setVehicle(List<Vehicle> vehicle) {
this.vehicle = vehicle;
}

public List<Object> getCards() {
return cards;
}

public void setCards(List<Object> cards) {
this.cards = cards;
}

public ProfilePic getProfilePic() {
return profilePic;
}

public void setProfilePic(ProfilePic profilePic) {
this.profilePic = profilePic;
}

    public class Stripe {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("customer")
        @Expose
        private String customer;
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

        public String getCustomer() {
            return customer;
        }

        public void setCustomer(String customer) {
            this.customer = customer;
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

    public class Address {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("street")
        @Expose
        private String street;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("defaultAddress")
        @Expose
        private Boolean defaultAddress;
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

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Boolean getDefaultAddress() {
            return defaultAddress;
        }

        public void setDefaultAddress(Boolean defaultAddress) {
            this.defaultAddress = defaultAddress;
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

    public class Vehicle {

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

    }

    public class ProfilePic {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("imageUrl")
        @Expose
        private String imageUrl;
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

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
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
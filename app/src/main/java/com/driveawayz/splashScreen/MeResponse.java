package com.driveawayz.splashScreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MeResponse {

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
private List<Object> address = null;
@SerializedName("vehicle")
@Expose
private List<Object> vehicle = null;
@SerializedName("cards")
@Expose
private List<Object> cards = null;

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

public List<Object> getAddress() {
return address;
}

public void setAddress(List<Object> address) {
this.address = address;
}

public List<Object> getVehicle() {
return vehicle;
}

public void setVehicle(List<Object> vehicle) {
this.vehicle = vehicle;
}

public List<Object> getCards() {
return cards;
}

public void setCards(List<Object> cards) {
this.cards = cards;
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
}
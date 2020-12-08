package com.driveawayz.SignUp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUp1Response {

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
@SerializedName("street")
@Expose
private String street;
@SerializedName("address")
@Expose
private String address;
@SerializedName("phoneNumber")
@Expose
private String phoneNumber;
@SerializedName("deletedAt")
@Expose
private Object deletedAt;
@SerializedName("id")
@Expose
private Integer id;
@SerializedName("phoneVerified")
@Expose
private Boolean phoneVerified;
@SerializedName("createdAt")
@Expose
private String createdAt;
@SerializedName("updatedAt")
@Expose
private String updatedAt;

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

public String getPhoneNumber() {
return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
this.phoneNumber = phoneNumber;
}

public Object getDeletedAt() {
return deletedAt;
}

public void setDeletedAt(Object deletedAt) {
this.deletedAt = deletedAt;
}

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
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

}
package com.driveawayz.dashboard.supportFrag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SupportResponse {

@SerializedName("question")
@Expose
private String question;
@SerializedName("description")
@Expose
private String description;
@SerializedName("user")
@Expose
private User user;
@SerializedName("answer")
@Expose
private Object answer;
@SerializedName("deletedAt")
@Expose
private Object deletedAt;
@SerializedName("id")
@Expose
private Integer id;
@SerializedName("createdAt")
@Expose
private String createdAt;
@SerializedName("updatedAt")
@Expose
private String updatedAt;

public String getQuestion() {
return question;
}

public void setQuestion(String question) {
this.question = question;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public User getUser() {
return user;
}

public void setUser(User user) {
this.user = user;
}

public Object getAnswer() {
return answer;
}

public void setAnswer(Object answer) {
this.answer = answer;
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
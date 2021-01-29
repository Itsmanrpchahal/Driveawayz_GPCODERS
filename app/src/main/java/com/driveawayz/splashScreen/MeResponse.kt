package com.driveawayz.splashScreen

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MeResponse {
    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("email")
    @Expose
    private var email: String? = null

    @SerializedName("dateOfBirth")
    @Expose
    private var dateOfBirth: String? = null

    @SerializedName("phoneNumber")
    @Expose
    private var phoneNumber: String? = null

    @SerializedName("phoneVerified")
    @Expose
    private var phoneVerified: Boolean? = null

    @SerializedName("createdAt")
    @Expose
    private var createdAt: String? = null

    @SerializedName("updatedAt")
    @Expose
    private var updatedAt: String? = null

    @SerializedName("deletedAt")
    @Expose
    private var deletedAt: Any? = null

    @SerializedName("stripe")
    @Expose
    private var stripe: Stripe? = null

    @SerializedName("address")
    @Expose
    private var address: List<Address?>? = null

    @SerializedName("vehicle")
    @Expose
    private var vehicle: List<Vehicle?>? = null

    @SerializedName("cards")
    @Expose
    private var cards: List<Any?>? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getDateOfBirth(): String? {
        return dateOfBirth
    }

    fun setDateOfBirth(dateOfBirth: String?) {
        this.dateOfBirth = dateOfBirth
    }

    fun getPhoneNumber(): String? {
        return phoneNumber
    }

    fun setPhoneNumber(phoneNumber: String?) {
        this.phoneNumber = phoneNumber
    }

    fun getPhoneVerified(): Boolean? {
        return phoneVerified
    }

    fun setPhoneVerified(phoneVerified: Boolean?) {
        this.phoneVerified = phoneVerified
    }

    fun getCreatedAt(): String? {
        return createdAt
    }

    fun setCreatedAt(createdAt: String?) {
        this.createdAt = createdAt
    }

    fun getUpdatedAt(): String? {
        return updatedAt
    }

    fun setUpdatedAt(updatedAt: String?) {
        this.updatedAt = updatedAt
    }

    fun getDeletedAt(): Any? {
        return deletedAt
    }

    fun setDeletedAt(deletedAt: Any?) {
        this.deletedAt = deletedAt
    }

    fun getStripe(): Stripe? {
        return stripe
    }

    fun setStripe(stripe: Stripe?) {
        this.stripe = stripe
    }

    fun getAddress(): List<Address?>? {
        return address
    }

    fun setAddress(address: List<Address?>?) {
        this.address = address
    }

    fun getVehicle(): List<Vehicle?>? {
        return vehicle
    }

    fun setVehicle(vehicle: List<Vehicle?>?) {
        this.vehicle = vehicle
    }

    fun getCards(): List<Any?>? {
        return cards
    }

    fun setCards(cards: List<Any?>?) {
        this.cards = cards
    }

    class Stripe {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("customer")
        @Expose
        var customer: String? = null

        @SerializedName("createdAt")
        @Expose
        var createdAt: String? = null

        @SerializedName("updatedAt")
        @Expose
        var updatedAt: String? = null

        @SerializedName("deletedAt")
        @Expose
        var deletedAt: Any? = null
    }

    class Address {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("street")
        @Expose
        var street: String? = null

        @SerializedName("address")
        @Expose
        var address: String? = null

        @SerializedName("defaultAddress")
        @Expose
        var defaultAddress: Boolean? = null

        @SerializedName("createdAt")
        @Expose
        var createdAt: String? = null

        @SerializedName("updatedAt")
        @Expose
        var updatedAt: String? = null

        @SerializedName("deletedAt")
        @Expose
        var deletedAt: Any? = null
    }

    class Vehicle {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("make")
        @Expose
        var make: String? = null

        @SerializedName("type")
        @Expose
        var type: String? = null

        @SerializedName("year")
        @Expose
        var year: Int? = null

        @SerializedName("transmission")
        @Expose
        var transmission: String? = null

        @SerializedName("licensePlate")
        @Expose
        var licensePlate: String? = null

        @SerializedName("state")
        @Expose
        var state: String? = null

        @SerializedName("createdAt")
        @Expose
        var createdAt: String? = null

        @SerializedName("updatedAt")
        @Expose
        var updatedAt: String? = null

        @SerializedName("deletedAt")
        @Expose
        var deletedAt: Any? = null
    }
}
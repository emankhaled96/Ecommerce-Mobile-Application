package com.emankhaled.ecommerceapp.Model;

public class AdminOrders {

    private String cityName,date,homeAddress,state,time,totalAmount,userPhone,username;

    public AdminOrders() {
    }

    public AdminOrders(String cityName, String date, String homeAddress, String state, String time, String totalAmount, String userPhone, String username) {
        this.cityName = cityName;
        this.date = date;
        this.homeAddress = homeAddress;
        this.state = state;
        this.time = time;
        this.totalAmount = totalAmount;
        this.userPhone = userPhone;
        this.username = username;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

package com.emankhaled.ecommerceapp.Model;

public class AdminOrders {

    private String cityname,date,homeaddress,state,time,totalamount,userphone,username ,orderphone, orderid;

    public AdminOrders() {
    }

    public AdminOrders(String cityname, String date, String homeaddress, String state, String time, String totalamount, String userphone, String username, String orderphone, String orderid) {
        this.cityname = cityname;
        this.date = date;
        this.homeaddress = homeaddress;
        this.state = state;
        this.time = time;
        this.totalamount = totalamount;
        this.userphone = userphone;
        this.username = username;
        this.orderphone = orderphone;
        this.orderid = orderid;
    }

    public AdminOrders(String cityname, String date, String homeaddress, String state, String time, String totalamount, String userphone, String username) {
        this.cityname = cityname;
        this.date = date;
        this.homeaddress = homeaddress;
        this.state = state;
        this.time = time;
        this.totalamount = totalamount;
        this.userphone = userphone;
        this.username = username;
    }

    public String getOrderphone() {
        return orderphone;
    }

    public void setOrderphone(String orderphone) {
        this.orderphone = orderphone;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHomeaddress() {
        return homeaddress;
    }

    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
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

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

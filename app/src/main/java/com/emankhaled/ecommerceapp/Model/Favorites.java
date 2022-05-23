package com.emankhaled.ecommerceapp.Model;

public class Favorites {

    private String pid, pname, price, pimage;

    public Favorites() {
    }

    public Favorites(String pid, String pname, String price, String pimage) {
        this.pid = pid;
        this.pname = pname;
        this.price = price;
        this.pimage = pimage;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }
}
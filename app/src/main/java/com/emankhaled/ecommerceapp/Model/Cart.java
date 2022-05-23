package com.emankhaled.ecommerceapp.Model;

public class Cart {

    private String pid,pname,price,Quantity,discount,pimage;

    public Cart(){


    }

    public Cart(String pid, String pname, String price, String quantity, String discount, String pimage) {
        this.pid = pid;
        this.pname = pname;
        this.price = price;
        Quantity = quantity;
        this.discount = discount;
        this.pimage = pimage;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public Cart(String pid, String pname, String price, String quantity, String discount) {
        this.pid = pid;
        this.pname = pname;
        this.price = price;
        Quantity = quantity;
        this.discount = discount;
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

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}

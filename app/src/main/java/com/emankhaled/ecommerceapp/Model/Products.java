package com.emankhaled.ecommerceapp.Model;

public class Products {

    private String ProductName,Category,Price,Description,Image,Time,Date,Pid,ProductState;

    public Products(){


    }

    public Products(String productName, String category, String price, String description, String image, String time, String date, String pid,String productState) {
        ProductName = productName;
        Category = category;
        Price = price;
        Description = description;
        Image = image;
        Time = time;
        Date = date;
        Pid = pid;
        ProductState=productState;
    }

    public String getProductState() {
        return ProductState;
    }

    public void setProductState(String productState) {
        ProductState = productState;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }
}

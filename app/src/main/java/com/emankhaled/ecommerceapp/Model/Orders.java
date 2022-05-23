package com.emankhaled.ecommerceapp.Model;

public class Orders {

    private String Pid, ProductName, ProductPrice, ProductQuantity, ProductImage;

    public Orders() {
    }

    public Orders(String pid, String productName, String productPrice, String productQuantity, String productImage) {
        Pid = pid;
        ProductName = productName;
        ProductPrice = productPrice;
        ProductQuantity = productQuantity;
        ProductImage = productImage;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public String getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        ProductQuantity = productQuantity;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }
}
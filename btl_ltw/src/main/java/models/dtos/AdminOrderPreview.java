package models.dtos;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class AdminOrderPreview {
    public UUID id;
    public Timestamp created_time;
    public int status;
    public String address;
    public String phonenum;
    public String buyer_name;
    public int totalProduct;
    public int totalPrice;
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public Timestamp getCreated_time() {
        return created_time;
    }
    public void setCreated_time(Timestamp created_time) {
        this.created_time = created_time;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhonenum() {
        return phonenum;
    }
    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }
    public String getBuyer_name() {
        return buyer_name;
    }
    public void setBuyer_name(String buyer_name) {
        this.buyer_name = buyer_name;
    }
    public int getTotalProduct() {
        return totalProduct;
    }
    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }
    public int getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCreate_time_string(){
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(created_time);
    }
}

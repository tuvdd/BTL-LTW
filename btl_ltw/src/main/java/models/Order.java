package models;
import java.sql.Timestamp;
import java.util.UUID;

public class Order{
    public UUID id;
    public Timestamp created_time;
    public int status;
    public String address;
    public String phonenum;
    public String buyer_name;
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

}

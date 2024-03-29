package models;

import java.sql.Timestamp;
import java.util.UUID;

public class User {
    public UUID id;
    public String name;
    public String phonenum;
    public String email;
    public boolean status;
    public Timestamp created_time;
    public Timestamp last_update_time;
    public String username;
    public String password;

    public void set(UUID id, String name, String phonenum, String email, boolean status,
            Timestamp created_time, Timestamp last_update_time, String username, String password) {
        this.id = id;
        this.name = name;
        this.phonenum = phonenum;
        this.email = email;
        this.status = status;
        this.created_time = created_time;
        this.last_update_time = last_update_time;
        this.username = username;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Timestamp created_time) {
        this.created_time = created_time;
    }

    public Timestamp getLast_update_time() {
        return last_update_time;
    }

    public void setLast_update_time(Timestamp last_update_time) {
        this.last_update_time = last_update_time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
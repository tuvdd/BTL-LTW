package models.dtos;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.sql.Timestamp;
import java.util.UUID;

public class AdminBookView {
    public UUID id;
    public String name;
    public byte[] image;
    public String author;
    public int release_year;
    public UUID category_id;
    public String category_name;
    public double price;
    public double promote_price;
    public int quantity;
    public String description;
    public String sub_description;
    public boolean status;
    public Timestamp create_time;
    public UUID create_by;
    public String create_by_name;
    public Timestamp last_update_time;
    public UUID last_update_by;
    public String last_update_by_name;
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
    public byte[] getImage() {
        return image;
    }
    public String getImageBase64() {
        return Base64.getEncoder().encodeToString(image);
    }
    public void setImage(byte[] image) {
        this.image = image;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getRelease_year() {
        return release_year;
    }
    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }
    public UUID getCategory_id() {
        return category_id;
    }
    public void setCategory_id(UUID category_id) {
        this.category_id = category_id;
    }
    public String getCategory_name() {
        return category_name;
    }
    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getPromote_price() {
        return promote_price;
    }
    public void setPromote_price(double promote_price) {
        this.promote_price = promote_price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getSub_description() {
        return sub_description;
    }
    public void setSub_description(String sub_description) {
        this.sub_description = sub_description;
    }
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public Timestamp getCreate_time() {
        return create_time;
    }
    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }
    public UUID getCreate_by() {
        return create_by;
    }
    public void setCreate_by(UUID create_by) {
        this.create_by = create_by;
    }
    public String getCreate_by_name() {
        return create_by_name;
    }
    public void setCreate_by_name(String create_by_name) {
        this.create_by_name = create_by_name;
    }
    public Timestamp getLast_update_time() {
        return last_update_time;
    }
    public void setLast_update_time(Timestamp last_update_time) {
        this.last_update_time = last_update_time;
    }
    public UUID getLast_update_by() {
        return last_update_by;
    }
    public void setLast_update_by(UUID last_update_by) {
        this.last_update_by = last_update_by;
    }
    public String getLast_update_by_name() {
        return last_update_by_name;
    }
    public void setLast_update_by_name(String last_update_by_name) {
        this.last_update_by_name = last_update_by_name;
    }
    public String getCreate_time_string(){
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(create_time);
    }
    public String getLast_update_time_string(){
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(last_update_time);
    }
}

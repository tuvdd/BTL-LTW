package models;

import java.util.Base64;
import java.sql.Timestamp;
import java.util.UUID;

import utils.JsonUtils;

public class Book extends Table {
    public String name;
    public byte[] image;
    public String author;
    public int release_year;
    public UUID category_id;
    public double price;
    public double promote_price;
    public int quantity;
    public String description;
    public String sub_description;
    public int status;
    public Timestamp create_time;
    public UUID create_by;
    public Timestamp last_update_time;
    public UUID last_update_by;

    public Book() {
        super();
        TableName = "books";
    }

    public static String GetTableName() {
        return "books";
    }

    public void set(UUID id, String name, byte[] image, String author, int release_year, UUID category_id, double price,
            double promote_price,
            int quantity, String description, String sub_description, int status, Timestamp create_time, UUID create_by,
            Timestamp last_update_time, UUID last_update_by) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.author = author;
        this.release_year = release_year;
        this.category_id = category_id;
        this.price = price;
        this.promote_price = promote_price;
        this.quantity = quantity;
        this.description = description;
        this.sub_description = sub_description;
        this.status = status;
        this.create_time = create_time;
        this.create_by = create_by;
        this.last_update_time = last_update_time;
        this.last_update_by = last_update_by;
    }

    @Override
    public String Get_Insert_Fields_SQL() {
        return ""
                + "id, "
                + "name, "
                + "image, "
                + "author, "
                + "release_year, "
                + "category_id, "
                + "price, "
                + "promote_price, "
                + "quantity, "
                + "description, "
                + "sub_description, "
                + "status, "
                + "create_time, "
                + "create_by, "
                + "last_update_time, "
                + "last_update_by ";
    }

    @Override
    public String Get_Insert_Values_SQL() {
        return ""
                + "gen_random_uuid(), "
                + "'" + name + "', "
                + "? , "
                + "'" + author + "', "
                + "" + release_year + ", "
                + "'" + category_id + "', "
                + "" + price + ", "
                + "" + promote_price + ", "
                + "" + quantity + ", "
                + "'" + description + "', "
                + "'" + sub_description + "', "
                + "" + status + ", "
                + "'" + create_time + "', "
                + "'" + create_by + "', "
                + "'" + last_update_time + "', "
                + "'" + last_update_by + "' ";
    }

    @Override
    public String Get_Update_Values_SQL() {
        return ""
                + "id = '" + id + "', "
                + "name = '" + name + "', "
                + (image == null || image.length == 0 ? "" : "image = ? , ")
                + "author = '" + author + "', "
                + "release_year = " + release_year + ", "
                + "category_id = '" + category_id + "', "
                + "price = " + price + ", "
                + "promote_price = " + promote_price + ", "
                + "quantity = " + quantity + ", "
                + "description = '" + description + "', "
                + "sub_description = '" + sub_description + "', "
                + "status = " + status + ", "
                + "last_update_time = '" + last_update_time + "', "
                + "last_update_by = '" + last_update_by + "' ";
    }

    public String To_Json_String() {
        return "{" +
                JsonUtils.PropToJson("id", id, true) +
                JsonUtils.PropToJson("name", name, true) +
                JsonUtils.PropToJson("author", author, true) +
                JsonUtils.PropToJson("release_year", release_year, false) +
                JsonUtils.PropToJson("category_id", category_id, true) +
                JsonUtils.PropToJson("price", price, false) +
                JsonUtils.PropToJson("promote_price", promote_price, false) +
                JsonUtils.PropToJson("quantity", quantity, true) +
                JsonUtils.PropToJson("description", description, true) +
                JsonUtils.PropToJson("sub_description", sub_description, true) +
                JsonUtils.PropToJson("status", status, false) +
                JsonUtils.PropToJson("create_time", create_time, true) +
                JsonUtils.PropToJson("create_by", create_by, true) +
                JsonUtils.PropToJson("last_update_time", last_update_time, true) +
                JsonUtils.PropToJson("last_update_by", last_update_by, true) +
                "}";
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
}

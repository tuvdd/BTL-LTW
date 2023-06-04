package models;

import java.util.UUID;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Comment {
    private UUID id;
    private UUID user_id;
    private UUID book_id;
    private int rate;
    private String comment_text;
    private Timestamp create_at;
    private String username;
    public Comment() {
    }

    public UUID getId() {
        return id;
    }

    public void setUserID(UUID userID) {
        this.user_id =  userID;
    }

    public UUID getUserID() {
        return user_id;
    }

    public void setBookId(UUID id) {
        this.book_id = id;
    }

    public UUID getBookId() {
        return book_id;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setComment(String comment) {
        this.comment_text = comment;
    }

    public String getComment() {
        return comment_text;
    }

    public void setCreate_at(Timestamp time) {
        this.create_at = time;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public String getStringCreate_at() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        // Chuyển đổi Timestamp thành Date
        Date date = new Date(this.create_at.getTime());

        // Chuyển đổi Date thành chuỗi
        String formattedDateTime = dateFormat.format(date);
        return formattedDateTime;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void set(UUID id, UUID user_id, UUID book_id, int rate, String comment, Timestamp create_at) {
        this.id = id;
        this.book_id = book_id;
        this.rate = rate;
        this.comment_text = comment;
        this.create_at = create_at;
        this.user_id = user_id;
        this.username = "";
    }

    public void setPropertyFromResultSet(String username, String comment_text, int rate, Timestamp create_at) {
        // this.id = UUID.fromString("");
        // this.book_id = UUID.fromString("");
        this.rate = rate;
        this.comment_text = comment_text;
        this.create_at = create_at;
        // this.user_id = UUID.fromString("");
        this.username = username;
    }
	
}
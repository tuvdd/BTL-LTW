package models;

import java.util.UUID;
import java.sql.Timestamp;

public class Comment {
    private UUID id;
    private UUID book_id;
    private int rate;
    private String comment;
    private Timestamp create_at;

    public Comment() {
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
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
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setCreate_at(Timestamp time) {
        this.create_at = time;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void set(UUID id, UUID book_id, int rate, String comment, Timestamp create_at) {
        this.id = id;
        this.book_id = book_id;
        this.rate = rate;
        this.comment = comment;
        this.create_at = create_at;
    }
	
}
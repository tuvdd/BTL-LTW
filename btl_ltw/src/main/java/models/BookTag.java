package models;

import java.util.UUID;

public class BookTag{
    public UUID id;
    public UUID book_id;
    public UUID tag_id;

    public void set(UUID book_id, UUID id, UUID tag_id) {
        this.book_id = book_id;
        this.id = id;
        this.tag_id = tag_id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getBook_id() {
        return book_id;
    }

    public void setBook_id(UUID book_id) {
        this.book_id = book_id;
    }

    public UUID getTag_id() {
        return tag_id;
    }

    public void setTag_id(UUID tag_id) {
        this.tag_id = tag_id;
    }

}

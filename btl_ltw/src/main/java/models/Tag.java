package models;
import java.util.UUID;


public class Tag {
    public UUID id;
    public String tag_name;

    public void set(UUID id, String tag_name) {
        this.id = id;
        this.tag_name = tag_name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

}
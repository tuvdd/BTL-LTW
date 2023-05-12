package models.dtos;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import models.BookImage;
import models.Tag;

public class BookFullDetail {
    public UUID id;
    public String name;
    public String author;
    public int release_year;
    public UUID category_id;
    public String category_name;
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
    public List<BookImage> listImages;
    public List<Tag> listTags;
}

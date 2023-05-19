package repositories.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import models.Tag;

public interface ITagRepo extends IRepo<Tag>{
    List<Tag> GetsByBookId(UUID id) throws SQLException;
}

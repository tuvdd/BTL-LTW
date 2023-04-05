package demo.services.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import demo.models.BookImage;

public interface IBookImageRepo extends IRepo<BookImage>{
    List<BookImage> GetListBookImageByBookId(UUID id) throws SQLException;
    List<BookImage> GetListBookImageByBookId(String id) throws SQLException;
}

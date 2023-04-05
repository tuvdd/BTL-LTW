package demo.services.interfaces;

import java.sql.SQLException;
import java.util.List;

import demo.models.Book;

public interface IBookRepo extends IRepo<Book> {
    List<Book> GetListBookByCategoryID(String uuid, int page, int size) throws SQLException;

    List<Book> GetListBookByPrice(double fromMoney, double toMoney, int page, int size) throws SQLException;

    List<Book> GetListBookByBookTagString(String tagString) throws SQLException;
}

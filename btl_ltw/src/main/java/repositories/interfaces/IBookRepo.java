package repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

import models.Book;
import models.dtos.AdminBookView;

public interface IBookRepo extends IRepo<Book> {
    List<Book> GetListBookByCategoryID(String uuid, int page, int size) throws SQLException;

    List<Book> GetListBookByPrice(double fromMoney, double toMoney, int page, int size) throws SQLException;

    List<Book> GetListBookByBookTagString(String tagString) throws SQLException;

    List<AdminBookView> GetsAdminBookView(String PaginSQL) throws SQLException;
}

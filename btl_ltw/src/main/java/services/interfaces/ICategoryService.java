package services.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import models.Category;

public interface ICategoryService extends IBaseService<Category>{
    Category Get(UUID id)throws SQLException;
    List<Category> Gets()throws SQLException;
    List<Category> Gets(int page, int size) throws SQLException;
}

package services.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import models.Category;

public interface ICategoryService {
    Category Get(UUID id)throws SQLException;
    List<Category> Gets()throws SQLException;
}

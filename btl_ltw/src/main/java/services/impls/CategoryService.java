package services.impls;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import models.Category;
import repositories.impls.CategoryRepo;
import repositories.interfaces.ICategoryRepo;
import repositories.utils.SQLInjection;
import services.interfaces.ICategoryService;

public class CategoryService implements ICategoryService {
    private ICategoryRepo categoryRepo;

    public CategoryService() {
        categoryRepo = new CategoryRepo();
    }

    @Override
    public Category Get(UUID id) throws SQLException {
        Category result = categoryRepo.Get(id);
        return result;
    }

    @Override
    public List<Category> Gets() throws SQLException {
        List<Category> result = categoryRepo.Gets("", "");
        return result;
    }

    @Override
    public List<Category> Gets(int page, int size) throws SQLException {
        List<Category> result = categoryRepo.Gets("", SQLInjection.PAGINSQL(page, size, null));
        return result;
    }

    @Override
    public String To_Json_String(Category obj) throws SQLException {
        return obj.To_Json_String();
    }

    @Override
    public String To_Json_String(List<Category> listObj) throws SQLException {
        String jsonResponse = "[";
        for (Category category : listObj) {
            jsonResponse += To_Json_String(category) +",";
        }
        jsonResponse += "]";
        return jsonResponse;
    }

    @Override
    public int Add(Category category) throws SQLException {
        return categoryRepo.Add(category);
    }
}

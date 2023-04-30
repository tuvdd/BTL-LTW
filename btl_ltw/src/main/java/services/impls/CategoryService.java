package services.impls;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import models.Category;
import repositories.impls.CategoryRepo;
import repositories.interfaces.ICategoryRepo;
import services.interfaces.ICategoryService;

public class CategoryService implements ICategoryService{
    private ICategoryRepo categoryRepo;

    public CategoryService() {
        categoryRepo = new CategoryRepo();
    }

    @Override
    public List<Category> Gets() throws SQLException {
        List<Category> result = categoryRepo.Gets("", "");
        return result;
    }

    @Override
    public Category Get(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Get'");
    }
    
}

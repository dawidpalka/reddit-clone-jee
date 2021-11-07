package pl.dawidpalka.readstack.domain.api;

import pl.dawidpalka.readstack.domain.category.Category;
import pl.dawidpalka.readstack.domain.category.CategoryDao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CategoryService {
    private final CategoryDao categoryDao = new CategoryDao();

    public List<CategoryName> findAllCategoryNames() {
        return categoryDao.findAll()
                .stream()
                .map(this::mapCategoryName)
                .collect(Collectors.toList());
    }

    public Optional<CategoryFullInfo> findById(int categoryId) {
        return categoryDao.findById(categoryId)
                .map(this::mapCategoryFullName);
    }

    private CategoryName mapCategoryName(Category category) {
        return new CategoryName(category.getId(), category.getName());
    }

    private CategoryFullInfo mapCategoryFullName(Category category) {
        return new CategoryFullInfo(category.getId(), category.getName(), category.getDescription());
    }
}

package edu.shopify.bo.custom.impl;

import edu.shopify.bo.custom.CategoryBo;
import edu.shopify.dao.DaoFactory;
import edu.shopify.dao.custom.CategoryDao;
import edu.shopify.dto.Category;
import edu.shopify.entity.CategoryEntity;
import edu.shopify.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class CategoryBoImpl implements CategoryBo {
    private final CategoryDao categoryDao = DaoFactory.getInstance().getDao(DaoType.CATEGORY);


    @Override
    public Boolean saveCategory(Category dto) throws Exception {
        return categoryDao.add(new ModelMapper().map(dto, CategoryEntity.class));
    }


    @Override
    public ObservableList<Category> getAllCategories() throws Exception{
        ObservableList<Category> categoryList = FXCollections.observableArrayList();
        for (CategoryEntity categoryEntity : categoryDao.getAll()) {
            categoryList.add(new ModelMapper().map(categoryEntity,Category.class));
        }

        return categoryList;
    }

    @Override
    public Category searchCategory(String id) throws Exception {
        CategoryEntity category = categoryDao.searchById(id);
        if (category != null)
            return new ModelMapper().map(categoryDao.searchById(id), Category.class);
        return null;
    }


}

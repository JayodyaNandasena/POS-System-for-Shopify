package edu.shopify.bo.custom;

import edu.shopify.bo.SuperBo;
import edu.shopify.dto.Category;
import edu.shopify.dto.Employee;
import javafx.collections.ObservableList;

public interface CategoryBo extends SuperBo {
    Boolean saveCategory(Category dto) throws Exception;
    ObservableList<Category> getAllCategories() throws Exception;
    Category searchCategory(String id) throws Exception;
}

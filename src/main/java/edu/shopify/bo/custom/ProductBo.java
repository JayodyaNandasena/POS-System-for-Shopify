package edu.shopify.bo.custom;

import edu.shopify.bo.SuperBo;
import edu.shopify.dto.Employee;
import edu.shopify.dto.Product;
import javafx.collections.ObservableList;

public interface ProductBo extends SuperBo {
    Boolean saveProduct(Product dto) throws Exception;
    ObservableList<Product> getAllProducts();
    Product searchProduct(String id) throws Exception;
    Boolean updateProduct(Product product);
    Boolean deleteProduct(String id);
    Boolean readdProduct(String id);
}

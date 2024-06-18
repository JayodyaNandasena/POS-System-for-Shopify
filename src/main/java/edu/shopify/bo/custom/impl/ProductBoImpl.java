package edu.shopify.bo.custom.impl;

import edu.shopify.bo.custom.EmployeeBo;
import edu.shopify.bo.custom.ProductBo;
import edu.shopify.dao.DaoFactory;
import edu.shopify.dao.custom.EmployeeDao;
import edu.shopify.dao.custom.ProductDao;
import edu.shopify.dto.Employee;
import edu.shopify.dto.Product;
import edu.shopify.entity.EmployeeEntity;
import edu.shopify.entity.ProductEntity;
import edu.shopify.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class ProductBoImpl implements ProductBo {
    private ProductDao productDao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);

    public ProductBoImpl() throws Exception {
    }

    @Override
    public Boolean saveProduct(Product dto) throws Exception {
        return productDao.add(new ModelMapper().map(dto, ProductEntity.class));
    }

    @Override
    public ObservableList<Product> getAllProducts() {
        ObservableList<Product> productList = FXCollections.observableArrayList();
        for (ProductEntity productEntity : productDao.getAll()) {
            productList.add(new ModelMapper().map(productEntity,Product.class));
        }

        return productList;
    }

    @Override
    public Product searchProduct(String id) throws Exception {
        ProductEntity product = productDao.searchById(id);
        if (product != null)
            return new ModelMapper().map(productDao.searchById(id), Product.class);
        return null;

    }

    @Override
    public Boolean updateProduct(Product product) {
        return productDao.update(new ModelMapper().map(product, ProductEntity.class));
    }

    @Override
    public Boolean deleteProduct(String id) {
        return productDao.delete(id);
    }

    @Override
    public Boolean readdProduct(String id) {
        return productDao.readd(id);
    }

    public static byte[] toByteArray(Blob blob) throws SQLException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (InputStream is = blob.getBinaryStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            throw new SQLException("Failed to convert Blob to byte array", e);
        }
        return baos.toByteArray();
    }

//    public static Blob toBlob(byte[] bytes, Connection connection) throws SQLException {
//        return connection.createBlob().setBytes(1, bytes);
//    }

}

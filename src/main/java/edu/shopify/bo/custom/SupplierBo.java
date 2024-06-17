package edu.shopify.bo.custom;

import edu.shopify.bo.SuperBo;
import edu.shopify.dto.Supplier;
import javafx.collections.ObservableList;

public interface SupplierBo extends SuperBo {
    Boolean saveSupplier(Supplier dto) throws Exception;
    ObservableList<Supplier> getAllSuppliers() throws Exception;
    Supplier searchSupplier(String id) throws Exception;
    Boolean updateSupplier(Supplier dto);
    Boolean deleteSupplier(String id);
    Boolean readdSupplier(String id);
}

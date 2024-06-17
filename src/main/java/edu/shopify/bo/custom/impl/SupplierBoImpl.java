package edu.shopify.bo.custom.impl;

import edu.shopify.bo.custom.SupplierBo;
import edu.shopify.dao.DaoFactory;
import edu.shopify.dao.custom.SupplierDao;
import edu.shopify.dto.Supplier;
import edu.shopify.entity.SupplierEntity;
import edu.shopify.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class SupplierBoImpl implements SupplierBo {
    private final SupplierDao supplierDao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);


    @Override
    public Boolean saveSupplier(Supplier dto) throws Exception {
        return supplierDao.add(new ModelMapper().map(dto, SupplierEntity.class));
    }


    @Override
    public ObservableList<Supplier> getAllSuppliers() throws Exception{
        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
        for (SupplierEntity supplierEntity : supplierDao.getAll()) {
            supplierList.add(new ModelMapper().map(supplierEntity,Supplier.class));
        }

        return supplierList;
    }

    @Override
    public Supplier searchSupplier(String id) throws Exception {
        SupplierEntity supplier = supplierDao.searchById(id);
        if (supplier != null)
            return new ModelMapper().map(supplierDao.searchById(id), Supplier.class);
        return null;

    }

    @Override
    public Boolean updateSupplier(Supplier dto) {
        return supplierDao.update(new ModelMapper().map(dto, SupplierEntity.class));
    }

    @Override
    public Boolean deleteSupplier(String id) {
        return supplierDao.delete(id);
    }

    @Override
    public Boolean readdSupplier(String id) {
        return supplierDao.readd(id);
    }

}

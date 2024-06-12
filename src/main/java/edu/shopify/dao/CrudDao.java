package edu.shopify.dao;

import javafx.collections.ObservableList;

public interface CrudDao <T> extends SuperDao{
    boolean add(T dto);
    ObservableList<T> getAll();
    T searchById(String id);
    boolean update(T dto);
    boolean delete(String id);
}

package edu.shopify.dao;


import java.util.List;

public interface CrudDao <T> extends SuperDao{
    boolean add(T dto);
    List<T> getAll();
    T searchById(String id);
    boolean update(T dto);
    boolean delete(String id);
}

package edu.shopify.dao;


import java.util.List;

public interface CrudDao <T> extends SuperDao{
    Boolean add(T dto);
    List<T> getAll();
    T searchById(String id);
    Boolean update(T dto);
    Boolean delete(String id);
}

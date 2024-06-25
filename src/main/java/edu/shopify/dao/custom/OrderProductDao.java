package edu.shopify.dao.custom;

import edu.shopify.dao.CrudDao;
import edu.shopify.entity.OrderProductEntity;

public interface OrderProductDao extends CrudDao<OrderProductEntity> {
    Boolean readd(String id);
}

package edu.shopify.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class OrderProductEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "orderId")
    private OrderEntity order;

    @Id
    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductEntity product;

    private Integer itemQuantity;
}

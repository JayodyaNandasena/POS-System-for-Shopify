package edu.shopify.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.sql.Blob;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class ProductEntity {
    @Id
    private String id;
    private String name;
    private String size;
    @ManyToOne
    private CategoryEntity category;
    @ManyToOne
    private SupplierEntity supplier;
    @Lob
    private byte[] image;
    private Double retailPrice;
    private Double wholesalePrice;
    private Integer qtyInStock;
    private Boolean isSelling;
}

package edu.shopify.dto;

import lombok.*;

import java.sql.Blob;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    private String id;
    private String name;
    private String size;
    private Category category;
    private Supplier supplier;
    //private byte[] image;
    private Double retailPrice;
    private Double wholesalePrice;
    private Integer qtyInStock;
    private Boolean isSelling;
}

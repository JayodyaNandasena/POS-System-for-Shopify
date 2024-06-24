package edu.shopify.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;
import java.util.List;

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

//    @ManyToOne
//    @JoinColumn(name = "employeeId")
//    private Employee employee;

//    @OneToMany(mappedBy = "product")
//    private List<OrderProductEntity> orders;
}

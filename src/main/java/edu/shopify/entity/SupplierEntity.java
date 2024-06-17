package edu.shopify.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class SupplierEntity {
    @Id
    private String supplierId;
    private String name;
    private String company;
    private String nic;
    private String mobile;
    private  Boolean isActive;
}

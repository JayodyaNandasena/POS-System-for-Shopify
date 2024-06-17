package edu.shopify.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Supplier {
    private String supplierId;
    private String name;
    private String nic;
    private String mobile;
    private String email;
    private String company;
    private Boolean isActive;
}

package edu.shopify.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class SupplierTM {
    private String supplierId;
    private String name;
    private String company;
    private String nic;
    private String mobile;
    private String email;

}

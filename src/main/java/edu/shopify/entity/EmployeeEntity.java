package edu.shopify.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class EmployeeEntity {
    @Id
    private String employeeId;
    private String firstName;
    private String lastName;
    private String address;
    private String mobile;
    private String email;
    private String password;
    private Boolean isActive;

    @OneToMany(mappedBy = "employee")
    private List<OrderEntity> orders;

//    @OneToMany(mappedBy = "employee")
//    private List<ProductEntity> managedProducts;
}

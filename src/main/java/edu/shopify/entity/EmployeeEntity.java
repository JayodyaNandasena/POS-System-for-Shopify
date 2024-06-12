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
public class EmployeeEntity {
    @Id
    private String employeeId;
    private String firstName;
    private String lastName;
    private String address;
    private String mobile;
    private String email;
    private String password;
    private  Boolean isActive;
}

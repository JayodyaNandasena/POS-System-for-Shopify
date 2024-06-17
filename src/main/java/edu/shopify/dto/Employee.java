package edu.shopify.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String address;
    private String mobile;
    private String email;
    private String password;
    private Boolean isActive;
}

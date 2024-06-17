package edu.shopify.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class EmployeeTM {
    private String employeeId;
    private String name;
    private String address;
    private String mobile;
    private String email;
}

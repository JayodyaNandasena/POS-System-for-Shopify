package edu.shopify.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
}

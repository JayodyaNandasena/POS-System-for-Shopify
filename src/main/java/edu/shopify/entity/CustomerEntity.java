package edu.shopify.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class CustomerEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String mobile;
    @Column(unique = true)
    private String email;

//    @OneToMany(mappedBy = "customer")
//    private List<OrderEntity> orders;
}

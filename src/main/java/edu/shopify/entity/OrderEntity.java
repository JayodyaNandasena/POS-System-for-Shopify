package edu.shopify.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class OrderEntity {
    @Id
    private String orderId;
    private Date date;
    private String time;
    private Double orderPrice;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private EmployeeEntity employee;

//    @OneToMany(mappedBy = "order")
//    private List<OrderProductEntity> orderProducts;
}

package edu.shopify.dto.tm;

import edu.shopify.dto.Category;
import edu.shopify.dto.Supplier;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CartTm {
    private String id;
    private String name;
    private Integer qty;
    private Double unitPrice;
    private Double amount;
}

package hm.springframework.spring6restmvc.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Beverage {
    private UUID id;
    private Integer version;
    private String beverageName;
    private BeverageStyle beverageStyle;
    private String upc;
    private Integer quantityOnHand;
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

}

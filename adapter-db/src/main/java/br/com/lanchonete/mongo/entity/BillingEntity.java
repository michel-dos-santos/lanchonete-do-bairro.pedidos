package br.com.lanchonete.mongo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingEntity {

    @Id
    @GeneratedValue
    private String id;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;
    @Column(length = 14, scale = 2)
    private BigDecimal totalPrice;
    @Enumerated(EnumType.STRING)
    private StatusPaymentType status;
    @Enumerated(EnumType.STRING)
    private BillingFormType billingFormType;

}

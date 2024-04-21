package br.com.lanchonete.mongo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class OrderEntity {

    @Transient
    public static final String SEQUENCE_NAME = "orders_sequence";

    @Id
    @GeneratedValue
    private String id;
    @Column(columnDefinition = "uuid")
    private UUID externalId;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;
    @Column(length = 10)
    private long number;
    @Enumerated(EnumType.STRING)
    private StatusType status;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_billing_id")
    private BillingEntity billing;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_client_id")
    private ClientEntity client;
    @OneToMany(mappedBy="order", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<OrderItemEntity> orderItems;

}

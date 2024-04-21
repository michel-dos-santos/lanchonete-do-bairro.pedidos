package br.com.lanchonete.rest.services.billing.dto;

import br.com.lanchonete.model.BillingFormType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    private String orderId;
    private BigDecimal totalPrice;
    private BillingFormType billingFormType;

}

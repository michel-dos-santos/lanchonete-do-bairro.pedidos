package br.com.lanchonete.rest.services.product.dto;

import br.com.lanchonete.model.StatusActiveType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductDTO {

    private String id;
    private String name;
    private BigDecimal unitPrice;
    private ResponseCategoryDTO category;
    private StatusActiveType status;

}

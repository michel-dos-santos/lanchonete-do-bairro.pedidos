package br.com.lanchonete.rest.mappers.outputs;

import br.com.lanchonete.model.Billing;
import br.com.lanchonete.rest.services.billing.dto.RequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BillingOutputMapper {

    @Autowired
    private ModelMapper modelMapper;

    public RequestDTO mapBillingRequestDTOFromBilling(Billing billing) {
        RequestDTO requestDTO = modelMapper.map(billing, RequestDTO.class);
        requestDTO.setBillingOrderId(billing.getId().toString());
        return requestDTO;
    }

}

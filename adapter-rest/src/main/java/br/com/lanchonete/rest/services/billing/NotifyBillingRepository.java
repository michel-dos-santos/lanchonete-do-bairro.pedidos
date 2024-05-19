package br.com.lanchonete.rest.services.billing;

import br.com.lanchonete.model.Billing;
import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.NotifyBillingHubRepository;
import br.com.lanchonete.rest.mappers.outputs.BillingOutputMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NotifyBillingRepository implements NotifyBillingHubRepository {

    @Autowired
    private BillingServiceFeignClient serviceFeignClient;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private BillingOutputMapper billingOutputMapper;

    @Override
    public void sendNotification(Billing billing, UUID orderId) {
        logRepository.info(NotifyBillingRepository.class, LogCode.LogCodeInfo._0011);
        serviceFeignClient.billing(billingOutputMapper.mapBillingRequestDTOFromBilling(billing, orderId));
        logRepository.info(NotifyBillingRepository.class, LogCode.LogCodeInfo._0012);
    }

}
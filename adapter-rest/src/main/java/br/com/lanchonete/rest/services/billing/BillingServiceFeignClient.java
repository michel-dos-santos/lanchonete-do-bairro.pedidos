package br.com.lanchonete.rest.services.billing;

import br.com.lanchonete.rest.services.billing.dto.RequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "billingServiceFeignClient", url = "${api.billing.url}", fallbackFactory = BillingServiceFallback.class)
public interface BillingServiceFeignClient {

    @PostMapping()
    void billing(@RequestBody RequestDTO requestDTO);

}

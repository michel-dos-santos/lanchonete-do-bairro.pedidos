package br.com.lanchonete.rest.services.clients;

import br.com.lanchonete.rest.services.clients.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(value = "clientServiceFeignClient", url = "${api.clients.url}", fallbackFactory = ClientServiceFallback.class)
public interface ClientServiceFeignClient {

    @GetMapping(value = "{id}")
    ResponseDTO getClientById(@PathVariable UUID id);

}

package br.com.lanchonete.rest.services.product;

import br.com.lanchonete.rest.services.product.dto.ResponseProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(value = "productServiceFeignClient", url = "${api.products.url}", fallbackFactory = ProductServiceFallback.class)
public interface ProductServiceFeignClient {

    @GetMapping(value = "{id}")
    ResponseProductDTO getProductById(@PathVariable UUID id);

}

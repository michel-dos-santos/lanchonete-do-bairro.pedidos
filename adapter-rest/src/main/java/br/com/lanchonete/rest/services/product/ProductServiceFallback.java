package br.com.lanchonete.rest.services.product;

import br.com.lanchonete.port.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceFallback implements FallbackFactory<ProductServiceFeignClient> {

    @Autowired
    private LogRepository logRepository;

    @Override
    public ProductServiceFeignClient create(Throwable cause) {
        //logRepository.info(cause.getMessage());

        //try {
        //    throw new ServiceException(new ErrorResponse(cause.getMessage()));
        //} catch (IllegalArgumentException e) {
        //    throw new TokenServiceUnavailableException();
        //}
        return null;
    }
}

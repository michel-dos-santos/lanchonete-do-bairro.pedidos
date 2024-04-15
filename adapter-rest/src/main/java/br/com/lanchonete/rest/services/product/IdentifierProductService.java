package br.com.lanchonete.rest.services.product;

import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.model.Product;
import br.com.lanchonete.port.repository.IdentifierProductRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.rest.services.product.dto.ResponseProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdentifierProductService implements IdentifierProductRepository {

    @Autowired
    private ProductServiceFeignClient serviceFeignClient;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LogRepository logRepository;

    @Override
    public Product identifierById(UUID id) {
        logRepository.info(IdentifierProductService.class, LogCode.LogCodeInfo._0032);
        ResponseProductDTO productById = serviceFeignClient.getProductById(id);
        Product product = modelMapper.map(productById, Product.class);
        product.setCatalogProductId(id);
        product.setId(null);
        product.getCategory().setCatalogCategoryId(product.getCategory().getId());
        product.getCategory().setId(null);
        logRepository.info(IdentifierProductService.class, LogCode.LogCodeInfo._0033);
        return product;
    }
}
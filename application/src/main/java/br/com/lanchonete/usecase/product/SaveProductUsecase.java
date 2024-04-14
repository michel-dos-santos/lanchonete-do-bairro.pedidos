package br.com.lanchonete.usecase.product;

import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.model.Product;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.ProductRepository;
import br.com.lanchonete.port.usecase.product.SaveProduct;

public class SaveProductUsecase implements SaveProduct {

    private final LogRepository logRepository;
    private final ProductRepository productRepository;

    public SaveProductUsecase(ProductRepository productRepository, LogRepository logRepository) {
        this.logRepository = logRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        logRepository.info(SaveProductUsecase.class, LogCode.LogCodeInfo._0006);
        Product productSaved = productRepository.save(product);
        logRepository.info(SaveProductUsecase.class, LogCode.LogCodeInfo._0007);
        return productSaved;
    }
}

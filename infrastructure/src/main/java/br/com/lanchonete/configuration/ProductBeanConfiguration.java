package br.com.lanchonete.configuration;

import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.ProductRepository;
import br.com.lanchonete.usecase.product.SaveProductUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductBeanConfiguration {

    @Bean
    SaveProductUsecase saveProduct(ProductRepository productRepository, LogRepository logRepository) {
        return new SaveProductUsecase(productRepository, logRepository);
    }

}

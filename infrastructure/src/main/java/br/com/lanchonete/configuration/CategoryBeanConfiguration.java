package br.com.lanchonete.configuration;

import br.com.lanchonete.port.repository.CategoryRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.usecase.category.SaveCategoryUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryBeanConfiguration {

    @Bean
    SaveCategoryUsecase saveCategory(CategoryRepository productRepository, LogRepository logRepository) {
        return new SaveCategoryUsecase(productRepository, logRepository);
    }

}

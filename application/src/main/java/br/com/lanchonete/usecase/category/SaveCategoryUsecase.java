package br.com.lanchonete.usecase.category;

import br.com.lanchonete.model.Category;
import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.port.repository.CategoryRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.usecase.category.SaveCategory;

public class SaveCategoryUsecase implements SaveCategory {

    private final LogRepository logRepository;
    private final CategoryRepository categoryRepository;

    public SaveCategoryUsecase(CategoryRepository categoryRepository, LogRepository logRepository) {
        this.logRepository = logRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        logRepository.info(SaveCategoryUsecase.class, LogCode.LogCodeInfo._0009);
        Category categorySaved = categoryRepository.save(category);
        logRepository.info(SaveCategoryUsecase.class, LogCode.LogCodeInfo._0010);
        return categorySaved;
    }
}

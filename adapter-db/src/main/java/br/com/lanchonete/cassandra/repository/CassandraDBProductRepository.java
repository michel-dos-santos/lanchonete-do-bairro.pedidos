package br.com.lanchonete.cassandra.repository;

import br.com.lanchonete.cassandra.entity.CategoryEntity;
import br.com.lanchonete.cassandra.entity.ProductEntity;
import br.com.lanchonete.model.Product;
import br.com.lanchonete.port.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CassandraDBProductRepository implements ProductRepository {

    private final SpringDataCassandraProductRepository productRepository;
    private final SpringDataCassandraCategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CassandraDBProductRepository(SpringDataCassandraProductRepository productRepository, SpringDataCassandraCategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public Product save(Product product) {
        ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);
        CategoryEntity categoryEntity = categoryRepository.findByName(product.getCategory().getName()).get();
        productEntity.setCategory(categoryEntity);
        return modelMapper.map(productRepository.save(productEntity), Product.class);
    }

}

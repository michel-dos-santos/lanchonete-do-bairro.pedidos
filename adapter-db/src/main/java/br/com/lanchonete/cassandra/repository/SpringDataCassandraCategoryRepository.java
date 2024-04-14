package br.com.lanchonete.cassandra.repository;

import br.com.lanchonete.cassandra.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataCassandraCategoryRepository extends JpaRepository<CategoryEntity, UUID> {

    boolean existsByName(String name);

    Optional<CategoryEntity> findByName(String name);

}

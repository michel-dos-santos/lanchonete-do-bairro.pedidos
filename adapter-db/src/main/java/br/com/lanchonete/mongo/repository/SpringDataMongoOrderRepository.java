package br.com.lanchonete.mongo.repository;

import br.com.lanchonete.mongo.entity.OrderEntity;
import br.com.lanchonete.mongo.entity.StatusType;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataMongoOrderRepository extends MongoRepository<OrderEntity, String> {

    Optional<OrderEntity> findByExternalId(UUID externalId);

    List<OrderEntity> findAllByStatus(StatusType statusType);

    @Query(value = "{ 'status': { '$in': ?0 } }")
    List<OrderEntity> findAllOrdersMonitor(List<StatusType> status, Sort sort);

}

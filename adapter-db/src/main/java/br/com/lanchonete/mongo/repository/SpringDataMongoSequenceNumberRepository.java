package br.com.lanchonete.mongo.repository;

import br.com.lanchonete.mongo.entity.SequenceNumber;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataMongoSequenceNumberRepository extends MongoRepository<SequenceNumber, Long> {

}

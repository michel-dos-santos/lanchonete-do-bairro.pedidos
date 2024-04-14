package br.com.lanchonete.cassandra.repository;

import br.com.lanchonete.cassandra.entity.SequenceNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCassandraSequenceNumberRepository extends JpaRepository<SequenceNumber, Long> {

}

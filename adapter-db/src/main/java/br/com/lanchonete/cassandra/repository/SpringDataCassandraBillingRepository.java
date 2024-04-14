package br.com.lanchonete.cassandra.repository;

import br.com.lanchonete.cassandra.entity.BillingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataCassandraBillingRepository extends JpaRepository<BillingEntity, UUID> {

}

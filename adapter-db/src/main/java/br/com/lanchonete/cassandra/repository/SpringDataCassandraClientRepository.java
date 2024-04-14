package br.com.lanchonete.cassandra.repository;

import br.com.lanchonete.cassandra.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataCassandraClientRepository extends JpaRepository<ClientEntity, UUID> {
    boolean existsByCpf(String cpf);
    Optional<ClientEntity> findByCpf(String cpf);

}

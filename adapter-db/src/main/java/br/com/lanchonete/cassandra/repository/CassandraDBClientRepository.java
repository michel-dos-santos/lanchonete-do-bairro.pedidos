package br.com.lanchonete.cassandra.repository;

import br.com.lanchonete.cassandra.entity.ClientEntity;
import br.com.lanchonete.model.Client;
import br.com.lanchonete.port.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CassandraDBClientRepository implements ClientRepository {

    private final SpringDataCassandraClientRepository clientRepository;
    private final ModelMapper modelMapper;

    public CassandraDBClientRepository(SpringDataCassandraClientRepository clientRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public Client save(Client client) {
        ClientEntity clientEntity = modelMapper.map(client, ClientEntity.class);
        clientRepository.save(clientEntity);
        return modelMapper.map(clientEntity, Client.class);
    }

}

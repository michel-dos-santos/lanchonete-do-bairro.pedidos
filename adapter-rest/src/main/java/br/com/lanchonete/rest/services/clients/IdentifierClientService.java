package br.com.lanchonete.rest.services.clients;

import br.com.lanchonete.model.Client;
import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.port.repository.IdentifierClientRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.rest.services.clients.dto.ResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdentifierClientService implements IdentifierClientRepository {

    @Autowired
    private ClientServiceFeignClient serviceFeignClient;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LogRepository logRepository;

    @Override
    public Client identifierById(UUID id) {
        logRepository.info(IdentifierClientService.class, LogCode.LogCodeInfo._0011);
        ResponseDTO clientById = serviceFeignClient.getClientById(id);
        Client client = modelMapper.map(clientById, Client.class);
        logRepository.info(IdentifierClientService.class, LogCode.LogCodeInfo._0012);
        return client;
    }
}
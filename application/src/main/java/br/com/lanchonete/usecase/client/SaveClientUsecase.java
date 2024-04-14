package br.com.lanchonete.usecase.client;

import br.com.lanchonete.model.Client;
import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.port.repository.ClientRepository;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.usecase.client.SaveClient;

public class SaveClientUsecase implements SaveClient {

    private final LogRepository logRepository;
    private final ClientRepository clientRepository;

    public SaveClientUsecase(ClientRepository clientRepository, LogRepository logRepository) {
        this.logRepository = logRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Client save(Client client) {
        logRepository.info(SaveClientUsecase.class, LogCode.LogCodeInfo._0001);
        Client clientSaved = clientRepository.save(client);
        logRepository.info(SaveClientUsecase.class, LogCode.LogCodeInfo._0003);

        return clientSaved;
    }
}

package br.com.lanchonete.port.repository;

import br.com.lanchonete.model.Client;

import java.util.UUID;

public interface IdentifierClientRepository {

    Client identifierById(UUID id);

}

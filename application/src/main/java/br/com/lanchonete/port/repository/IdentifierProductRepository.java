package br.com.lanchonete.port.repository;

import br.com.lanchonete.model.Product;

import java.util.UUID;

public interface IdentifierProductRepository {

    Product identifierById(UUID id);

}

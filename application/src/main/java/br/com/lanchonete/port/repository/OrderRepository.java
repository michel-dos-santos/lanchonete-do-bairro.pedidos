package br.com.lanchonete.port.repository;

import br.com.lanchonete.model.Order;
import br.com.lanchonete.model.StatusPaymentType;
import br.com.lanchonete.model.StatusType;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {

    Order checkout(Order order);
    List<Order> findAllOrdersByStatus(StatusType statusType);

    Order updateStatus(String id, StatusType statusType);

    Order findById(String id);

    Order findByExternalId(UUID externalId);

    Order save(Order order);

    List<Order> listOrdersMonitor();

    Order updateStatusPaymentType(UUID externalId, StatusPaymentType status);

}

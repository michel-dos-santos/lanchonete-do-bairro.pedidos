package br.com.lanchonete.port.repository;

import br.com.lanchonete.model.Billing;

import java.util.UUID;

public interface NotifyBillingHubRepository {

    void sendNotification(Billing billing, UUID orderId);

}

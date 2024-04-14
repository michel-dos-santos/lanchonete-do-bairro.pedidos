package br.com.lanchonete.port.repository;

import br.com.lanchonete.model.Billing;

public interface NotifyBillingHubRepository {

    void sendNotification(Billing billing);

}

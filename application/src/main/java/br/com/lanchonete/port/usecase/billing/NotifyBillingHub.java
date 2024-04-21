package br.com.lanchonete.port.usecase.billing;

import br.com.lanchonete.model.Billing;

import java.util.UUID;

public interface NotifyBillingHub {

    void notify(Billing billing, UUID orderId);

}

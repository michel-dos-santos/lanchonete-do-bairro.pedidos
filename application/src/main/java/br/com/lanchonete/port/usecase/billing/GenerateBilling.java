package br.com.lanchonete.port.usecase.billing;

import br.com.lanchonete.model.Billing;

import java.util.UUID;

public interface GenerateBilling {

    Billing generate(Billing billing, UUID orderId);

}

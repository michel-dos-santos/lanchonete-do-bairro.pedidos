package br.com.lanchonete.usecase.billing;

import br.com.lanchonete.model.Billing;
import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.model.StatusPaymentType;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.usecase.billing.GenerateBilling;
import br.com.lanchonete.port.usecase.billing.NotifyBillingHub;

import java.util.UUID;

public class GenerateBillingUsecase implements GenerateBilling {

    private final LogRepository logRepository;
    private final NotifyBillingHub notifyBillingHub;

    public GenerateBillingUsecase(LogRepository logRepository, NotifyBillingHub notifyBillingHub) {
        this.logRepository = logRepository;
        this.notifyBillingHub = notifyBillingHub;
    }

    @Override
    public Billing generate(Billing billing, UUID orderId) {
        logRepository.info(GenerateBillingUsecase.class, LogCode.LogCodeInfo._0005);
        billing.setStatus(StatusPaymentType.PENDING);
        billing.setId(UUID.randomUUID());
        notifyBillingHub.notify(billing, orderId);
        logRepository.info(GenerateBillingUsecase.class, LogCode.LogCodeInfo._0006);
        return billing;
    }
}

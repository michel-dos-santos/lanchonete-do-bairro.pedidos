package br.com.lanchonete.usecase.billing;

import br.com.lanchonete.model.Billing;
import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.NotifyBillingHubRepository;
import br.com.lanchonete.port.usecase.billing.NotifyBillingHub;

public class NotifyBillingHubUsecase implements NotifyBillingHub {

    private final LogRepository logRepository;
    private final NotifyBillingHubRepository billingHubRepository;

    public NotifyBillingHubUsecase(LogRepository logRepository, NotifyBillingHubRepository billingHubRepository) {
        this.logRepository = logRepository;
        this.billingHubRepository = billingHubRepository;
    }

    @Override
    public void notify(Billing billing) {
        logRepository.info(NotifyBillingHubUsecase.class, LogCode.LogCodeInfo._0028);
        billingHubRepository.sendNotification(billing);
        logRepository.info(NotifyBillingHubUsecase.class, LogCode.LogCodeInfo._0029);
    }

}

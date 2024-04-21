package br.com.lanchonete.configuration;

import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.NotifyBillingHubRepository;
import br.com.lanchonete.port.repository.OrderRepository;
import br.com.lanchonete.port.usecase.billing.NotifyBillingHub;
import br.com.lanchonete.usecase.billing.GenerateBillingUsecase;
import br.com.lanchonete.usecase.billing.NotifyBillingHubUsecase;
import br.com.lanchonete.usecase.billing.UpdateBillingByHubUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BillingBeanConfiguration {

    @Bean
    GenerateBillingUsecase generateBilling(LogRepository logRepository, NotifyBillingHub notifyBillingHub) {
        return new GenerateBillingUsecase(logRepository, notifyBillingHub);
    }

    @Bean
    NotifyBillingHubUsecase notifyBillingHub(LogRepository logRepository, NotifyBillingHubRepository notifyBillingHubRepository) {
        return new NotifyBillingHubUsecase(logRepository, notifyBillingHubRepository);
    }

    @Bean
    UpdateBillingByHubUsecase updateBillingByHub(LogRepository logRepository, OrderRepository orderRepository) {
        return new UpdateBillingByHubUsecase(logRepository, orderRepository);
    }

}

package br.com.lanchonete.usecase.billing;

import br.com.lanchonete.model.LogCode;
import br.com.lanchonete.model.Order;
import br.com.lanchonete.model.StatusPaymentType;
import br.com.lanchonete.model.StatusType;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.repository.OrderRepository;
import br.com.lanchonete.port.usecase.billing.UpdateBillingByHub;

import java.util.UUID;

public class UpdateBillingByHubUsecase implements UpdateBillingByHub {

    private final LogRepository logRepository;
    private final OrderRepository orderRepository;

    public UpdateBillingByHubUsecase(LogRepository logRepository, OrderRepository orderRepository) {
        this.logRepository = logRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void updateStatusPaymentType(StatusPaymentType statusPaymentType, UUID externalId) {
        logRepository.info(UpdateBillingByHubUsecase.class, LogCode.LogCodeInfo._0013);
        Order order = orderRepository.updateStatusPaymentType(externalId, statusPaymentType);

        if (StatusPaymentType.PAID == statusPaymentType) {
            orderRepository.updateStatus(order.getId(), StatusType.IN_PREPARATION);
        }

        if (StatusPaymentType.REJECTED == statusPaymentType) {
            orderRepository.updateStatus(order.getId(), StatusType.FINISHED);
        }
        logRepository.info(UpdateBillingByHubUsecase.class, LogCode.LogCodeInfo._0014);
    }
}

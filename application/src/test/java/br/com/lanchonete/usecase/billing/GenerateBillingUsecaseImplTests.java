package br.com.lanchonete.usecase.billing;

import br.com.lanchonete.model.Billing;
import br.com.lanchonete.model.StatusPaymentType;
import br.com.lanchonete.port.repository.LogRepository;
import br.com.lanchonete.port.usecase.billing.NotifyBillingHub;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class GenerateBillingUsecaseImplTests {

    @InjectMocks
    private GenerateBillingUsecase generateBillingUsecase;
    @Mock
    private NotifyBillingHub notifyBillingHub;
    @Mock
    private LogRepository logRepository;
    private static EasyRandom easyRandom;

    @BeforeAll
    public static void beforeTests() {
        easyRandom = new EasyRandom();
    }

    @Test
    public void generateBilling() throws Exception {
        Billing billing = easyRandom.nextObject(Billing.class);
        billing.setStatus(StatusPaymentType.PENDING);
        UUID orderId = UUID.randomUUID();

        Billing billingOutput = generateBillingUsecase.generate(billing, orderId);

        assertThat(billingOutput).isNotNull();
        assertThat(billingOutput.getStatus()).isEqualTo(billing.getStatus());

    }

}

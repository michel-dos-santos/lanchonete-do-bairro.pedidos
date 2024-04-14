package br.com.lanchonete.cassandra.repository;

import br.com.lanchonete.cassandra.entity.BillingEntity;
import br.com.lanchonete.exception.billing.BillingNotFoundException;
import br.com.lanchonete.model.Billing;
import br.com.lanchonete.model.StatusPaymentType;
import br.com.lanchonete.port.repository.BillingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
public class CassandraDBBillingRepository implements BillingRepository {

    private final SpringDataCassandraBillingRepository billingRepository;
    private final ModelMapper modelMapper;

    public CassandraDBBillingRepository(SpringDataCassandraBillingRepository billingRepository, ModelMapper modelMapper) {
        this.billingRepository = billingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public Billing save(Billing billing) {
        BillingEntity billingEntity = modelMapper.map(billing, BillingEntity.class);
        return modelMapper.map(billingRepository.save(billingEntity), Billing.class);
    }

    @Override
    public Billing updateStatusPaymentType(UUID id, StatusPaymentType status) {
        Billing billing = findById(id);
        billing.setStatus(status);
        return save(billing);
    }

    @Override
    @Transactional
    public Billing findById(UUID id) {
        Optional<BillingEntity> billingEntityOptional = billingRepository.findById(id);
        if (billingEntityOptional.isEmpty()) {
            throw new BillingNotFoundException("id", null, id.toString(), null);
        }
        return modelMapper.map(billingEntityOptional.get(), Billing.class);
    }

}

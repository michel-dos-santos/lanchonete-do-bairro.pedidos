package br.com.lanchonete.mongo.repository;

import br.com.lanchonete.exception.order.OrderNotFoundException;
import br.com.lanchonete.model.Order;
import br.com.lanchonete.model.StatusPaymentType;
import br.com.lanchonete.model.StatusType;
import br.com.lanchonete.mongo.entity.OrderEntity;
import br.com.lanchonete.mongo.service.SequenceGeneratorService;
import br.com.lanchonete.port.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class MongoDBOrderRepository implements OrderRepository {

    private final SpringDataMongoOrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final SequenceGeneratorService sequenceGeneratorService;

    public MongoDBOrderRepository(SpringDataMongoOrderRepository orderRepository, ModelMapper modelMapper, SequenceGeneratorService sequenceGeneratorService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Override
    @Transactional
    public Order checkout(Order order) {
        OrderEntity orderEntity = modelMapper.map(order, OrderEntity.class);
        orderEntity.setNumber(sequenceGeneratorService.generateSequence(OrderEntity.SEQUENCE_NAME));
        return modelMapper.map(orderRepository.save(orderEntity), Order.class);
    }

    @Override
    public List<Order> findAllOrdersByStatus(StatusType statusType) {
        List<OrderEntity> orderEntityList = orderRepository.findAllByStatus(br.com.lanchonete.mongo.entity.StatusType.get(statusType.toString()));
        Type type = new TypeToken<List<Order>>() {}.getType();
        return modelMapper.map(orderEntityList, type);
    }

    @Override
    @Transactional
    public Order updateStatus(String id, StatusType statusType) {
        Order order = findById(id);
        order.setStatus(statusType);
        order = save(order);
        return order;
    }

    @Override
    @Transactional
    public Order findById(String id) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
        if (orderEntityOptional.isEmpty()) {
            throw new OrderNotFoundException("id", null, id.toString(), null);
        }
        return modelMapper.map(orderEntityOptional.get(), Order.class);
    }

    @Override
    @Transactional
    public Order findByExternalId(UUID externalId) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findByExternalId(externalId);
        if (orderEntityOptional.isEmpty()) {
            throw new OrderNotFoundException("id", null, externalId.toString(), null);
        }
        return modelMapper.map(orderEntityOptional.get(), Order.class);
    }

    @Override
    @Transactional
    public Order save(Order order) {
        OrderEntity orderEntity = modelMapper.map(order, OrderEntity.class);
        return modelMapper.map(orderRepository.save(orderEntity), Order.class);
    }

    @Override
    public List<Order> listOrdersMonitor() {
        List<OrderEntity> orderEntityList = orderRepository.findAllOrdersMonitor(
                Arrays.asList(
                        br.com.lanchonete.mongo.entity.StatusType.READY,
                        br.com.lanchonete.mongo.entity.StatusType.IN_PREPARATION,
                        br.com.lanchonete.mongo.entity.StatusType.RECEIVED
                ),
                Sort.by(Sort.Direction.ASC, "createdAt") //TODO AJUSTAR PARA A ORDENAÇÃO FICAR CORRETA
        );
        Type type = new TypeToken<List<Order>>() {}.getType();
        return modelMapper.map(orderEntityList, type);
    }

    @Override
    public Order updateStatusPaymentType(UUID externalId, StatusPaymentType status) {
        Order order = findByExternalId(externalId);
        order.getBilling().setStatus(status);
        order = save(order);
        return order;
    }

}

package br.com.lanchonete.cassandra.repository;

import br.com.lanchonete.cassandra.entity.OrderEntity;
import br.com.lanchonete.cassandra.entity.SequenceNumber;
import br.com.lanchonete.exception.order.OrderNotFoundException;
import br.com.lanchonete.model.Order;
import br.com.lanchonete.model.StatusType;
import br.com.lanchonete.port.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CassandraDBOrderRepository implements OrderRepository {

    private final SpringDataCassandraOrderRepository orderRepository;
    private final SpringDataCassandraSequenceNumberRepository sequenceNumberRepository;
    private final ModelMapper modelMapper;

    public CassandraDBOrderRepository(SpringDataCassandraOrderRepository orderRepository, SpringDataCassandraSequenceNumberRepository sequenceNumberRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.sequenceNumberRepository = sequenceNumberRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public Order checkout(Order order) {
        OrderEntity orderEntity = modelMapper.map(order, OrderEntity.class);
        SequenceNumber sequenceNumber = sequenceNumberRepository.save(new SequenceNumber());
        orderEntity.setNumber(sequenceNumber.getNumber());
        orderEntity.getOrderItems().forEach(orderItemEntity -> orderItemEntity.setOrder(orderEntity));
        return modelMapper.map(orderRepository.save(orderEntity), Order.class);
    }

    @Override
    public List<Order> findAllOrdersByStatus(StatusType statusType) {
        List<OrderEntity> orderEntityList = orderRepository.findAllByStatus(br.com.lanchonete.cassandra.entity.StatusType.get(statusType.toString()));
        Type type = new TypeToken<List<Order>>() {}.getType();
        return modelMapper.map(orderEntityList, type);
    }

    @Override
    @Transactional
    public Order updateStatus(UUID id, StatusType statusType) {
        Order order = findById(id);
        order.setStatus(statusType);
        order = save(order);
        return order;
    }

    @Override
    @Transactional
    public Order findById(UUID id) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
        if (orderEntityOptional.isEmpty()) {
            throw new OrderNotFoundException("id", null, id.toString(), null);
        }
        return modelMapper.map(orderEntityOptional.get(), Order.class);
    }

    @Override
    public Order findByBillingId(UUID id) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findByBillingId(id);
        if (orderEntityOptional.isEmpty()) {
            throw new OrderNotFoundException("id", null, id.toString(), null);
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
                        br.com.lanchonete.cassandra.entity.StatusType.READY,
                        br.com.lanchonete.cassandra.entity.StatusType.IN_PREPARATION,
                        br.com.lanchonete.cassandra.entity.StatusType.RECEIVED
                )
        );
        Type type = new TypeToken<List<Order>>() {}.getType();
        return modelMapper.map(orderEntityList, type);
    }

}

package br.com.lanchonete.rest.mappers.inputs;

import br.com.lanchonete.model.Order;
import br.com.lanchonete.model.OrderItem;
import br.com.lanchonete.port.repository.IdentifierClientRepository;
import br.com.lanchonete.port.repository.IdentifierProductRepository;
import br.com.lanchonete.rest.mappers.inputs.dtos.OrderInputDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

@Component
public class OrderInputMapper {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IdentifierClientRepository identifierClientRepository;
    @Autowired
    private IdentifierProductRepository identifierProductRepository;

    public Order mapOrderFromOrderInputDTO(OrderInputDTO orderInputDTO) {
        Order order = modelMapper.map(orderInputDTO, Order.class);

        if (Objects.nonNull(orderInputDTO.getClientID())) {
            order.setClient(identifierClientRepository.identifierById(orderInputDTO.getClientID()));
        }

        order.setOrderItems(new ArrayList<>());
        orderInputDTO.getOrderItems().forEach(orderItemInputDTO -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setObservation(orderItemInputDTO.getObservation());
            orderItem.setQuantity(orderItemInputDTO.getQuantity());
            orderItem.setProduct(identifierProductRepository.identifierById(orderItemInputDTO.getProductID()));
            order.getOrderItems().add(orderItem);
        });
        return order;
    }

}

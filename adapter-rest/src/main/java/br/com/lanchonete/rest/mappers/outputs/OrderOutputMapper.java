package br.com.lanchonete.rest.mappers.outputs;

import br.com.lanchonete.model.Order;
import br.com.lanchonete.rest.mappers.outputs.dtos.MyOrderOutputDTO;
import br.com.lanchonete.rest.mappers.outputs.dtos.OrderOutputDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderOutputMapper {

    @Autowired
    private ModelMapper modelMapper;

    public OrderOutputDTO mapOrderOutputDTOFromOrder(Order order) {
        return modelMapper.map(order, OrderOutputDTO.class);
    }

    public List<OrderOutputDTO> mapListOrderOutputDTOFromListOrder(List<Order> orders) {
        return modelMapper.map(orders, new TypeToken<List<OrderOutputDTO>>() {}.getType());
    }

    public MyOrderOutputDTO mapMyOrderOutputDTOFromOrder(Order order) {
        MyOrderOutputDTO myOrderOutputDTO = modelMapper.map(order, MyOrderOutputDTO.class);
        myOrderOutputDTO.setBillingOrderId(order.getBilling().getId());
        return myOrderOutputDTO;
    }

    public List<MyOrderOutputDTO> mapListMyOrderOutputDTOFromListOrder(List<Order> orders) {
        return modelMapper.map(orders, new TypeToken<List<MyOrderOutputDTO>>() {}.getType());
    }

}

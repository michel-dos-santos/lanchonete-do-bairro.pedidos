package br.com.lanchonete.rest.mappers.outputs;

import br.com.lanchonete.model.Order;
import br.com.lanchonete.rest.mappers.outputs.dtos.MyOrderOutputDTO;
import br.com.lanchonete.rest.mappers.outputs.dtos.OrderOutputDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderOutputMapper {

    private final ModelMapper modelMapper;

    public OrderOutputMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        this.modelMapper.addMappings(MappingToModel.mapOrderOutputDTOFromOrder);
        this.modelMapper.addMappings(MappingToModel.mapMyOrderOutputDTOFromOrder);
    }

    public OrderOutputDTO mapOrderOutputDTOFromOrder(Order order) {
        return modelMapper.map(order, OrderOutputDTO.class);
    }

    public List<OrderOutputDTO> mapListOrderOutputDTOFromListOrder(List<Order> orders) {
        return modelMapper.map(orders, new TypeToken<List<OrderOutputDTO>>() {}.getType());
    }

    public MyOrderOutputDTO mapMyOrderOutputDTOFromOrder(Order order) {
        return modelMapper.map(order, MyOrderOutputDTO.class);
    }

    public List<MyOrderOutputDTO> mapListMyOrderOutputDTOFromListOrder(List<Order> orders) {
        return modelMapper.map(orders, new TypeToken<List<MyOrderOutputDTO>>() {}.getType());
    }

    class MappingToModel {

        public static final PropertyMap<Order, OrderOutputDTO> mapOrderOutputDTOFromOrder = new PropertyMap<>() {
            protected void configure() {
                map().setId(source.getExternalId());
            }
        };

        public static final PropertyMap<Order, MyOrderOutputDTO> mapMyOrderOutputDTOFromOrder = new PropertyMap<>() {
            protected void configure() {
                map().setId(source.getExternalId());
            }
        };

    }

}

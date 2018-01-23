package services;

import entity.OrderItems;

import java.util.List;

public interface OrderService {
    OrderItems add(OrderItems orderItems);

    void update(OrderItems orderItems);

    void delete(OrderItems orderItems);

    void deleteAll();

    OrderItems findByIdOrder(Integer idOrder);

    List<OrderItems> listAll();

    List<OrderItems> listAllOrderByDate();

    long count();
}

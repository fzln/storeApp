package services.impl;

import entity.OrderItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.OrderRepository;
import services.OrderService;

import java.util.List;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderItems add(OrderItems orderItems) {
        return orderRepository.saveAndFlush(orderItems);
    }

    @Override
    public void update(OrderItems orderItems) {
        orderRepository.saveAndFlush(orderItems);
    }

    @Override
    public void delete(OrderItems descriptions) {
        orderRepository.delete(descriptions);

    }

    @Override
    public void deleteAll() {
        orderRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderItems findByIdOrder(Integer idOrder) {
        return orderRepository.getOne(idOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItems> listAll() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItems> listAllOrderByDate() {
        return orderRepository.findAllByOrderByCreatedDateDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return orderRepository.count();
    }
}

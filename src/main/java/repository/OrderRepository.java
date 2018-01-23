package repository;

import entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderItems, Integer> {
    List<OrderItems> findAllByOrderByCreatedDateDesc();
}

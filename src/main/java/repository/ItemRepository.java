package repository;

import entity.Item;
import entity.ItemPK;
import entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, ItemPK> {
    List<Item> findAllByIdOrderOrderByLineIdAsc(OrderItems orderItems);

    Integer countByIdOrder(OrderItems orderItems);
}

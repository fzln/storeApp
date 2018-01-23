package services;

import entity.Item;
import entity.ItemPK;
import entity.OrderItems;

import java.util.List;

public interface ItemService {
    Item add(Item item);

    void update(Item item);

    void delete(Item item);

    void deleteAll();

    Item findByItemPK(ItemPK itemPK);

    List<Item> listAll();

    List<Item> listAllByIdOrderOrderByLineId(OrderItems orderItems);

    Integer countByIdOrder(OrderItems orderItems);

    long count();
}

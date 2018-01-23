package services.impl;


import entity.Item;
import entity.ItemPK;
import entity.OrderItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ItemRepository;
import services.ItemService;

import java.util.List;

@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item add(Item item) {
        return  itemRepository.saveAndFlush(item);
    }

    @Override
    public void update(Item item) {
        itemRepository.saveAndFlush(item);
    }

    @Override
    public void delete(Item descriptions) {
        itemRepository.delete(descriptions);

    }

    @Override
    public void deleteAll() {
        itemRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Item findByItemPK(ItemPK itemPK) {
        return itemRepository.getOne(itemPK);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> listAll() {
        return itemRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> listAllByIdOrderOrderByLineId(OrderItems orderItems) {
        return itemRepository.findAllByIdOrderOrderByLineIdAsc(orderItems);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer countByIdOrder(OrderItems orderItems)
    {
        return itemRepository.countByIdOrder(orderItems);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return itemRepository.count();
    }
}

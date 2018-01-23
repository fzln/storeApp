package mb;

import entity.Item;
import entity.OrderItems;
import org.primefaces.component.api.UIColumn;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.web.context.annotation.RequestScope;
import services.DescriptionService;
import services.ItemService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScope
public class ItemBean extends BeanSuper {
    private Item item;
    private List<Item> itemList;
    private OrderItems orderItems;

    private String serialNumberString;
    @Inject
    private ItemService itemService;

    @Inject
    private ItemSession itemSession;

    @Inject
    private OrderSession orderSession;

    @Inject
    private DescriptionService descriptionService;

    @PostConstruct
    public void postConstruct() {
        orderItems = orderSession.getOrderItems();
        itemList = itemService.listAllByIdOrderOrderByLineId(orderItems);
        newItem();
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    private void newItem() {
        item = new Item();
        item.setIdOrder(orderItems);
    }

    public void setItemSession(ItemSession itemSession) {
        this.itemSession = itemSession;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public void setOrderSession(OrderSession orderSession) {
        this.orderSession = orderSession;
    }

    public void setDescriptionService(DescriptionService descriptionService) {
        this.descriptionService = descriptionService;
    }

    public OrderItems getOrderItems() {
        return orderItems;
    }

    public String getSerialNumberString() {
        return serialNumberString;
    }

    public void setSerialNumberString(String serialNumberString) {
        this.serialNumberString = serialNumberString;
    }

    public void createItemFromSession() {
        item = itemSession.getItem();
        createItem();
        itemSession.resetItem();
    }

    public void createItem() {
        if (item == null) return;
        try {
            item.setSerialNumber(descriptionService.findBySerialNumber(serialNumberString));
            itemService.update(item);
        } catch (Exception e) {
            sendErrorMessage("Can't create Item", "Create failed");
            e.printStackTrace();
        }
        loadItemList();
        sendInfoMessage("Item Created", "Id " + item.getLineId());
    }

    public void deleteItem(Item item) {
        try {
            itemService.delete(item);
        } catch (Exception e) {
            sendErrorMessage("Can't delete Item", "Delete failed");
            e.printStackTrace();
        }
        loadItemList();
        sendInfoMessage("Item Deleted", "Id " + item.getLineId());
    }

    public void deleteItemBySession() {
        item = itemSession.getItem();
        deleteItem();
        itemSession.resetItem();
    }

    public void deleteItem() {
        try {
            itemService.delete(item);
        } catch (Exception e) {
            sendErrorMessage("Can't delete Item", "Delete failed");
            e.printStackTrace();
        }
        loadItemList();
        sendInfoMessage("Item Deleted", "Id " + item.getLineId());
    }

    public void saveItem() {
        itemService.update(item);
        item = new Item();
        invalidateItemList();
    }

    public void resetItem() {
        newItem();
    }

    private void invalidateItemList() {
        itemList = null;
    }

    private void loadItemList() {
        itemList = itemService.listAllByIdOrderOrderByLineId(orderItems);
    }

    public void clearItemList() {
        itemService.deleteAll();
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void onRowEditInit(RowEditEvent event){
        item = (Item) event.getObject();
        if (item == null) return;
        serialNumberString=item.getSerialNumber().getSerialNumber();
}

    public void onRowEdit(RowEditEvent event) {
        item = (Item) event.getObject();
        if (item == null) return;
        try {
            item.setSerialNumber(descriptionService.findBySerialNumber(serialNumberString));

            itemService.update(item);
        } catch (Exception e) {
            sendErrorMessage("Can't update Item", "Update failed");
            e.printStackTrace();
        }
        loadItemList();
        sendInfoMessage("Item Edited", "Id " + item.getLineId());
        itemSession.resetItem();
        resetItem();
    }

    public void onRowCancel(RowEditEvent event) {
        sendInfoMessage("Edit Cancelled", "Id " + ((Item) event.getObject()).getLineId());
    }

    public void onCellEdit(Item item) {
        try {
            itemService.update(item);
        } catch (Exception e) {
            sendErrorMessage("Can't update Item", "Update failed");
            e.printStackTrace();
        }
        loadItemList();
        sendInfoMessage("Item Edited", "Id " + item.getLineId());
        itemSession.resetItem();
        resetItem();
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        int rowIndex = event.getRowIndex();
        UIColumn column = event.getColumn();

        if (newValue != null && !newValue.equals(oldValue)) {
            sendInfoMessage("Cell Changed", "Old: " + oldValue + ", New:" + newValue + " rowIndex:" + rowIndex +
                    " CK:" + column.getColumnKey());
        }
    }
}

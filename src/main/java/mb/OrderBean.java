package mb;

import entity.Item;
import entity.OrderItems;
import org.primefaces.component.api.UIColumn;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.web.context.annotation.RequestScope;
import services.ItemService;
import services.OrderService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScope
public class OrderBean extends BeanSuper {
    private OrderItems orderItems = new OrderItems();
    private List<OrderItems> orderList;

    private Item item;
    private List<Item> itemList;

    @Inject
    private OrderService orderService;

    @Inject
    private OrderSession orderSession;

    @Inject
    private ItemService itemService;

    @PostConstruct
    public void postConstruct() {
        loadOrderList();
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public OrderItems getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(OrderItems orderItems) {
        this.orderItems = orderItems;
    }

    public void setOrderSession(OrderSession orderSession) {
        this.orderSession = orderSession;
    }

    public void createOrderFromSession(){
        orderItems = orderSession.getOrderItems();
        createOrder();
        orderSession.resetOrder();
    }

    public void createOrder() {
        if (orderItems == null) return;
        try {
            orderService.update(orderItems);
        } catch (Exception e) {
            sendErrorMessage("Can't create Order", "Create failed");
            e.printStackTrace();
        }
        loadOrderList();
        sendInfoMessage("Order Created", "Id " + orderItems.getIdOrder());
    }

    public void deleteOrder(OrderItems orderItems) {
        try {
            orderService.delete(orderItems);
        } catch (Exception e) {
            sendErrorMessage("Can't delete Order", "Delete failed");
            e.printStackTrace();
        }
        loadOrderList();
        sendInfoMessage("Order Deleted", "Id " + orderItems.getIdOrder());
    }

    public void deleteOrder() {
        orderItems = orderSession.getOrderItems();
        if (orderItems == null) return;
        try {
            orderService.delete(orderItems);
        } catch (Exception e) {
            sendErrorMessage("Can't delete Order", "Delete failed");
            e.printStackTrace();
        }
        loadOrderList();
        sendInfoMessage("Order Deleted", "Id " + orderItems.getIdOrder());
        orderSession.resetOrder();
    }

    public void saveOrder() {
        orderService.update(orderItems);
        orderItems = new OrderItems();
        invalidateOrderList();
    }

    public void resetOrder() {
        orderItems = new OrderItems();
    }

    private void invalidateOrderList() {
        orderList = null;
    }

    private void loadOrderList() {
        orderList = orderService.listAllOrderByDate();
    }

    public Integer countByIdOrder(OrderItems orderItems){
        return itemService.countByIdOrder(orderItems);
    }

    public List<OrderItems> getOrderList() {
        if (orderList == null) loadOrderList();
        return orderList;
    }

    public void onRowEdit(RowEditEvent event) {
        orderItems = (OrderItems) event.getObject();
        if (orderItems == null) return;
        try {
            orderService.update(orderItems);
        } catch (Exception e) {
            sendErrorMessage("Can't update Order", "Update failed");
            e.printStackTrace();
        }
        loadOrderList();
        sendInfoMessage("Order Edited", "Id " + orderItems.getIdOrder());
        orderSession.resetOrder();
        resetOrder();
    }

    public void onRowCancel(RowEditEvent event) {
        sendInfoMessage("Edit Cancelled", "Id " + ((OrderItems) event.getObject()).getIdOrder());
    }

    public void onCellEdit(OrderItems orderItems) {
        try {
            orderService.update(orderItems);
        } catch (Exception e) {
            sendErrorMessage("Can't update Order", "Update failed");
            e.printStackTrace();
        }
        loadOrderList();
        sendInfoMessage("Order Edited", "Id " + orderItems.getIdOrder());
        orderSession.resetOrder();
        resetOrder();
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

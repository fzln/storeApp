package mb;

import entity.OrderItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.annotation.SessionScope;
import webcalendar.WebCalendar;
import webcalendar.WebCalendarService;

import javax.inject.Named;

@Named
@SessionScope
public class OrderSession {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private OrderItems orderItems;

    public OrderItems getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(OrderItems orderItems) {
        this.orderItems = orderItems;
    }

    public void resetOrder() {
        orderItems = null;
    }

    public void newOrder() {
        orderItems = new OrderItems();

        try {

            WebCalendar service = new WebCalendarService().getWebCalendarPort();
            orderItems.setCreatedDate(service.getCalendar().toGregorianCalendar());
        }
        catch (Exception e){
            logger.info("WebCalendar is not available.");
        }
    }

    public String actionOrder(OrderItems orderItems){
        this.orderItems=orderItems;
        return "/itemList";
    }
}

package mb;

import entity.Item;
import org.springframework.web.context.annotation.SessionScope;

import javax.inject.Named;

@Named
@SessionScope
public class ItemSession {

    private Item item;

    public Item getItem() {
        return item;
    }

    public void setItem(Item description) {
        this.item = item;
    }

    public void resetItem() { item = null; }

    public void newItem() {
        item = new Item();
    }
}

package entity;

import javax.persistence.*;
import java.util.Objects;

@IdClass(ItemPK.class)
@Entity
public class Item {
    @Id
    private Integer lineId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "serialNumber", columnDefinition = "char(6)")
    private Description serialNumber;

    private Integer quantity;

    @Id
    @ManyToOne
    @JoinColumn(name = "idOrder")
    private OrderItems idOrder;

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Description getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Description serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OrderItems getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(OrderItems idOrder) {
        this.idOrder = idOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(lineId, item.lineId) &&
                Objects.equals(serialNumber, item.serialNumber) &&
                Objects.equals(quantity, item.quantity) &&
                Objects.equals(idOrder, item.idOrder);
    }

    @Override
    public int hashCode() {

        return Objects.hash(lineId, serialNumber, quantity, idOrder);
    }
}

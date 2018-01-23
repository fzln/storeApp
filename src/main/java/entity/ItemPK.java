package entity;

import java.io.Serializable;
import java.util.Objects;

public class ItemPK implements Serializable{
    private Integer idOrder;
    private Integer lineId;

    public ItemPK() {}

    public ItemPK(Integer idOrder, Integer lineId) {
        this.idOrder = idOrder;
        this.lineId = lineId;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPK itemPK = (ItemPK) o;
        return Objects.equals(idOrder, itemPK.idOrder) &&
                Objects.equals(lineId, itemPK.lineId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idOrder, lineId);
    }
}

package entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Objects;

@Entity
public class OrderItems {
    @Id
    private Integer idOrder;
    @Column(length = 50)
    private String customerName;
    @Column(length = 100)
    private String customerAddress;
    private Integer totalAmount;

    @Column(nullable = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    Calendar createdDate;

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Calendar getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItems orderItems = (OrderItems) o;
        return Objects.equals(idOrder, orderItems.idOrder) &&
                Objects.equals(customerName, orderItems.customerName) &&
                Objects.equals(customerAddress, orderItems.customerAddress) &&
                Objects.equals(totalAmount, orderItems.totalAmount) &&
                Objects.equals(createdDate, orderItems.createdDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idOrder, customerName, customerAddress, totalAmount, createdDate);
    }
}

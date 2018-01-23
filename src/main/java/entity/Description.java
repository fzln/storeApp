package entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
public class Description {
    @Id
    @Column(columnDefinition = "char(6)")
    private String serialNumber;

    @Column(length = 50, nullable = false)
    @NotNull
    private String name;
    @Column(length = 100)
    private String description;

    @Temporal(TemporalType.DATE)
    private Date productionDate;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Description that = (Description) o;
        return Objects.equals(serialNumber, that.serialNumber) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(productionDate, that.productionDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(serialNumber, name, description, productionDate);
    }
}

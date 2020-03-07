package baltic.amadeus.pizzacooker.entity;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class Product implements Serializable {
    private String name;
    private Integer qty;

    public Product() {
    }

    public Product(String name, Integer qty) {
        this.name = name;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

}

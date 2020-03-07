package baltic.amadeus.pizzacooker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "stocks")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Stock implements Serializable {
    @Id
    @SequenceGenerator(name = "pk_sequence_stock", sequenceName = "stock_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence_stock")
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "cooker_machine_id")
    private CookerMachine cookerMachine;

    @ElementCollection
    @JoinColumn(name = "stock_id")
    private Set<Product> products;

    public Stock() {
    }

    public Stock(Integer id, String name,Set<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public Stock(String name,Set<Product> products) {
        this.name = name;
        this.products = products;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CookerMachine getCookerMachine() {
        return cookerMachine;
    }

    public void setCookerMachine(CookerMachine cookerMachine) {
        this.cookerMachine = cookerMachine;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}

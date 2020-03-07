package baltic.amadeus.pizzacooker.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cooker_machines")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CookerMachine implements Serializable {
    @Id
    @SequenceGenerator(name = "pk_sequence_cooker_machine", sequenceName = "cooker_machine_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence_cooker_machine")
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "cook_count")
    private Integer cookCount;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "cookerMachine")
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(name = "clean")
    private Boolean clean;

    public CookerMachine() {
    }

    public CookerMachine(Integer id, String name, Integer cookCount, Boolean clean) {
        this.id = id;
        this.name = name;
        this.cookCount = cookCount;
        this.clean = clean;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCookCount() {
        return cookCount;
    }

    public void setCookCount(Integer cookCount) {
        this.cookCount = cookCount;
    }

    public Boolean getClean() {
        return clean;
    }

    public void setClean(Boolean clean) {
        this.clean = clean;
    }

    @Override
    public String toString() {
        return "CookerMachine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cookCount=" + cookCount +
                ", stock=" + stock +
                ", clean=" + clean +
                '}';
    }
}

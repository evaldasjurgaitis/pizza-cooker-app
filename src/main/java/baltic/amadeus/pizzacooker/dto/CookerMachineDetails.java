package baltic.amadeus.pizzacooker.dto;

public class CookerMachineDetails {
    private Integer id;
    private String name;
    private Integer cookCount;
    private StockDetails stockDetails;
    private Boolean clean;


    public CookerMachineDetails() {
    }

    public CookerMachineDetails(Integer id, String name, Integer cookCount, StockDetails stockDetails, Boolean clean) {
        this.id = id;
        this.name = name;
        this.cookCount = cookCount;
        this.stockDetails = stockDetails;
        this.clean = clean;
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

    public Integer getCookCount() {
        return cookCount;
    }

    public void setCookCount(Integer cookCount) {
        this.cookCount = cookCount;
    }

    public StockDetails getStockDetails() {
        return stockDetails;
    }

    public void setStockDetails(StockDetails stockDetails) {
        this.stockDetails = stockDetails;
    }

    public Boolean getClean() {
        return clean;
    }

    public void setClean(Boolean clean) {
        this.clean = clean;
    }
}

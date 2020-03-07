package baltic.amadeus.pizzacooker.dto;

public class ProductDetails {
    private String name;
    private Integer qty;

    public ProductDetails() {
    }

    public ProductDetails(String name, Integer qty) {
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

package baltic.amadeus.pizzacooker.component;

import baltic.amadeus.pizzacooker.dto.StockDetails;
import baltic.amadeus.pizzacooker.entity.Stock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class StockComponent {
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    ProductComponent productComponent;

    public StockDetails convertToDto(Stock stock) {
        StockDetails stockDetails = modelMapper.map(stock, StockDetails.class);
        stockDetails.setProductsDetails(productComponent.convertToDto(stock.getProducts()));
        return stockDetails;
    }
}

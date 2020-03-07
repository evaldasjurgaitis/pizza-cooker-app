package baltic.amadeus.pizzacooker.service;

import baltic.amadeus.pizzacooker.dao.CookerMachineDAO;
import baltic.amadeus.pizzacooker.dto.PizzaType;
import baltic.amadeus.pizzacooker.dto.CookerMachineDetails;
import baltic.amadeus.pizzacooker.dto.ProductDetails;
import baltic.amadeus.pizzacooker.entity.CookerMachine;
import baltic.amadeus.pizzacooker.entity.Stock;
import baltic.amadeus.pizzacooker.exception.CookerMachineNotFoundException;
import baltic.amadeus.pizzacooker.component.CookerMachineComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CookerMachineServiceImpl implements CookerMachineService {

    @Autowired
    CookerMachineDAO cookerMachineDAO;

    @Autowired
    RecipeService recipeService;

    @Autowired
    StockService stockService;

    @Autowired
    CookerMachineComponent cookerMachineComponent;

    @Value("${cooker-machine.cook-count.limit}")
    private Integer cookCountLimit;

    public CookerMachineDetails save(CookerMachineDetails cookerMachineDetails) {
        CookerMachine cookerMachine = cookerMachineComponent.convertToEntity(cookerMachineDetails);
        Stock stock = new Stock(cookerMachine.getStock().getName(), cookerMachine.getStock().getProducts());
        cookerMachine.setStock(stock);
        stock.setCookerMachine(cookerMachine);
        return cookerMachineComponent.convertToDto(cookerMachineDAO.save(cookerMachine));
    }

    public CookerMachineDetails get(Integer id) {
        Optional<CookerMachine> cookerMachine = cookerMachineDAO.findById(id);
        return cookerMachineComponent.convertToDto(cookerMachine.orElseThrow(()-> new CookerMachineNotFoundException(id)));
    }

    public List<CookerMachineDetails> list() {
        return cookerMachineComponent.convertToDto(cookerMachineDAO.findAll());
    }

    public void delete(Integer id) {
        cookerMachineDAO.deleteById(id);
    }

    public CookerMachineDetails cleanUp(Integer id) {
        CookerMachineDetails cookerMachineDetails = get(id);
        cookerMachineDetails.setCookCount(0);
        cookerMachineDetails.setClean(true);
        return update(cookerMachineDetails);
    }

    public CookerMachineDetails updateCookCount(Integer cookerMachineId) {
        CookerMachineDetails cookerMachineDetails = get(cookerMachineId);
        Integer cookCount = cookerMachineDetails.getCookCount()+1;
        if (cookCount == cookCountLimit) {
            cookerMachineDetails.setClean(false);
        }
        cookerMachineDetails.setCookCount(cookCount);
        return update(cookerMachineDetails);
    }

    public CookerMachineDetails updateReducedProducts(Integer cookerMachineId, Integer recipeId, PizzaType pizzaType) {
        CookerMachineDetails cookerMachineDetails = get(cookerMachineId);
        Set<ProductDetails> productsDetails = stockService.getReducedProducts(cookerMachineId, recipeId, pizzaType);
        cookerMachineDetails.getStockDetails().setProductsDetails(productsDetails);
        return update(cookerMachineDetails);
    }

    public Map<String, Integer> getCookerMachineProductsMap(Integer cookerMachineId) {
        Set<ProductDetails> cookerMachineProducts = get(cookerMachineId).getStockDetails().getProductsDetails();
        return cookerMachineProducts.stream().collect(
                Collectors.toMap(product -> product.getName(), product -> product.getQty()));
    }

    public CookerMachineDetails update(CookerMachineDetails cookerMachineDetails) {
        CookerMachine cookerMachine = cookerMachineComponent.convertToEntity(cookerMachineDetails);
        return cookerMachineComponent.convertToDto(cookerMachineDAO.save(cookerMachine));
    }

}

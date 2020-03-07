package baltic.amadeus.pizzacooker.unit.service;

import baltic.amadeus.pizzacooker.dao.CookerMachineDAO;
import baltic.amadeus.pizzacooker.dto.PizzaType;
import baltic.amadeus.pizzacooker.dto.CookerMachineDetails;
import baltic.amadeus.pizzacooker.dto.ProductDetails;
import baltic.amadeus.pizzacooker.dto.StockDetails;
import baltic.amadeus.pizzacooker.entity.CookerMachine;
import baltic.amadeus.pizzacooker.entity.Product;
import baltic.amadeus.pizzacooker.entity.Stock;
import baltic.amadeus.pizzacooker.service.*;
import baltic.amadeus.pizzacooker.component.CookerMachineComponent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;


@RunWith(MockitoJUnitRunner.class)
public class CookerMachineServiceTest {

    @Mock
    private CookerMachineDAO cookerMachineDAO;

    @Mock
    private StockService stockService;

    @Mock
    CookerMachineComponent cookerMachineComponent;

    @InjectMocks
    private CookerMachineServiceImpl cookerMachineService;

    private CookerMachineDetails cookerMachineDetails;

    private StockDetails stockDetails;

    private CookerMachine cookerMachine;

    private Stock stock;
    private final Integer COOKER_MACHINE_ID = 1;
    private final Integer EXPECTED_COOKER_COUNT = 0;
    private final Integer EXPECTED_COOKER_COUNT_UPDATED = 11;

    @Before
    public void setUp() {
        cookerMachineService = Mockito.spy(cookerMachineService);

        Set<ProductDetails> stockProductsDetails = new HashSet<>();
        stockProductsDetails.add(new ProductDetails("dough", 10));
        stockProductsDetails.add(new ProductDetails("cheese", 10));
        stockProductsDetails.add(new ProductDetails("salemi", 10));
        stockDetails = new StockDetails(1, "Alfa-stock", stockProductsDetails);
        cookerMachineDetails = new CookerMachineDetails(1, "Alfa", 10, stockDetails, true);

        Set<Product> stockProducts = new HashSet<>();
        stockProducts.add(new Product("dough", 10));
        stockProducts.add(new Product("cheese", 10));
        stockProducts.add(new Product("salemi", 10));
        stock = new Stock(1, "Alfa-stock", stockProducts);
        cookerMachine = new CookerMachine(1, "Alfa", 10, true);
        cookerMachine.setStock(stock);
    }

    @Test
    public void shoulSaveCookerMachine() {
        when(cookerMachineComponent.convertToEntity(cookerMachineDetails)).thenReturn(cookerMachine);
        cookerMachineService.save(cookerMachineDetails);
        Mockito.verify(cookerMachineDAO, times(1)).save(any());
        Mockito.verify(cookerMachineComponent, times(1)).convertToEntity(any());
    }

    @Test
    public void shoulGetCookerMachine() {
        Optional<CookerMachine> expectedCookerMachine = Optional.of(cookerMachine);
        when(cookerMachineDAO.findById(cookerMachine.getId())).thenReturn(expectedCookerMachine);
        when(cookerMachineComponent.convertToDto(cookerMachine)).thenReturn(cookerMachineDetails);
        CookerMachineDetails actualCookerMachineDetails = cookerMachineService.get(COOKER_MACHINE_ID);
        Mockito.verify(cookerMachineDAO, times(1)).findById(any());
        Assert.assertEquals(expectedCookerMachine.get().getId(), actualCookerMachineDetails.getId());
        Assert.assertEquals(expectedCookerMachine.get().getCookCount(), actualCookerMachineDetails.getCookCount());
        Assert.assertEquals(expectedCookerMachine.get().getName(), actualCookerMachineDetails.getName());
    }

    @Test
    public void shouldGetListCookerMachines() {
        List<CookerMachine> expectedList = mock(List.class);
        when(cookerMachineDAO.findAll()).thenReturn(expectedList);
        List<CookerMachineDetails> actualList = cookerMachineService.list();
        Assert.assertEquals(expectedList.size(), actualList.size());
        Mockito.verify(cookerMachineDAO, times(1)).findAll();
    }

    @Test
    public void shouldDeleteCookerMachine() {
        when(cookerMachineDAO.save(cookerMachine)).thenReturn(cookerMachine);
        when(cookerMachineComponent.convertToEntity(cookerMachineDetails)).thenReturn(cookerMachine);
        when(cookerMachineComponent.convertToDto(cookerMachine)).thenReturn(cookerMachineDetails);
        cookerMachineService.save(cookerMachineDetails);
        cookerMachineService.delete(COOKER_MACHINE_ID);
        Mockito.verify(cookerMachineDAO, times(1)).deleteById(any());
    }

    @Test
    public void shouldCleanUpCookerMachine() {
        when(cookerMachineDAO.findById(COOKER_MACHINE_ID)).thenReturn(Optional.of(cookerMachine));
        when(cookerMachineDAO.save(cookerMachine)).thenReturn(cookerMachine);
        when(cookerMachineComponent.convertToEntity(cookerMachineDetails)).thenReturn(cookerMachine);
        when(cookerMachineComponent.convertToDto(cookerMachine)).thenReturn(cookerMachineDetails);
        cookerMachineService.save(cookerMachineDetails);
        CookerMachineDetails actualCookerMachine = cookerMachineService.cleanUp(COOKER_MACHINE_ID);
        Mockito.verify(cookerMachineDAO, times(1)).findById(any());
        Mockito.verify(cookerMachineDAO, times(2)).save(any());
        Assert.assertEquals(EXPECTED_COOKER_COUNT, actualCookerMachine.getCookCount());
    }

    @Test
    public void shouldUpdateCookCount() {
        when(cookerMachineDAO.findById(COOKER_MACHINE_ID)).thenReturn(Optional.of(cookerMachine));
        when(cookerMachineDAO.save(cookerMachine)).thenReturn(cookerMachine);
        when(cookerMachineComponent.convertToEntity(cookerMachineDetails)).thenReturn(cookerMachine);
        when(cookerMachineComponent.convertToDto(cookerMachine)).thenReturn(cookerMachineDetails);
        cookerMachineService.save(cookerMachineDetails);
        CookerMachineDetails actualCookerMachine = cookerMachineService.updateCookCount(COOKER_MACHINE_ID);
        Mockito.verify(cookerMachineDAO, times(1)).findById(any());
        Mockito.verify(cookerMachineDAO, times(2)).save(any());
        Assert.assertEquals(EXPECTED_COOKER_COUNT_UPDATED, actualCookerMachine.getCookCount());
    }

    @Test
    public void shouldUpdateReducedProducts() {
        Set<ProductDetails> expectedStockProductsDetails = new HashSet<>();
        expectedStockProductsDetails.add(new ProductDetails("dough", 9));
        expectedStockProductsDetails.add(new ProductDetails("cheese", 9));
        expectedStockProductsDetails.add(new ProductDetails("salemi", 9));
        List<ProductDetails> expectedProductsDetailsList = expectedStockProductsDetails.stream().collect(Collectors.toList());
        when(cookerMachineDAO.findById(COOKER_MACHINE_ID)).thenReturn(Optional.of(cookerMachine));
        when(cookerMachineDAO.save(cookerMachine)).thenReturn(cookerMachine);
        when(cookerMachineComponent.convertToEntity(cookerMachineDetails)).thenReturn(cookerMachine);
        when(cookerMachineComponent.convertToDto(cookerMachine)).thenReturn(cookerMachineDetails);
        when(stockService.getReducedProducts(COOKER_MACHINE_ID, 1, PizzaType.SMALL)).thenReturn(expectedStockProductsDetails);
        CookerMachineDetails actualCookerMachineDetails = cookerMachineService.updateReducedProducts(COOKER_MACHINE_ID, 1, PizzaType.SMALL);
        List<ProductDetails> actualProductsDetaisList = actualCookerMachineDetails.getStockDetails().getProductsDetails().stream().collect(Collectors.toList());
        Assert.assertEquals(expectedProductsDetailsList.get(0).getName(), actualProductsDetaisList.get(0).getName());
        Assert.assertEquals(expectedProductsDetailsList.get(0).getQty(), actualProductsDetaisList.get(0).getQty());
        Assert.assertEquals(expectedProductsDetailsList.get(1).getName(), actualProductsDetaisList.get(1).getName());
        Assert.assertEquals(expectedProductsDetailsList.get(1).getQty(), actualProductsDetaisList.get(1).getQty());
        Assert.assertEquals(expectedProductsDetailsList.get(2).getName(), actualProductsDetaisList.get(2).getName());
        Assert.assertEquals(expectedProductsDetailsList.get(2).getQty(), actualProductsDetaisList.get(2).getQty());
    }

    @Test
    public void shoulGetCookerMachineProductsMap() {
        Map<String, Integer> expectedProductsMap = new HashMap<>();
        expectedProductsMap.put("dough", 10);
        expectedProductsMap.put("cheese", 10);
        expectedProductsMap.put("salemi", 10);
        when(cookerMachineDAO.findById(COOKER_MACHINE_ID)).thenReturn(Optional.of(cookerMachine));
        when(cookerMachineComponent.convertToDto(cookerMachine)).thenReturn(cookerMachineDetails);
        Map<String, Integer> actualProductsMap = cookerMachineService.getCookerMachineProductsMap(COOKER_MACHINE_ID);

        Assert.assertEquals(expectedProductsMap.get("dough"), actualProductsMap.get("dough"));
        Assert.assertEquals(expectedProductsMap.get("cheese"), actualProductsMap.get("cheese"));
        Assert.assertEquals(expectedProductsMap.get("salemi"), actualProductsMap.get("salemi"));
    }
}

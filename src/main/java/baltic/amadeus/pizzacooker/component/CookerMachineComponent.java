package baltic.amadeus.pizzacooker.component;

import baltic.amadeus.pizzacooker.dto.CookerMachineDetails;
import baltic.amadeus.pizzacooker.entity.CookerMachine;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CookerMachineComponent {
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    StockComponent stockComponent;

    public CookerMachineDetails convertToDto(CookerMachine cookerMachine) {
        CookerMachineDetails cookerMachineDetails = modelMapper.map(cookerMachine, CookerMachineDetails.class);
        cookerMachineDetails.setStockDetails(stockComponent.convertToDto(cookerMachine.getStock()));
        return cookerMachineDetails;
    }

    public List<CookerMachineDetails> convertToDto(List<CookerMachine> cookerMachines) {
        return cookerMachines.stream()
                .map(cookerMachine -> convertToDto(cookerMachine))
                .collect(Collectors.toList());
    }

    public CookerMachine convertToEntity(CookerMachineDetails cookerMachineDetails) {
        CookerMachine cookerMachine = modelMapper.map(cookerMachineDetails, CookerMachine.class);
        return cookerMachine;
    }
}

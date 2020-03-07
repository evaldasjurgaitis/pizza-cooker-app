package baltic.amadeus.pizzacooker.controller;

import baltic.amadeus.pizzacooker.dto.MessageType;
import baltic.amadeus.pizzacooker.dto.CookerMachineDetails;
import baltic.amadeus.pizzacooker.dto.PizzaDetails;
import baltic.amadeus.pizzacooker.service.CookerMachineService;
import baltic.amadeus.pizzacooker.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(value = "/cooker-machines/")
public class CookerMachineController {
    @Autowired
    CookerMachineService cookerMachineService;

    @Autowired
    PizzaService pizzaService;

    @PutMapping
    public ResponseEntity<CookerMachineDetails> save(@RequestBody CookerMachineDetails cookerMachineDetails) {
        return status(HttpStatus.OK).body(cookerMachineService.save(cookerMachineDetails));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CookerMachineDetails> get(@PathVariable("id") Integer id) {
        return status(HttpStatus.OK).body(cookerMachineService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<CookerMachineDetails>> list() {
        return status(HttpStatus.OK).body(cookerMachineService.list());
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Integer id) {
        cookerMachineService.delete(id);
    }

    @GetMapping(value = "{id}/cleanup")
    public ResponseEntity<PizzaDetails> cleanup(@PathVariable("id") Integer id) {
        cookerMachineService.cleanUp(id);
        return status(OK).body(new PizzaDetails(null, MessageType.CLEANED_COOKER_MACHINE));
    }

    @GetMapping(value = "{machineId}/pizza/{recipeId}/size/{size}")
    public ResponseEntity<PizzaDetails> getPizza(@PathVariable("machineId") Integer machineId, @PathVariable("recipeId") Integer recipeId, @PathVariable("size") Integer size) {
        return status(OK).body(pizzaService.getPizza(machineId, recipeId, size));
    }
}

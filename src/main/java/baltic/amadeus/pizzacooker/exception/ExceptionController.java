package baltic.amadeus.pizzacooker.exception;

import baltic.amadeus.pizzacooker.dto.MessageType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex,  WebRequest request) {
        return new ResponseEntity<>(new ExceptionResponse(MessageType.INTERNAL_SERVER_ERROR, ex.getMessage(), request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CookerMachineNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(CookerMachineNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(new ExceptionResponse(MessageType.NOT_FOUND_COOKER_MACHINE, ex.getMessage(), request.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecipeNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(RecipeNotFoundException ex,  WebRequest request) {
        return new ResponseEntity<>(new ExceptionResponse(MessageType.NOT_FOUND_RECIPE, ex.getMessage(), request.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StockNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(StockNotFoundException ex,  WebRequest request) {
        return new ResponseEntity<>(new ExceptionResponse(MessageType.NOT_FOUND_STOCK, ex.getMessage(), request.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PizzaTypeNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(PizzaTypeNotFoundException ex,  WebRequest request) {
        return new ResponseEntity<>(new ExceptionResponse(MessageType.NO_PIZZA_TYPE, ex.getMessage(), request.getDescription(false)), HttpStatus.NOT_FOUND);
    }
}

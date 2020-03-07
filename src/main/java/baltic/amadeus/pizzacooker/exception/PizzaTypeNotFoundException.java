package baltic.amadeus.pizzacooker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class PizzaTypeNotFoundException extends RuntimeException {
    public PizzaTypeNotFoundException(String exception) {
        super(exception);
    }
}

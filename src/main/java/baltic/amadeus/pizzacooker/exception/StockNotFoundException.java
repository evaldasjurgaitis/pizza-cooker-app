package baltic.amadeus.pizzacooker.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class StockNotFoundException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(CookerMachineNotFoundException.class);
    private String message;

    public StockNotFoundException(Integer id) {
        this.message = "Not found stock: " + id;
        logger.error(message);
        new StockNotFoundException(message);
    }
    private StockNotFoundException(String exception) {
        super(exception);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package baltic.amadeus.pizzacooker.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class CookerMachineNotFoundException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(CookerMachineNotFoundException.class);
    private String message;

    public CookerMachineNotFoundException(String exception) {
        super(exception);
    }

    public CookerMachineNotFoundException(Integer id) {
        this.message = "Not found cookerMachine: " + id;
        logger.error(message);
        new CookerMachineNotFoundException(message);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

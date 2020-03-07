package baltic.amadeus.pizzacooker.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class RecipeNotFoundException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(RecipeNotFoundException.class);
    private String message;

    public RecipeNotFoundException(Integer id) {
        this.message = "Not found recipe: " + id;
        logger.error(message);
        new RecipeNotFoundException(message);
    }

    private RecipeNotFoundException(String exception) {
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

package baltic.amadeus.pizzacooker.dto;

public enum MessageType {
    INTERNAL_SERVER_ERROR("Internal server error"),
    NOT_FOUND_COOKER_MACHINE("Not found cookerMachine"),
    NOT_FOUND_RECIPE("Not found recipe"),
    NOT_FOUND_STOCK("Not found stock"),
    NO_ENOUGH_PRODUCTS("Sorry, not enough products, we can not offer anything."),
    NO_PIZZA_TYPE("Selected pizza type not found."), 
    CLEAN_COOKER_MACHINE("Please clean up cooker machine."),
    CLEANED_COOKER_MACHINE("Cooker Machine cleaned up"),
    OFFER_PIZZA("Sorry, not enough products for pizza. We offer another pizza. Bon appetite!"),
    PIZZA_PRODUCED("Take Your Pizza. Bon appetite!");

    private final String message;

    MessageType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

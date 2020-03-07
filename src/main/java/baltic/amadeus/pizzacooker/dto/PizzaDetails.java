package baltic.amadeus.pizzacooker.dto;

public class PizzaDetails {
    private Pizza pizza;
    private MessageType messageType;
    private String message;

    public PizzaDetails() {
    }

    public PizzaDetails(Pizza pizza, MessageType messageType) {
        this.pizza = pizza;
        this.messageType = messageType;
        this.message = messageType.getMessage();
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

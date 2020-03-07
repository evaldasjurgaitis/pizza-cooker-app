package baltic.amadeus.pizzacooker.dto;

import baltic.amadeus.pizzacooker.exception.PizzaTypeNotFoundException;

import java.util.Arrays;

public enum PizzaType {
    SMALL(1), MID(2), LARGE(3);

    private final int value;

    private PizzaType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PizzaType valueOf(int value) {
        return Arrays.stream(values())
                .filter(pizzaType -> values().length >= value && pizzaType.value == value)
                .findAny().orElseThrow(()-> new PizzaTypeNotFoundException(MessageType.NO_PIZZA_TYPE.getMessage()));
    }
}

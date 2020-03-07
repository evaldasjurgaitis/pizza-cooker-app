package baltic.amadeus.pizzacooker.exception;

import baltic.amadeus.pizzacooker.dto.MessageType;

public class ExceptionResponse {
    private MessageType messageType;
    private String message;
    private String details;

    public ExceptionResponse(MessageType messageType, String message, String details) {
        this.messageType = messageType;
        this.message = message;
        this.details = details;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

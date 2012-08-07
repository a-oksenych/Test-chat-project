package com.copyright.rup.chat.room;

/**
 * @author Oleksandr Dekhtyar
 * 
 */
public class RoomException extends RuntimeException {

    private static final long serialVersionUID = -1746366491789644432L;

    public enum Type {
        NOT_FOUND,
        OTHER
    }

    private Type type = Type.OTHER;

    public RoomException(Type type, Throwable e) {
        super(e);
        this.type = type;
    }

    public RoomException(Type type, String message) {
        super(message);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

}

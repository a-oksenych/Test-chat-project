package com.copyright.rup.chat.account;

/**
 * @author Oleksandr Dekhtyar
 * 
 */
public class AccountException extends RuntimeException {

    private static final long serialVersionUID = 614314455279065305L;

    public enum Type {
        NOT_FOUND,
        OTHER
    }

    private Type type = Type.OTHER;

    public AccountException(Type type, Throwable e) {
        super(e);
        this.type = type;
    }

    public AccountException(Type type, String message) {
        super(message);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

}

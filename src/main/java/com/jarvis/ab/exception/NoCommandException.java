package com.jarvis.ab.exception;

/**
 * Created by qiu.yuan on 2017/2/14.
 */
public class NoCommandException extends CommandException {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public NoCommandException(String message) {
        super(message);
    }
}

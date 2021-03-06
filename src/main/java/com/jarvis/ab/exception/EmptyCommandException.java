package com.jarvis.ab.exception;

/**
 * Created by jarvis on 10/02/2017.
 */
public class EmptyCommandException extends CommandException {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public EmptyCommandException(String message) {
        super(message);
    }
}

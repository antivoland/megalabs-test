package ru.ml.pyramid.server.model;

public class PyramidException extends Exception {
    public PyramidException(String message) {
        super(message);
    }

    public PyramidException(String message, Throwable cause) {
        super(message, cause);
    }
}

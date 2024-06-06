package com.rakbank.busra.app.user.exceptions;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(Long id) {
        super("Event with ID " + id + " not found");
    }
}

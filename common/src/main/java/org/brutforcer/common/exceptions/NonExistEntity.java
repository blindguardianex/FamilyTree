package org.brutforcer.common.exceptions;

public class NonExistEntity extends RuntimeException{

    public NonExistEntity(String message) {
        super(message);
    }
}

package org.brutforcer.common.exceptions;

public class UpdatingNonExistEntity extends RuntimeException{

    public UpdatingNonExistEntity(String message) {
        super(message);
    }
}

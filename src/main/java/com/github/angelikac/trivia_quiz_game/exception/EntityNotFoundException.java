package com.github.angelikac.trivia_quiz_game.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class entityClass) {
        super("Entity of class " + entityClass.getSimpleName() + " not found");
    }
}

package com.supero.desafio.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Não foi possível encontrar tarefa com id: " + id);
    }
}

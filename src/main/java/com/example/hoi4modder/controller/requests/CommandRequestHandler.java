package com.example.hoi4modder.controller.requests;

/**
 * Extends functionality of request handler with additional commands to handle command history
 * @param <T> - request handler generic type
 */
public interface CommandRequestHandler<T> extends RequestHandler<T>{
    T addCommand(T after);
    int removeCommand(T model);
    T duplicateCommand(T model);
}

package com.example.hoi4modder.controller.requests;

public interface CommandRequestHandler<T> extends RequestHandler<T>{
    T addCommand(T after);
    int removeCommand(T model);
    T duplicateCommand(T model);
}

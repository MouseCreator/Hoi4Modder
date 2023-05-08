package com.example.hoi4modder.controller.requests;

/**
 * Interface for request from list item to list controller
 * @param <T> - generic, common for item and controller
 */
public interface Request<T> {
    /**
     * Visit request by handler
     * @param handler - handler that will proceed the request
     */
    void handleWith(RequestHandler<T> handler);
}

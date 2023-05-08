package com.example.hoi4modder.controller.requests;

/**
 * Receives request and responds, using controller methods
 * @param <T> - generic value, inherited from controller
 */
public interface RequestHandler<T> {
    /**
     * Checks if item is present
     * @param request - request, containing which item to check
     */
    void handle(ItemPresentRequest<T> request);

    /**
     * Creates a duplicate of some element
     * @param request - request, containing which item to duplicate
     */
    void handle(DuplicateRequest<T> request);

    /**
     * Receive request
     * @param request - request to be executed
     */
    void onRequest(Request<T> request);

    /**
     * Removes element from the list
     * @param removeRequest - request, containing which item to remove
     */
    void handle(RemoveRequest<T> removeRequest);

    /**
     * Adds element to the list
     * @param tAddRequest - request, containing position to add element
     */
    void handle(AddRequest<T> tAddRequest);
}

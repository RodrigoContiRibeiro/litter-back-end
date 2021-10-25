package com.litter.litter.payload.request.book;

public class DeleteBookRequest {
    private Long id;

    public DeleteBookRequest() {
    }

    public DeleteBookRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}

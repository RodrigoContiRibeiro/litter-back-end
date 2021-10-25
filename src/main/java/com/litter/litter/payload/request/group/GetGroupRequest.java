package com.litter.litter.payload.request.group;

public class GetGroupRequest {
    private Long id;

    public GetGroupRequest(){}

    public GetGroupRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}

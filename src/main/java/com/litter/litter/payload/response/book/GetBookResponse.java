package com.litter.litter.payload.response.book;

public class GetBookResponse {
    private Long id;
    private String title;
    private String author;
    private String synopsis;
    private String imageUrl;

    public GetBookResponse(Long id, String title, String author, String synopsis, String imageUrl) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.synopsis = synopsis;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getSynopsis() {
        return synopsis;
    }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

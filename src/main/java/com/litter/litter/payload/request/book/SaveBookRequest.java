package com.litter.litter.payload.request.book;

import javax.validation.constraints.NotBlank;

public class SaveBookRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String synopsis;

    @NotBlank
    private String imageUrl;

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

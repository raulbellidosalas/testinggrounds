package com.acme.catchup.platform.news.interfaces.rest.resources;

public record CreateFavoriteSourceResource(String newsApiKey, String sourceId) {
    /**
     * Creates a new instance of CreateFavoriteSourceResource.
     *
     * @param newsApiKey The API key for the news service.
     * @param sourceId   The ID of the news source to be added as a favorite.
     */
    public CreateFavoriteSourceResource {
        if (newsApiKey == null || newsApiKey.isBlank()) {
            throw new IllegalArgumentException("newsApiKey cannot be null or blank");
        }
        if (sourceId == null || sourceId.isBlank()) {
            throw new IllegalArgumentException("sourceId cannot be null or blank");
        }
    }
}



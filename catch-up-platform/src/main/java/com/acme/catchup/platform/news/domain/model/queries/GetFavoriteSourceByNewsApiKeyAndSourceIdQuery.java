package com.acme.catchup.platform.news.domain.model.queries;

/**
 * GetFavoriteSourceByIdQuery
 * @param id The ID of the favorite source to retrieve
 * @summary
 * GetFavoriteSourceByIdQuery is a record that represents a query to retrieve a favorite news source by its ID.
 */
public record GetFavoriteSourceByNewsApiKeyAndSourceIdQuery(String newsApiKey, String sourceId) {

    public GetFavoriteSourceByNewsApiKeyAndSourceIdQuery {
        if (newsApiKey == null || newsApiKey.isBlank()) {
            throw new IllegalArgumentException("newsApiKey must not be null or blank");
        }
        if (sourceId == null || sourceId.isBlank()) {
            throw new IllegalArgumentException("sourceId must not be null or blank");
        }
    }
}

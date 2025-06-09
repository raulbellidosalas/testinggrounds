package com.acme.catchup.platform.news.domain.model.queries;

/**
 * GetFavoriteSourceByIdQuery
 * @param id The ID of the favorite source to retrieve
 * @summary
 * GetFavoriteSourceByIdQuery is a record that represents a query to retrieve a favorite news source by its ID.
 */
public record GetAllFavoriteSourcesByNewsApiKeyQuery(String newsApiKey) {

    public GetAllFavoriteSourcesByNewsApiKeyQuery {
        if (newsApiKey == null || newsApiKey.isBlank()) {
            throw new IllegalArgumentException("newsApiKey must not be null or blank");
        }
    }
}

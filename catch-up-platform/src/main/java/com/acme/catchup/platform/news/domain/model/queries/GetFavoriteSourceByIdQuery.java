package com.acme.catchup.platform.news.domain.model.queries;

/**
 * GetFavoriteSourceByIdQuery
 * @param id The ID of the favorite source to retrieve
 * @summary
 * GetFavoriteSourceByIdQuery is a record that represents a query to retrieve a favorite news source by its ID.
 */
public record GetFavoriteSourceByIdQuery(Long id)  {

    public GetFavoriteSourceByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("id must not be null or less than or equal to zero");
        }
    }
}

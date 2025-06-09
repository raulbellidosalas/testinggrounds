package com.acme.catchup.platform.news.domain.services;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.queries.GetAllFavoriteSourcesByNewsApiKeyQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByIdQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByNewsApiKeyAndSourceIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * @name FavoriteSourceQueryService
 * @summary
 * This interface represents the service to handle favorite source queries.
 * @since 1.0
 */
public interface FavoriteSourceQueryService {
    /**
     * Handles the query to get all favorite sources by newsApiKey.
     * @param query The query containing the newsApiKey.
     * @return A list of FavoriteSource objects associated with the given newsApiKey.
     * @throws IllegalArgumentException if newsApiKey is null or blank.
     * @see GetAllFavoriteSourcesByNewsApiKeyQuery
     */
    List<FavoriteSource> handle(GetAllFavoriteSourcesByNewsApiKeyQuery query);

    /**
     * Handles the query to get a favorite source by its ID.
     *
     * @param query The query containing the ID of the favorite source.
     * @return An Optional containing the FavoriteSource if found, otherwise empty.
     * @throws IllegalArgumentException if the ID is null or blank.
     * @see GetFavoriteSourceByIdQuery
     */
    Optional<FavoriteSource> handle(GetFavoriteSourceByIdQuery query);

    /**
     * Handles the query to get a favorite source by newsApiKey and sourceId.
     * @param query The query containing the newsApiKey and sourceId.
     * @return An Optional containing the FavoriteSource if found, otherwise empty.
     * @throws IllegalArgumentException if newsApiKey or sourceId is null or blank.
     * @see GetFavoriteSourceByNewsApiKeyAndSourceIdQuery
     */
    Optional<FavoriteSource> handle(GetFavoriteSourceByNewsApiKeyAndSourceIdQuery query);
}

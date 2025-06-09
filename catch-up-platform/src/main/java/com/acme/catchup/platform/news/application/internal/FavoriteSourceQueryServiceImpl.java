package com.acme.catchup.platform.news.application.internal;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.queries.GetAllFavoriteSourcesByNewsApiKeyQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByIdQuery;
import com.acme.catchup.platform.news.domain.model.queries.GetFavoriteSourceByNewsApiKeyAndSourceIdQuery;
import com.acme.catchup.platform.news.domain.services.FavoriteSourceQueryService;
import com.acme.catchup.platform.news.infrastructure.persistence.jpa.FavoriteSourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteSourceQueryServiceImpl implements FavoriteSourceQueryService {
    private final FavoriteSourceRepository favoriteSourceRepository;

    public FavoriteSourceQueryServiceImpl(FavoriteSourceRepository favoriteSourceRepository) {
        this.favoriteSourceRepository = favoriteSourceRepository;
    }

    /**
     * Handles the query to get all favorite sources by newsApiKey.
     *
     * @param query The query containing the newsApiKey.
     * @return A list of FavoriteSource objects associated with the given newsApiKey.
     * @throws IllegalArgumentException if newsApiKey is null or blank.
     * @see GetAllFavoriteSourcesByNewsApiKeyQuery
     */
    @Override
    public List<FavoriteSource> handle(GetAllFavoriteSourcesByNewsApiKeyQuery query) {
        return favoriteSourceRepository.findAllByNewsApiKey(query.newsApiKey());
    }

    /**
     * Handles the query to get a favorite source by its ID.
     *
     * @param query The query containing the ID of the favorite source.
     * @return An Optional containing the FavoriteSource if found, otherwise empty.
     * @throws IllegalArgumentException if the ID is null or blank.
     * @see GetFavoriteSourceByIdQuery
     */
    @Override
    public Optional<FavoriteSource> handle(GetFavoriteSourceByIdQuery query) {
        return favoriteSourceRepository.findById(query.id());
    }

    /**
     * Handles the query to get a favorite source by newsApiKey and sourceId.
     *
     * @param query The query containing the newsApiKey and sourceId.
     * @return An Optional containing the FavoriteSource if found, otherwise empty.
     * @throws IllegalArgumentException if newsApiKey or sourceId is null or blank.
     * @see GetFavoriteSourceByNewsApiKeyAndSourceIdQuery
     */
    @Override
    public Optional<FavoriteSource> handle(GetFavoriteSourceByNewsApiKeyAndSourceIdQuery query) {
        return favoriteSourceRepository.findByNewsApiKeyAndSourceId(query.newsApiKey(), query.sourceId());
    }
}

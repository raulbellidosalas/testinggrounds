package com.acme.catchup.platform.news.application.internal;

import com.acme.catchup.platform.news.domain.model.aggregates.FavoriteSource;
import com.acme.catchup.platform.news.domain.model.commands.CreateFavoriteSourceCommand;
import com.acme.catchup.platform.news.domain.services.FavoriteSourceCommandService;
import com.acme.catchup.platform.news.infrastructure.persistence.jpa.FavoriteSourceRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoriteSourceCommandServiceImpl implements FavoriteSourceCommandService {

    private final FavoriteSourceRepository favoriteSourceRepository;

    public FavoriteSourceCommandServiceImpl(FavoriteSourceRepository favoriteSourceRepository) {
        this.favoriteSourceRepository = favoriteSourceRepository;
    }


    /**
     * Handles the create favorite source command.
     *
     * @param command The crete favorite source command.
     * @return The created favorite source.
     * @throws IllegalArgumentException If newsApiKey or sourceId is null or blank.
     * @see CreateFavoriteSourceCommand
     */
    @Override
    public Optional<FavoriteSource> handle(CreateFavoriteSourceCommand command) {
        if (favoriteSourceRepository.existsByNewsApiKeyAndSourceId(command.newsApikey(), command.sourceId()))
            throw new IllegalArgumentException("Favorite source already exists for the given newsApiKey and sourceId.");
        var favoriteSource = new FavoriteSource(command.sourceId(), command.newsApikey());
        var createdFavoriteSource = favoriteSourceRepository.save(favoriteSource);
        return Optional.of(createdFavoriteSource);
    }
}

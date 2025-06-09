package com.acme.catchup.platform.news.domain.model.commands;

/**
 * CreateFavoriteSourceCommand
 * @param newsApikey News API key used to authenticate the request
 * @param sourceId ID of the news source to be added as a favorite
 * @summary
 * CreateFavoriteSourceCommand is a record that represents a command to create a favorite news source.
 */
public record CreateFavoriteSourceCommand(String newsApikey, String sourceId) {

    /**
     * Validates the command parameters.
     * @throws IllegalArgumentException if newsApiKey or sourceId is null or blank
     */
    public CreateFavoriteSourceCommand {
        if (newsApikey == null || newsApikey.isBlank()) {
            throw new IllegalArgumentException("newsApiKey must not be null or blank");
        }
        if (sourceId == null || sourceId.isBlank()) {
            throw new IllegalArgumentException("sourceId must not be null or blank");
        }
    }
}

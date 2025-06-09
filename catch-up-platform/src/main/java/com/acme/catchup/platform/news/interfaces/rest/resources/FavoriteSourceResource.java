package com.acme.catchup.platform.news.interfaces.rest.resources;

/**
 * Resource representation for a favorite news source.
 * @summary FavoriteSourceResource
 * This record represents the resource for a favorite news source
 */
public record FavoriteSourceResource(Long id, String newsApiKey, String sourceId) {
}


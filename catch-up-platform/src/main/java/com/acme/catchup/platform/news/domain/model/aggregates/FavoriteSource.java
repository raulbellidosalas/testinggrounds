package com.acme.catchup.platform.news.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * FavoriteSource Aggregate Root
 * @summary FavoriteSource
 * The FavoriteSource class is an aggregate root that represents a favorite news source in the system.
 * It contains the sourceId and the associated newsApiKey.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class FavoriteSource extends AbstractAggregateRoot<FavoriteSource> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private String newsApiKey;

    @Column(nullable = false)
    @Getter
    private String sourceId;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private Date updatedAt;

    protected FavoriteSource() {}

    /**
     * @summary Constructor for FavoriteSource
     * It creates a new FavoriteSource with the given sourceId and newsApiKey.
     * @param sourceId
     * @param newsApiKey
     */
    public FavoriteSource(String sourceId, String newsApiKey) {
        this.sourceId = sourceId;
        this.newsApiKey = newsApiKey;
    }
}

package com.danwsaps.catalog.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 234579086633644246L;

    @Column(
            name = "secure_id",
            length = 36,
            nullable = false,
            unique = true,
            updatable = false
    )
    private String secureId;

    @Column(
            name = "deleted",
            columnDefinition = "boolean default false",
            nullable = false
    )
    private Boolean deleted;

    @CreationTimestamp
    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(
            name = "updated_at",
            nullable = false
    )
    private LocalDateTime updatedAt;

    @PrePersist
    protected void prePersist() {
        if (this.deleted == null) {
            this.deleted = false;
        }

        if (this.secureId == null) {
            this.secureId = UUID.randomUUID().toString();
        }
    }

}

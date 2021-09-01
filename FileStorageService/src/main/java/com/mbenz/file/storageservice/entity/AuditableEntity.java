package com.mbenz.file.storageservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity {

    @CreatedBy
    @Column(nullable = false, updatable = false)
    @JsonIgnore
    private String createdBy;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @JsonIgnore
    private Timestamp createdOn;

    @LastModifiedBy
    @Column
    @JsonIgnore
    private String lastUpdatedBy;

    @LastModifiedDate
    @Column
    @JsonIgnore
    private Timestamp lastUpdatedOn;
}

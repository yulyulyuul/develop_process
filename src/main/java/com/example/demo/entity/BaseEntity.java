package com.example.demo.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseEntity {

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDateTime;

    @LastModifiedBy
    private String updatedBy;

    @LastModifiedDate
    private LocalDateTime updateDateTime;

    @Column(nullable = false, columnDefinition = "BOOLEAN")
    protected boolean deleted = Boolean.FALSE;

    public void delete() {
        deleted = Boolean.TRUE;
    }

    public boolean isDeleted() {
        return this.deleted;
    }
}

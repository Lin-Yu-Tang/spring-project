package com.tom.jpatest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class SoftDeleteEntity<T, E> implements DeleteAuditable {

    @Column(name = "IS_DELETED", nullable = false)
    private boolean isDeleted = false;
}

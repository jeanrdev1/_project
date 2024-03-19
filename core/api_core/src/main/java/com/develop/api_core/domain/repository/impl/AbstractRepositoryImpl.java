package com.develop.api_core.domain.repository.impl;

import com.develop.api_core.domain.entity.AbstractEntity;
import com.develop.api_core.domain.repository.AbstractRepository;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class AbstractRepositoryImpl<T extends AbstractEntity> extends SimpleJpaRepository<T, Long> implements AbstractRepository<T> {

    public AbstractRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
    }
}

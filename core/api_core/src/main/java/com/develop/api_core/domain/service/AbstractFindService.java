package com.develop.api_core.domain.service;

import com.develop.api_core.domain.entity.AbstractEntity;
import com.develop.api_core.domain.repository.AbstractRepository;
import com.develop.api_core.exception.NoContentException;
import com.develop.api_core.exception.NotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public abstract class AbstractFindService<T extends AbstractEntity> extends AbstractService {

    protected final AbstractRepository<T> repository;

    public AbstractFindService(AbstractRepository<T> repository) {
        this.repository = repository;
    }

    public ResponseEntity<List<T>> findAll() {
        List<T> entities = repository.findAll();
        if (entities.isEmpty()) throw new NoContentException();
        return ResponseEntity.ok(entities);
    }

    @SuppressWarnings("null")
    public ResponseEntity<T> findById(Long id) {
        T entity = repository.findById(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(entity);
    }
}

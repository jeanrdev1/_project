package com.develop.api_core.domain.service.impl;

import com.develop.api_core.domain.entity.AbstractEntity;
import com.develop.api_core.domain.repository.AbstractRepository;
import com.develop.api_core.domain.service.AbstractFindService;
import com.develop.api_core.exception.NoContentException;
import com.develop.api_core.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public abstract class AbstractFindServiceImpl<T extends AbstractEntity> implements AbstractFindService<T> {

    private final AbstractRepository<T> repository;

    public AbstractFindServiceImpl(AbstractRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<List<T>> findAll() {
        List<T> entities = repository.findAll();
        if (entities.isEmpty()) throw new NoContentException();
        return ResponseEntity.ok(entities);
    }

    @Override
    public ResponseEntity<T> findById(Long id) {
        T entity = repository.findById(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(entity);
    }
}

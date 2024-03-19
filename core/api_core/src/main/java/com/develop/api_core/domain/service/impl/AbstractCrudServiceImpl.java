package com.develop.api_core.domain.service.impl;

import com.develop.api_core.domain.entity.AbstractEntity;
import com.develop.api_core.domain.entity.dto.AbstractDTO;
import com.develop.api_core.domain.repository.AbstractRepository;
import com.develop.api_core.domain.service.AbstractCrudService;
import com.develop.api_core.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;

@Service
@Transactional
public abstract class AbstractCrudServiceImpl<T extends AbstractEntity, D extends AbstractDTO> implements AbstractCrudService<T, D> {

    private final ModelMapper modelMapper;

    private final AbstractRepository<T> repository;

    public AbstractCrudServiceImpl(ModelMapper modelMapper, AbstractRepository<T> repository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public ResponseEntity<T> save(D dto) {
        T entity = modelMapper.map(dto, entityClass());
        entity = repository.save(entity);
        return ResponseEntity.ok(entity);
    }

    @Override
    public ResponseEntity<T> update(Long id, D dto) {
        T entity = repository.findById(id).orElseThrow(NotFoundException::new);
        modelMapper.map(dto, entity);
        entity = repository.save(entity);
        return ResponseEntity.ok(entity);
    }

    @Override
    public void delete(Long id) {
        T entity = repository.findById(id).orElseThrow(NotFoundException::new);
        repository.delete(entity);
    }

    @SuppressWarnings("unchecked")
    private Class<T> entityClass() {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
}

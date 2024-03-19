package com.develop.api_core.domain.service;

import com.develop.api_core.domain.entity.AbstractEntity;
import com.develop.api_core.domain.entity.dto.AbstractDTO;
import com.develop.api_core.domain.repository.AbstractRepository;
import com.develop.api_core.exception.NotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;

@Transactional(readOnly = true)
public abstract class AbstractCrudService<T extends AbstractEntity, D extends AbstractDTO> extends AbstractFindService<T> {

    protected final ModelMapper modelMapper;

    protected final Class<T> entityClass;

    public AbstractCrudService(AbstractRepository<T> repository) {
        super(repository);
        this.modelMapper = new ModelMapper();
        this.entityClass = entityClass();
    }

    @SuppressWarnings("null")
    public ResponseEntity<T> save(D dto) {
        T entity = modelMapper.map(dto, entityClass);
        entity = repository.save(entity);
        return ResponseEntity.ok(entity);
    }

    @SuppressWarnings("null")
    public ResponseEntity<T> update(Long id, D dto) {
        T entity = repository.findById(id).orElseThrow(NotFoundException::new);
        modelMapper.map(dto, entity);
        entity = repository.save(entity);
        return ResponseEntity.ok(entity);
    }

    @SuppressWarnings("null")
    public void delete(Long id) {
        T entity = repository.findById(id).orElseThrow(NotFoundException::new);
        repository.delete(entity);
    }
    

    @SuppressWarnings("unchecked")
    private Class<T> entityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}

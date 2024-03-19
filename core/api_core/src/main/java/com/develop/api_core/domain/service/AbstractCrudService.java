package com.develop.api_core.domain.service;

import com.develop.api_core.domain.entity.AbstractEntity;
import com.develop.api_core.domain.entity.dto.AbstractDTO;
import org.springframework.http.ResponseEntity;

public interface AbstractCrudService<T extends AbstractEntity, D extends AbstractDTO> extends AbstractFindService<T> {

    ResponseEntity<T> save(D dto);

    ResponseEntity<T> update(Long id, D dto);

    void delete(Long id);
}

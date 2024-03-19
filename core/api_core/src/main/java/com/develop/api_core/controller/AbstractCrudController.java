package com.develop.api_core.controller;

import com.develop.api_core.domain.entity.AbstractEntity;
import com.develop.api_core.domain.entity.dto.AbstractDTO;
import com.develop.api_core.domain.service.AbstractCrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class AbstractCrudController<T extends AbstractEntity, D extends AbstractDTO> extends AbstractFindController<T> {

    private final AbstractCrudService<T, D> crudService;

    public AbstractCrudController(AbstractCrudService<T, D> crudService) {
        super(crudService);
        this.crudService = crudService;
    }

    @PostMapping
    public ResponseEntity<T> save(@RequestBody D dto) {
        return crudService.save(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<T> update(@PathVariable Long id, @RequestBody D dto) {
        return crudService.update(id, dto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        crudService.delete(id);
    }
}

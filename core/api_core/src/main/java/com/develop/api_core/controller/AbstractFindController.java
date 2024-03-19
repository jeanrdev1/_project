package com.develop.api_core.controller;

import com.develop.api_core.domain.entity.AbstractEntity;
import com.develop.api_core.domain.service.AbstractFindService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public abstract class AbstractFindController<T extends AbstractEntity> extends AbstractController {

    private final AbstractFindService<T> findService;

    public AbstractFindController(AbstractFindService<T> findService) {
        this.findService = findService;
    }

    @GetMapping("{id}")
    public ResponseEntity<T> findById(@PathVariable Long id) {
        return findService.findById(id);
    }

    @GetMapping
    public ResponseEntity<List<T>> findAll() {
        return findService.findAll();
    }
}

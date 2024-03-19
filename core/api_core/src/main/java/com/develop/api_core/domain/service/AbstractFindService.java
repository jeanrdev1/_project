package com.develop.api_core.domain.service;

import com.develop.api_core.domain.entity.AbstractEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AbstractFindService<T extends AbstractEntity> extends AbstractService {

    ResponseEntity<List<T>> findAll();

    ResponseEntity<T> findById(Long id);
}

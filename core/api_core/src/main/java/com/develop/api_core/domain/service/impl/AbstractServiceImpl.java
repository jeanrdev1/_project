package com.develop.api_core.domain.service.impl;

import com.develop.api_core.domain.entity.AbstractEntity;
import com.develop.api_core.domain.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public abstract class AbstractServiceImpl<T extends AbstractEntity> implements AbstractService {
}

package com.flexsolution.kmb.kmbvsemenuk.service;

import com.flexsolution.kmb.kmbvsemenuk.constant.Constants;
import com.flexsolution.kmb.kmbvsemenuk.domain.AbstractEntity;
import com.flexsolution.kmb.kmbvsemenuk.exception.EntityNotFoundException;
import com.flexsolution.kmb.kmbvsemenuk.repository.BaseRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

import static java.lang.String.format;

@Slf4j
@Setter(onMethod = @__({@Autowired}))
@Transactional()
public abstract class AbstractService<E extends AbstractEntity, R extends BaseRepository<E>> {

    protected R repository;

    public E save(E entity) {
        log.debug("Saving entity {}", entity);

        return repository.save(entity);
    }

    public E findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format(Constants.ENTITY_NOT_FOUND_EXCEPTION_MSG, id)));
    }

    public <T> T findById(Long id, Function<E, T> mapFunction) {
        log.debug("Find entity by id {}", id);

        return repository.findById(id).map(mapFunction)
                .orElseThrow(() -> new EntityNotFoundException(format(Constants.ENTITY_NOT_FOUND_EXCEPTION_MSG, id)));
    }

    public <T> Page<T> findAll(Pageable pageable, Function<E, T> mapFunction) {
        return repository.findAll(pageable).map(mapFunction);

    }

    public void deleteById(Long id) {
        log.debug("Delete entity by id {}", id);

        repository.deleteById(repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(format(Constants.ENTITY_NOT_FOUND_EXCEPTION_MSG, id))).getId());
    }
}

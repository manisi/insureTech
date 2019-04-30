package ir.insurance.startup.service.impl;

import ir.insurance.startup.service.OnvanKhodroService;
import ir.insurance.startup.domain.OnvanKhodro;
import ir.insurance.startup.repository.OnvanKhodroRepository;
import ir.insurance.startup.service.dto.OnvanKhodroDTO;
import ir.insurance.startup.service.mapper.OnvanKhodroMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing OnvanKhodro.
 */
@Service
@Transactional
public class OnvanKhodroServiceImpl implements OnvanKhodroService {

    private final Logger log = LoggerFactory.getLogger(OnvanKhodroServiceImpl.class);

    private final OnvanKhodroRepository onvanKhodroRepository;

    private final OnvanKhodroMapper onvanKhodroMapper;

    public OnvanKhodroServiceImpl(OnvanKhodroRepository onvanKhodroRepository, OnvanKhodroMapper onvanKhodroMapper) {
        this.onvanKhodroRepository = onvanKhodroRepository;
        this.onvanKhodroMapper = onvanKhodroMapper;
    }

    /**
     * Save a onvanKhodro.
     *
     * @param onvanKhodroDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OnvanKhodroDTO save(OnvanKhodroDTO onvanKhodroDTO) {
        log.debug("Request to save OnvanKhodro : {}", onvanKhodroDTO);
        OnvanKhodro onvanKhodro = onvanKhodroMapper.toEntity(onvanKhodroDTO);
        onvanKhodro = onvanKhodroRepository.save(onvanKhodro);
        return onvanKhodroMapper.toDto(onvanKhodro);
    }

    /**
     * Get all the onvanKhodros.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OnvanKhodroDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OnvanKhodros");
        return onvanKhodroRepository.findAll(pageable)
            .map(onvanKhodroMapper::toDto);
    }


    /**
     * Get one onvanKhodro by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OnvanKhodroDTO> findOne(Long id) {
        log.debug("Request to get OnvanKhodro : {}", id);
        return onvanKhodroRepository.findById(id)
            .map(onvanKhodroMapper::toDto);
    }

    /**
     * Delete the onvanKhodro by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OnvanKhodro : {}", id);
        onvanKhodroRepository.deleteById(id);
    }
}

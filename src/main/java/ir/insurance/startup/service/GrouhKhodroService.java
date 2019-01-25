package ir.insurance.startup.service;

import ir.insurance.startup.domain.GrouhKhodro;
import ir.insurance.startup.repository.GrouhKhodroRepository;
import ir.insurance.startup.service.dto.GrouhKhodroDTO;
import ir.insurance.startup.service.mapper.GrouhKhodroMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing GrouhKhodro.
 */
@Service
@Transactional
public class GrouhKhodroService {

    private final Logger log = LoggerFactory.getLogger(GrouhKhodroService.class);

    private final GrouhKhodroRepository grouhKhodroRepository;

    private final GrouhKhodroMapper grouhKhodroMapper;

    public GrouhKhodroService(GrouhKhodroRepository grouhKhodroRepository, GrouhKhodroMapper grouhKhodroMapper) {
        this.grouhKhodroRepository = grouhKhodroRepository;
        this.grouhKhodroMapper = grouhKhodroMapper;
    }

    /**
     * Save a grouhKhodro.
     *
     * @param grouhKhodroDTO the entity to save
     * @return the persisted entity
     */
    public GrouhKhodroDTO save(GrouhKhodroDTO grouhKhodroDTO) {
        log.debug("Request to save GrouhKhodro : {}", grouhKhodroDTO);

        GrouhKhodro grouhKhodro = grouhKhodroMapper.toEntity(grouhKhodroDTO);
        grouhKhodro = grouhKhodroRepository.save(grouhKhodro);
        return grouhKhodroMapper.toDto(grouhKhodro);
    }

    /**
     * Get all the grouhKhodros.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<GrouhKhodroDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GrouhKhodros");
        return grouhKhodroRepository.findAll(pageable)
            .map(grouhKhodroMapper::toDto);
    }


    /**
     * Get one grouhKhodro by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<GrouhKhodroDTO> findOne(Long id) {
        log.debug("Request to get GrouhKhodro : {}", id);
        return grouhKhodroRepository.findById(id)
            .map(grouhKhodroMapper::toDto);
    }

    /**
     * Delete the grouhKhodro by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete GrouhKhodro : {}", id);
        grouhKhodroRepository.deleteById(id);
    }
}

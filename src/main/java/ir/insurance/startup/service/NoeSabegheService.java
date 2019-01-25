package ir.insurance.startup.service;

import ir.insurance.startup.domain.NoeSabeghe;
import ir.insurance.startup.repository.NoeSabegheRepository;
import ir.insurance.startup.service.dto.NoeSabegheDTO;
import ir.insurance.startup.service.mapper.NoeSabegheMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing NoeSabeghe.
 */
@Service
@Transactional
public class NoeSabegheService {

    private final Logger log = LoggerFactory.getLogger(NoeSabegheService.class);

    private final NoeSabegheRepository noeSabegheRepository;

    private final NoeSabegheMapper noeSabegheMapper;

    public NoeSabegheService(NoeSabegheRepository noeSabegheRepository, NoeSabegheMapper noeSabegheMapper) {
        this.noeSabegheRepository = noeSabegheRepository;
        this.noeSabegheMapper = noeSabegheMapper;
    }

    /**
     * Save a noeSabeghe.
     *
     * @param noeSabegheDTO the entity to save
     * @return the persisted entity
     */
    public NoeSabegheDTO save(NoeSabegheDTO noeSabegheDTO) {
        log.debug("Request to save NoeSabeghe : {}", noeSabegheDTO);

        NoeSabeghe noeSabeghe = noeSabegheMapper.toEntity(noeSabegheDTO);
        noeSabeghe = noeSabegheRepository.save(noeSabeghe);
        return noeSabegheMapper.toDto(noeSabeghe);
    }

    /**
     * Get all the noeSabeghes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<NoeSabegheDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NoeSabeghes");
        return noeSabegheRepository.findAll(pageable)
            .map(noeSabegheMapper::toDto);
    }


    /**
     * Get one noeSabeghe by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<NoeSabegheDTO> findOne(Long id) {
        log.debug("Request to get NoeSabeghe : {}", id);
        return noeSabegheRepository.findById(id)
            .map(noeSabegheMapper::toDto);
    }

    /**
     * Delete the noeSabeghe by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete NoeSabeghe : {}", id);
        noeSabegheRepository.deleteById(id);
    }
}

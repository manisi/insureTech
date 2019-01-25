package ir.insurance.startup.service;

import ir.insurance.startup.domain.Sabeghe;
import ir.insurance.startup.repository.SabegheRepository;
import ir.insurance.startup.service.dto.SabegheDTO;
import ir.insurance.startup.service.mapper.SabegheMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Sabeghe.
 */
@Service
@Transactional
public class SabegheService {

    private final Logger log = LoggerFactory.getLogger(SabegheService.class);

    private final SabegheRepository sabegheRepository;

    private final SabegheMapper sabegheMapper;

    public SabegheService(SabegheRepository sabegheRepository, SabegheMapper sabegheMapper) {
        this.sabegheRepository = sabegheRepository;
        this.sabegheMapper = sabegheMapper;
    }

    /**
     * Save a sabeghe.
     *
     * @param sabegheDTO the entity to save
     * @return the persisted entity
     */
    public SabegheDTO save(SabegheDTO sabegheDTO) {
        log.debug("Request to save Sabeghe : {}", sabegheDTO);

        Sabeghe sabeghe = sabegheMapper.toEntity(sabegheDTO);
        sabeghe = sabegheRepository.save(sabeghe);
        return sabegheMapper.toDto(sabeghe);
    }

    /**
     * Get all the sabeghes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<SabegheDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Sabeghes");
        return sabegheRepository.findAll(pageable)
            .map(sabegheMapper::toDto);
    }


    /**
     * Get one sabeghe by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<SabegheDTO> findOne(Long id) {
        log.debug("Request to get Sabeghe : {}", id);
        return sabegheRepository.findById(id)
            .map(sabegheMapper::toDto);
    }

    /**
     * Delete the sabeghe by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Sabeghe : {}", id);
        sabegheRepository.deleteById(id);
    }
}

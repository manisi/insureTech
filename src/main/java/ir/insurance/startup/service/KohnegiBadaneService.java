package ir.insurance.startup.service;

import ir.insurance.startup.domain.KohnegiBadane;
import ir.insurance.startup.repository.KohnegiBadaneRepository;
import ir.insurance.startup.service.dto.KohnegiBadaneDTO;
import ir.insurance.startup.service.mapper.KohnegiBadaneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing KohnegiBadane.
 */
@Service
@Transactional
public class KohnegiBadaneService {

    private final Logger log = LoggerFactory.getLogger(KohnegiBadaneService.class);

    private final KohnegiBadaneRepository kohnegiBadaneRepository;

    private final KohnegiBadaneMapper kohnegiBadaneMapper;

    public KohnegiBadaneService(KohnegiBadaneRepository kohnegiBadaneRepository, KohnegiBadaneMapper kohnegiBadaneMapper) {
        this.kohnegiBadaneRepository = kohnegiBadaneRepository;
        this.kohnegiBadaneMapper = kohnegiBadaneMapper;
    }

    /**
     * Save a kohnegiBadane.
     *
     * @param kohnegiBadaneDTO the entity to save
     * @return the persisted entity
     */
    public KohnegiBadaneDTO save(KohnegiBadaneDTO kohnegiBadaneDTO) {
        log.debug("Request to save KohnegiBadane : {}", kohnegiBadaneDTO);
        KohnegiBadane kohnegiBadane = kohnegiBadaneMapper.toEntity(kohnegiBadaneDTO);
        kohnegiBadane = kohnegiBadaneRepository.save(kohnegiBadane);
        return kohnegiBadaneMapper.toDto(kohnegiBadane);
    }

    /**
     * Get all the kohnegiBadanes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<KohnegiBadaneDTO> findAll(Pageable pageable) {
        log.debug("Request to get all KohnegiBadanes");
        return kohnegiBadaneRepository.findAll(pageable)
            .map(kohnegiBadaneMapper::toDto);
    }


    /**
     * Get one kohnegiBadane by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<KohnegiBadaneDTO> findOne(Long id) {
        log.debug("Request to get KohnegiBadane : {}", id);
        return kohnegiBadaneRepository.findById(id)
            .map(kohnegiBadaneMapper::toDto);
    }

    /**
     * Delete the kohnegiBadane by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete KohnegiBadane : {}", id);        kohnegiBadaneRepository.deleteById(id);
    }
}

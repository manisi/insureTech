package ir.insurance.startup.service;

import ir.insurance.startup.domain.JarimeDirkard;
import ir.insurance.startup.repository.JarimeDirkardRepository;
import ir.insurance.startup.service.dto.JarimeDirkardDTO;
import ir.insurance.startup.service.mapper.JarimeDirkardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing JarimeDirkard.
 */
@Service
@Transactional
public class JarimeDirkardService {

    private final Logger log = LoggerFactory.getLogger(JarimeDirkardService.class);

    private final JarimeDirkardRepository jarimeDirkardRepository;

    private final JarimeDirkardMapper jarimeDirkardMapper;

    public JarimeDirkardService(JarimeDirkardRepository jarimeDirkardRepository, JarimeDirkardMapper jarimeDirkardMapper) {
        this.jarimeDirkardRepository = jarimeDirkardRepository;
        this.jarimeDirkardMapper = jarimeDirkardMapper;
    }

    /**
     * Save a jarimeDirkard.
     *
     * @param jarimeDirkardDTO the entity to save
     * @return the persisted entity
     */
    public JarimeDirkardDTO save(JarimeDirkardDTO jarimeDirkardDTO) {
        log.debug("Request to save JarimeDirkard : {}", jarimeDirkardDTO);
        JarimeDirkard jarimeDirkard = jarimeDirkardMapper.toEntity(jarimeDirkardDTO);
        jarimeDirkard = jarimeDirkardRepository.save(jarimeDirkard);
        return jarimeDirkardMapper.toDto(jarimeDirkard);
    }

    /**
     * Get all the jarimeDirkards.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<JarimeDirkardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all JarimeDirkards");
        return jarimeDirkardRepository.findAll(pageable)
            .map(jarimeDirkardMapper::toDto);
    }


    /**
     * Get one jarimeDirkard by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<JarimeDirkardDTO> findOne(Long id) {
        log.debug("Request to get JarimeDirkard : {}", id);
        return jarimeDirkardRepository.findById(id)
            .map(jarimeDirkardMapper::toDto);
    }

    /**
     * Delete the jarimeDirkard by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete JarimeDirkard : {}", id);        jarimeDirkardRepository.deleteById(id);
    }
}

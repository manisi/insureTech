package ir.insurance.startup.service;

import ir.insurance.startup.domain.AnvaeKhodro;
import ir.insurance.startup.repository.AnvaeKhodroRepository;
import ir.insurance.startup.service.dto.AnvaeKhodroDTO;
import ir.insurance.startup.service.mapper.AnvaeKhodroMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing AnvaeKhodro.
 */
@Service
@Transactional
public class AnvaeKhodroService {

    private final Logger log = LoggerFactory.getLogger(AnvaeKhodroService.class);

    private final AnvaeKhodroRepository anvaeKhodroRepository;

    private final AnvaeKhodroMapper anvaeKhodroMapper;

    public AnvaeKhodroService(AnvaeKhodroRepository anvaeKhodroRepository, AnvaeKhodroMapper anvaeKhodroMapper) {
        this.anvaeKhodroRepository = anvaeKhodroRepository;
        this.anvaeKhodroMapper = anvaeKhodroMapper;
    }

    /**
     * Save a anvaeKhodro.
     *
     * @param anvaeKhodroDTO the entity to save
     * @return the persisted entity
     */
    public AnvaeKhodroDTO save(AnvaeKhodroDTO anvaeKhodroDTO) {
        log.debug("Request to save AnvaeKhodro : {}", anvaeKhodroDTO);
        AnvaeKhodro anvaeKhodro = anvaeKhodroMapper.toEntity(anvaeKhodroDTO);
        anvaeKhodro = anvaeKhodroRepository.save(anvaeKhodro);
        return anvaeKhodroMapper.toDto(anvaeKhodro);
    }

    /**
     * Get all the anvaeKhodros.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AnvaeKhodroDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AnvaeKhodros");
        return anvaeKhodroRepository.findAll(pageable)
            .map(anvaeKhodroMapper::toDto);
    }


    /**
     * Get one anvaeKhodro by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AnvaeKhodroDTO> findOne(Long id) {
        log.debug("Request to get AnvaeKhodro : {}", id);
        return anvaeKhodroRepository.findById(id)
            .map(anvaeKhodroMapper::toDto);
    }

    /**
     * Delete the anvaeKhodro by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AnvaeKhodro : {}", id);        anvaeKhodroRepository.deleteById(id);
    }
}

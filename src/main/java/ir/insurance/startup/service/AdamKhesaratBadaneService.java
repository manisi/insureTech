package ir.insurance.startup.service;

import ir.insurance.startup.domain.AdamKhesaratBadane;
import ir.insurance.startup.repository.AdamKhesaratBadaneRepository;
import ir.insurance.startup.service.dto.AdamKhesaratBadaneDTO;
import ir.insurance.startup.service.mapper.AdamKhesaratBadaneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing AdamKhesaratBadane.
 */
@Service
@Transactional
public class AdamKhesaratBadaneService {

    private final Logger log = LoggerFactory.getLogger(AdamKhesaratBadaneService.class);

    private final AdamKhesaratBadaneRepository adamKhesaratBadaneRepository;

    private final AdamKhesaratBadaneMapper adamKhesaratBadaneMapper;

    public AdamKhesaratBadaneService(AdamKhesaratBadaneRepository adamKhesaratBadaneRepository, AdamKhesaratBadaneMapper adamKhesaratBadaneMapper) {
        this.adamKhesaratBadaneRepository = adamKhesaratBadaneRepository;
        this.adamKhesaratBadaneMapper = adamKhesaratBadaneMapper;
    }

    /**
     * Save a adamKhesaratBadane.
     *
     * @param adamKhesaratBadaneDTO the entity to save
     * @return the persisted entity
     */
    public AdamKhesaratBadaneDTO save(AdamKhesaratBadaneDTO adamKhesaratBadaneDTO) {
        log.debug("Request to save AdamKhesaratBadane : {}", adamKhesaratBadaneDTO);
        AdamKhesaratBadane adamKhesaratBadane = adamKhesaratBadaneMapper.toEntity(adamKhesaratBadaneDTO);
        adamKhesaratBadane = adamKhesaratBadaneRepository.save(adamKhesaratBadane);
        return adamKhesaratBadaneMapper.toDto(adamKhesaratBadane);
    }

    /**
     * Get all the adamKhesaratBadanes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AdamKhesaratBadaneDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AdamKhesaratBadanes");
        return adamKhesaratBadaneRepository.findAll(pageable)
            .map(adamKhesaratBadaneMapper::toDto);
    }


    /**
     * Get one adamKhesaratBadane by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AdamKhesaratBadaneDTO> findOne(Long id) {
        log.debug("Request to get AdamKhesaratBadane : {}", id);
        return adamKhesaratBadaneRepository.findById(id)
            .map(adamKhesaratBadaneMapper::toDto);
    }

    /**
     * Delete the adamKhesaratBadane by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AdamKhesaratBadane : {}", id);        adamKhesaratBadaneRepository.deleteById(id);
    }
}

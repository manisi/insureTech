package ir.insurance.startup.service;

import ir.insurance.startup.domain.AdamKhesarat;
import ir.insurance.startup.repository.AdamKhesaratRepository;
import ir.insurance.startup.service.dto.AdamKhesaratDTO;
import ir.insurance.startup.service.mapper.AdamKhesaratMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing AdamKhesarat.
 */
@Service
@Transactional
public class AdamKhesaratService {

    private final Logger log = LoggerFactory.getLogger(AdamKhesaratService.class);

    private final AdamKhesaratRepository adamKhesaratRepository;

    private final AdamKhesaratMapper adamKhesaratMapper;

    public AdamKhesaratService(AdamKhesaratRepository adamKhesaratRepository, AdamKhesaratMapper adamKhesaratMapper) {
        this.adamKhesaratRepository = adamKhesaratRepository;
        this.adamKhesaratMapper = adamKhesaratMapper;
    }

    /**
     * Save a adamKhesarat.
     *
     * @param adamKhesaratDTO the entity to save
     * @return the persisted entity
     */
    public AdamKhesaratDTO save(AdamKhesaratDTO adamKhesaratDTO) {
        log.debug("Request to save AdamKhesarat : {}", adamKhesaratDTO);
        AdamKhesarat adamKhesarat = adamKhesaratMapper.toEntity(adamKhesaratDTO);
        adamKhesarat = adamKhesaratRepository.save(adamKhesarat);
        return adamKhesaratMapper.toDto(adamKhesarat);
    }

    /**
     * Get all the adamKhesarats.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AdamKhesaratDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AdamKhesarats");
        return adamKhesaratRepository.findAll(pageable)
            .map(adamKhesaratMapper::toDto);
    }


    /**
     * Get one adamKhesarat by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AdamKhesaratDTO> findOne(Long id) {
        log.debug("Request to get AdamKhesarat : {}", id);
        return adamKhesaratRepository.findById(id)
            .map(adamKhesaratMapper::toDto);
    }

    /**
     * Delete the adamKhesarat by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AdamKhesarat : {}", id);
        adamKhesaratRepository.deleteById(id);
    }

    public List<AdamKhesarat> findAllforlookup() {
        return  adamKhesaratRepository.findAll();
    }
}

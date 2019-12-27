package ir.insurance.startup.service;

import ir.insurance.startup.domain.AdamKhesaratSarneshin;
import ir.insurance.startup.repository.AdamKhesaratSarneshinRepository;
import ir.insurance.startup.service.dto.AdamKhesaratSarneshinDTO;
import ir.insurance.startup.service.mapper.AdamKhesaratSarneshinMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing AdamKhesaratSarneshin.
 */
@Service
@Transactional
public class AdamKhesaratSarneshinService {

    private final Logger log = LoggerFactory.getLogger(AdamKhesaratSarneshinService.class);

    private final AdamKhesaratSarneshinRepository adamKhesaratSarneshinRepository;

    private final AdamKhesaratSarneshinMapper adamKhesaratSarneshinMapper;

    public AdamKhesaratSarneshinService(AdamKhesaratSarneshinRepository adamKhesaratSarneshinRepository, AdamKhesaratSarneshinMapper adamKhesaratSarneshinMapper) {
        this.adamKhesaratSarneshinRepository = adamKhesaratSarneshinRepository;
        this.adamKhesaratSarneshinMapper = adamKhesaratSarneshinMapper;
    }

    /**
     * Save a adamKhesaratSarneshin.
     *
     * @param adamKhesaratSarneshinDTO the entity to save
     * @return the persisted entity
     */
    public AdamKhesaratSarneshinDTO save(AdamKhesaratSarneshinDTO adamKhesaratSarneshinDTO) {
        log.debug("Request to save AdamKhesaratSarneshin : {}", adamKhesaratSarneshinDTO);
        AdamKhesaratSarneshin adamKhesaratSarneshin = adamKhesaratSarneshinMapper.toEntity(adamKhesaratSarneshinDTO);
        adamKhesaratSarneshin = adamKhesaratSarneshinRepository.save(adamKhesaratSarneshin);
        return adamKhesaratSarneshinMapper.toDto(adamKhesaratSarneshin);
    }

    /**
     * Get all the adamKhesaratSarneshins.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AdamKhesaratSarneshinDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AdamKhesaratSarneshins");
        return adamKhesaratSarneshinRepository.findAll(pageable)
            .map(adamKhesaratSarneshinMapper::toDto);
    }


    /**
     * Get one adamKhesaratSarneshin by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AdamKhesaratSarneshinDTO> findOne(Long id) {
        log.debug("Request to get AdamKhesaratSarneshin : {}", id);
        return adamKhesaratSarneshinRepository.findById(id)
            .map(adamKhesaratSarneshinMapper::toDto);
    }

    /**
     * Delete the adamKhesaratSarneshin by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AdamKhesaratSarneshin : {}", id);        adamKhesaratSarneshinRepository.deleteById(id);
    }

    public List<AdamKhesaratSarneshin> findAllforlookup() {
        return  adamKhesaratSarneshinRepository.findAll();
    }
}

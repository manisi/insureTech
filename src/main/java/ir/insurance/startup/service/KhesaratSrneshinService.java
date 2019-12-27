package ir.insurance.startup.service;

import ir.insurance.startup.domain.KhesaratSrneshin;
import ir.insurance.startup.repository.KhesaratSrneshinRepository;
import ir.insurance.startup.service.dto.KhesaratSrneshinDTO;
import ir.insurance.startup.service.mapper.KhesaratSrneshinMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing KhesaratSrneshin.
 */
@Service
@Transactional
public class KhesaratSrneshinService {

    private final Logger log = LoggerFactory.getLogger(KhesaratSrneshinService.class);

    private final KhesaratSrneshinRepository khesaratSrneshinRepository;

    private final KhesaratSrneshinMapper khesaratSrneshinMapper;

    public KhesaratSrneshinService(KhesaratSrneshinRepository khesaratSrneshinRepository, KhesaratSrneshinMapper khesaratSrneshinMapper) {
        this.khesaratSrneshinRepository = khesaratSrneshinRepository;
        this.khesaratSrneshinMapper = khesaratSrneshinMapper;
    }

    /**
     * Save a khesaratSrneshin.
     *
     * @param khesaratSrneshinDTO the entity to save
     * @return the persisted entity
     */
    public KhesaratSrneshinDTO save(KhesaratSrneshinDTO khesaratSrneshinDTO) {
        log.debug("Request to save KhesaratSrneshin : {}", khesaratSrneshinDTO);
        KhesaratSrneshin khesaratSrneshin = khesaratSrneshinMapper.toEntity(khesaratSrneshinDTO);
        khesaratSrneshin = khesaratSrneshinRepository.save(khesaratSrneshin);
        return khesaratSrneshinMapper.toDto(khesaratSrneshin);
    }

    /**
     * Get all the khesaratSrneshins.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<KhesaratSrneshinDTO> findAll(Pageable pageable) {
        log.debug("Request to get all KhesaratSrneshins");
        return khesaratSrneshinRepository.findAll(pageable)
            .map(khesaratSrneshinMapper::toDto);
    }


    /**
     * Get one khesaratSrneshin by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<KhesaratSrneshinDTO> findOne(Long id) {
        log.debug("Request to get KhesaratSrneshin : {}", id);
        return khesaratSrneshinRepository.findById(id)
            .map(khesaratSrneshinMapper::toDto);
    }

    /**
     * Delete the khesaratSrneshin by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete KhesaratSrneshin : {}", id);
        khesaratSrneshinRepository.deleteById(id);
    }

    public List<KhesaratSrneshin> findAllforlookup() {
        return khesaratSrneshinRepository.findAll();
    }
}

package ir.insurance.startup.service;

import ir.insurance.startup.domain.KhesaratSales;
import ir.insurance.startup.repository.KhesaratSalesRepository;
import ir.insurance.startup.service.dto.KhesaratSalesDTO;
import ir.insurance.startup.service.mapper.KhesaratSalesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing KhesaratSales.
 */
@Service
@Transactional
public class KhesaratSalesService {

    private final Logger log = LoggerFactory.getLogger(KhesaratSalesService.class);

    private final KhesaratSalesRepository khesaratSalesRepository;

    private final KhesaratSalesMapper khesaratSalesMapper;

    public KhesaratSalesService(KhesaratSalesRepository khesaratSalesRepository, KhesaratSalesMapper khesaratSalesMapper) {
        this.khesaratSalesRepository = khesaratSalesRepository;
        this.khesaratSalesMapper = khesaratSalesMapper;
    }

    /**
     * Save a khesaratSales.
     *
     * @param khesaratSalesDTO the entity to save
     * @return the persisted entity
     */
    public KhesaratSalesDTO save(KhesaratSalesDTO khesaratSalesDTO) {
        log.debug("Request to save KhesaratSales : {}", khesaratSalesDTO);

        KhesaratSales khesaratSales = khesaratSalesMapper.toEntity(khesaratSalesDTO);
        khesaratSales = khesaratSalesRepository.save(khesaratSales);
        return khesaratSalesMapper.toDto(khesaratSales);
    }

    /**
     * Get all the khesaratSales.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<KhesaratSalesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all KhesaratSales");
        return khesaratSalesRepository.findAll(pageable)
            .map(khesaratSalesMapper::toDto);
    }


    /**
     * Get one khesaratSales by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<KhesaratSalesDTO> findOne(Long id) {
        log.debug("Request to get KhesaratSales : {}", id);
        return khesaratSalesRepository.findById(id)
            .map(khesaratSalesMapper::toDto);
    }

    /**
     * Delete the khesaratSales by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete KhesaratSales : {}", id);
        khesaratSalesRepository.deleteById(id);
    }
}

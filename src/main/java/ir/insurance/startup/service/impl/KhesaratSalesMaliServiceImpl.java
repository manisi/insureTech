package ir.insurance.startup.service.impl;

import ir.insurance.startup.service.KhesaratSalesMaliService;
import ir.insurance.startup.domain.KhesaratSalesMali;
import ir.insurance.startup.repository.KhesaratSalesMaliRepository;
import ir.insurance.startup.service.dto.KhesaratSalesMaliDTO;
import ir.insurance.startup.service.mapper.KhesaratSalesMaliMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing KhesaratSalesMali.
 */
@Service
@Transactional
public class KhesaratSalesMaliServiceImpl implements KhesaratSalesMaliService {

    private final Logger log = LoggerFactory.getLogger(KhesaratSalesMaliServiceImpl.class);

    private final KhesaratSalesMaliRepository khesaratSalesMaliRepository;

    private final KhesaratSalesMaliMapper khesaratSalesMaliMapper;

    public KhesaratSalesMaliServiceImpl(KhesaratSalesMaliRepository khesaratSalesMaliRepository, KhesaratSalesMaliMapper khesaratSalesMaliMapper) {
        this.khesaratSalesMaliRepository = khesaratSalesMaliRepository;
        this.khesaratSalesMaliMapper = khesaratSalesMaliMapper;
    }

    /**
     * Save a khesaratSalesMali.
     *
     * @param khesaratSalesMaliDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public KhesaratSalesMaliDTO save(KhesaratSalesMaliDTO khesaratSalesMaliDTO) {
        log.debug("Request to save KhesaratSalesMali : {}", khesaratSalesMaliDTO);
        KhesaratSalesMali khesaratSalesMali = khesaratSalesMaliMapper.toEntity(khesaratSalesMaliDTO);
        khesaratSalesMali = khesaratSalesMaliRepository.save(khesaratSalesMali);
        return khesaratSalesMaliMapper.toDto(khesaratSalesMali);
    }

    /**
     * Get all the khesaratSalesMalis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<KhesaratSalesMaliDTO> findAll(Pageable pageable) {
        log.debug("Request to get all KhesaratSalesMalis");
        return khesaratSalesMaliRepository.findAll(pageable)
            .map(khesaratSalesMaliMapper::toDto);
    }


    /**
     * Get one khesaratSalesMali by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<KhesaratSalesMaliDTO> findOne(Long id) {
        log.debug("Request to get KhesaratSalesMali : {}", id);
        return khesaratSalesMaliRepository.findById(id)
            .map(khesaratSalesMaliMapper::toDto);
    }

    /**
     * Delete the khesaratSalesMali by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete KhesaratSalesMali : {}", id);
        khesaratSalesMaliRepository.deleteById(id);
    }

    @Override
    public List<KhesaratSalesMali> findAllforlookup() {
        return khesaratSalesMaliRepository.findAll();
    }
}

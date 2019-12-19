package ir.insurance.startup.service.impl;

import ir.insurance.startup.service.AdamKhesaratSalesMaliService;
import ir.insurance.startup.domain.AdamKhesaratSalesMali;
import ir.insurance.startup.repository.AdamKhesaratSalesMaliRepository;
import ir.insurance.startup.service.dto.AdamKhesaratSalesMaliDTO;
import ir.insurance.startup.service.mapper.AdamKhesaratSalesMaliMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing AdamKhesaratSalesMali.
 */
@Service
@Transactional
public class AdamKhesaratSalesMaliServiceImpl implements AdamKhesaratSalesMaliService {

    private final Logger log = LoggerFactory.getLogger(AdamKhesaratSalesMaliServiceImpl.class);

    private final AdamKhesaratSalesMaliRepository adamKhesaratSalesMaliRepository;

    private final AdamKhesaratSalesMaliMapper adamKhesaratSalesMaliMapper;

    public AdamKhesaratSalesMaliServiceImpl(AdamKhesaratSalesMaliRepository adamKhesaratSalesMaliRepository, AdamKhesaratSalesMaliMapper adamKhesaratSalesMaliMapper) {
        this.adamKhesaratSalesMaliRepository = adamKhesaratSalesMaliRepository;
        this.adamKhesaratSalesMaliMapper = adamKhesaratSalesMaliMapper;
    }

    /**
     * Save a adamKhesaratSalesMali.
     *
     * @param adamKhesaratSalesMaliDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AdamKhesaratSalesMaliDTO save(AdamKhesaratSalesMaliDTO adamKhesaratSalesMaliDTO) {
        log.debug("Request to save AdamKhesaratSalesMali : {}", adamKhesaratSalesMaliDTO);
        AdamKhesaratSalesMali adamKhesaratSalesMali = adamKhesaratSalesMaliMapper.toEntity(adamKhesaratSalesMaliDTO);
        adamKhesaratSalesMali = adamKhesaratSalesMaliRepository.save(adamKhesaratSalesMali);
        return adamKhesaratSalesMaliMapper.toDto(adamKhesaratSalesMali);
    }

    /**
     * Get all the adamKhesaratSalesMalis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AdamKhesaratSalesMaliDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AdamKhesaratSalesMalis");
        return adamKhesaratSalesMaliRepository.findAll(pageable)
            .map(adamKhesaratSalesMaliMapper::toDto);
    }


    /**
     * Get one adamKhesaratSalesMali by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AdamKhesaratSalesMaliDTO> findOne(Long id) {
        log.debug("Request to get AdamKhesaratSalesMali : {}", id);
        return adamKhesaratSalesMaliRepository.findById(id)
            .map(adamKhesaratSalesMaliMapper::toDto);
    }

    /**
     * Delete the adamKhesaratSalesMali by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AdamKhesaratSalesMali : {}", id);
        adamKhesaratSalesMaliRepository.deleteById(id);
    }
}

package ir.insurance.startup.service.impl;

import ir.insurance.startup.service.EstelaamSalesNerkhService;
import ir.insurance.startup.domain.EstelaamSalesNerkh;
import ir.insurance.startup.repository.EstelaamSalesNerkhRepository;
import ir.insurance.startup.service.dto.EstelaamSalesNerkhDTO;
import ir.insurance.startup.service.mapper.EstelaamSalesNerkhMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EstelaamSalesNerkh}.
 */
@Service
@Transactional
public class EstelaamSalesNerkhServiceImpl implements EstelaamSalesNerkhService {

    private final Logger log = LoggerFactory.getLogger(EstelaamSalesNerkhServiceImpl.class);

    private final EstelaamSalesNerkhRepository estelaamSalesNerkhRepository;

    private final EstelaamSalesNerkhMapper estelaamSalesNerkhMapper;

    public EstelaamSalesNerkhServiceImpl(EstelaamSalesNerkhRepository estelaamSalesNerkhRepository, EstelaamSalesNerkhMapper estelaamSalesNerkhMapper) {
        this.estelaamSalesNerkhRepository = estelaamSalesNerkhRepository;
        this.estelaamSalesNerkhMapper = estelaamSalesNerkhMapper;
    }

    /**
     * Save a estelaamSalesNerkh.
     *
     * @param estelaamSalesNerkhDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EstelaamSalesNerkhDTO save(EstelaamSalesNerkhDTO estelaamSalesNerkhDTO) {
        log.debug("Request to save EstelaamSalesNerkh : {}", estelaamSalesNerkhDTO);
        EstelaamSalesNerkh estelaamSalesNerkh = estelaamSalesNerkhMapper.toEntity(estelaamSalesNerkhDTO);
        estelaamSalesNerkh = estelaamSalesNerkhRepository.save(estelaamSalesNerkh);
        return estelaamSalesNerkhMapper.toDto(estelaamSalesNerkh);
    }

    /**
     * Get all the estelaamSalesNerkhs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EstelaamSalesNerkhDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EstelaamSalesNerkhs");
        return estelaamSalesNerkhRepository.findAll(pageable)
            .map(estelaamSalesNerkhMapper::toDto);
    }


    /**
     * Get one estelaamSalesNerkh by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EstelaamSalesNerkhDTO> findOne(Long id) {
        log.debug("Request to get EstelaamSalesNerkh : {}", id);
        return estelaamSalesNerkhRepository.findById(id)
            .map(estelaamSalesNerkhMapper::toDto);
    }

    /**
     * Delete the estelaamSalesNerkh by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EstelaamSalesNerkh : {}", id);
        estelaamSalesNerkhRepository.deleteById(id);
    }
}

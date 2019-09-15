package ir.insurance.startup.service.impl;

import ir.insurance.startup.service.GroupOperationsDataService;
import ir.insurance.startup.domain.GroupOperationsData;
import ir.insurance.startup.repository.GroupOperationsDataRepository;
import ir.insurance.startup.service.dto.GroupOperationsDataDTO;
import ir.insurance.startup.service.mapper.GroupOperationsDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing GroupOperationsData.
 */
@Service
@Transactional
public class GroupOperationsDataServiceImpl implements GroupOperationsDataService {

    private final Logger log = LoggerFactory.getLogger(GroupOperationsDataServiceImpl.class);

    private final GroupOperationsDataRepository groupOperationsDataRepository;

    private final GroupOperationsDataMapper groupOperationsDataMapper;

    public GroupOperationsDataServiceImpl(GroupOperationsDataRepository groupOperationsDataRepository, GroupOperationsDataMapper groupOperationsDataMapper) {
        this.groupOperationsDataRepository = groupOperationsDataRepository;
        this.groupOperationsDataMapper = groupOperationsDataMapper;
    }

    /**
     * Save a groupOperationsData.
     *
     * @param groupOperationsDataDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GroupOperationsDataDTO save(GroupOperationsDataDTO groupOperationsDataDTO) {
        log.debug("Request to save GroupOperationsData : {}", groupOperationsDataDTO);
        GroupOperationsData groupOperationsData = groupOperationsDataMapper.toEntity(groupOperationsDataDTO);
        groupOperationsData = groupOperationsDataRepository.save(groupOperationsData);
        return groupOperationsDataMapper.toDto(groupOperationsData);
    }

    /**
     * Get all the groupOperationsData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GroupOperationsDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GroupOperationsData");
        return groupOperationsDataRepository.findAll(pageable)
            .map(groupOperationsDataMapper::toDto);
    }


    /**
     * Get one groupOperationsData by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GroupOperationsDataDTO> findOne(Long id) {
        log.debug("Request to get GroupOperationsData : {}", id);
        return groupOperationsDataRepository.findById(id)
            .map(groupOperationsDataMapper::toDto);
    }

    /**
     * Delete the groupOperationsData by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GroupOperationsData : {}", id);
        groupOperationsDataRepository.deleteById(id);
    }
}

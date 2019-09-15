package ir.insurance.startup.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import ir.insurance.startup.domain.GroupOperationsData;
import ir.insurance.startup.domain.*; // for static metamodels
import ir.insurance.startup.repository.GroupOperationsDataRepository;
import ir.insurance.startup.service.dto.GroupOperationsDataCriteria;
import ir.insurance.startup.service.dto.GroupOperationsDataDTO;
import ir.insurance.startup.service.mapper.GroupOperationsDataMapper;

/**
 * Service for executing complex queries for GroupOperationsData entities in the database.
 * The main input is a {@link GroupOperationsDataCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link GroupOperationsDataDTO} or a {@link Page} of {@link GroupOperationsDataDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class GroupOperationsDataQueryService extends QueryService<GroupOperationsData> {

    private final Logger log = LoggerFactory.getLogger(GroupOperationsDataQueryService.class);

    private final GroupOperationsDataRepository groupOperationsDataRepository;

    private final GroupOperationsDataMapper groupOperationsDataMapper;

    public GroupOperationsDataQueryService(GroupOperationsDataRepository groupOperationsDataRepository, GroupOperationsDataMapper groupOperationsDataMapper) {
        this.groupOperationsDataRepository = groupOperationsDataRepository;
        this.groupOperationsDataMapper = groupOperationsDataMapper;
    }

    /**
     * Return a {@link List} of {@link GroupOperationsDataDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<GroupOperationsDataDTO> findByCriteria(GroupOperationsDataCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<GroupOperationsData> specification = createSpecification(criteria);
        return groupOperationsDataMapper.toDto(groupOperationsDataRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link GroupOperationsDataDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<GroupOperationsDataDTO> findByCriteria(GroupOperationsDataCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<GroupOperationsData> specification = createSpecification(criteria);
        return groupOperationsDataRepository.findAll(specification, page)
            .map(groupOperationsDataMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(GroupOperationsDataCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<GroupOperationsData> specification = createSpecification(criteria);
        return groupOperationsDataRepository.count(specification);
    }

    /**
     * Function to convert GroupOperationsDataCriteria to a {@link Specification}
     */
    private Specification<GroupOperationsData> createSpecification(GroupOperationsDataCriteria criteria) {
        Specification<GroupOperationsData> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), GroupOperationsData_.id));
            }
            if (criteria.getEntityName() != null) {
                specification = specification.and(buildSpecification(criteria.getEntityName(), GroupOperationsData_.entityName));
            }
            if (criteria.getActing() != null) {
                specification = specification.and(buildSpecification(criteria.getActing(), GroupOperationsData_.acting));
            }
            if (criteria.getEstate() != null) {
                specification = specification.and(buildSpecification(criteria.getEstate(), GroupOperationsData_.estate));
            }
        }
        return specification;
    }
}

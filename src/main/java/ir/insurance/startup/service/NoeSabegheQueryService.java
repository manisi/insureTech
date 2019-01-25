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

import ir.insurance.startup.domain.NoeSabeghe;
import ir.insurance.startup.domain.*; // for static metamodels
import ir.insurance.startup.repository.NoeSabegheRepository;
import ir.insurance.startup.service.dto.NoeSabegheCriteria;
import ir.insurance.startup.service.dto.NoeSabegheDTO;
import ir.insurance.startup.service.mapper.NoeSabegheMapper;

/**
 * Service for executing complex queries for NoeSabeghe entities in the database.
 * The main input is a {@link NoeSabegheCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NoeSabegheDTO} or a {@link Page} of {@link NoeSabegheDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NoeSabegheQueryService extends QueryService<NoeSabeghe> {

    private final Logger log = LoggerFactory.getLogger(NoeSabegheQueryService.class);

    private final NoeSabegheRepository noeSabegheRepository;

    private final NoeSabegheMapper noeSabegheMapper;

    public NoeSabegheQueryService(NoeSabegheRepository noeSabegheRepository, NoeSabegheMapper noeSabegheMapper) {
        this.noeSabegheRepository = noeSabegheRepository;
        this.noeSabegheMapper = noeSabegheMapper;
    }

    /**
     * Return a {@link List} of {@link NoeSabegheDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NoeSabegheDTO> findByCriteria(NoeSabegheCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NoeSabeghe> specification = createSpecification(criteria);
        return noeSabegheMapper.toDto(noeSabegheRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NoeSabegheDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NoeSabegheDTO> findByCriteria(NoeSabegheCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NoeSabeghe> specification = createSpecification(criteria);
        return noeSabegheRepository.findAll(specification, page)
            .map(noeSabegheMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NoeSabegheCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<NoeSabeghe> specification = createSpecification(criteria);
        return noeSabegheRepository.count(specification);
    }

    /**
     * Function to convert NoeSabegheCriteria to a {@link Specification}
     */
    private Specification<NoeSabeghe> createSpecification(NoeSabegheCriteria criteria) {
        Specification<NoeSabeghe> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), NoeSabeghe_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), NoeSabeghe_.name));
            }
            if (criteria.getSharh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSharh(), NoeSabeghe_.sharh));
            }
            if (criteria.getFaal() != null) {
                specification = specification.and(buildSpecification(criteria.getFaal(), NoeSabeghe_.faal));
            }
        }
        return specification;
    }
}

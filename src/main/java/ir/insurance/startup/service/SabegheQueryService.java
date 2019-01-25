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

import ir.insurance.startup.domain.Sabeghe;
import ir.insurance.startup.domain.*; // for static metamodels
import ir.insurance.startup.repository.SabegheRepository;
import ir.insurance.startup.service.dto.SabegheCriteria;
import ir.insurance.startup.service.dto.SabegheDTO;
import ir.insurance.startup.service.mapper.SabegheMapper;

/**
 * Service for executing complex queries for Sabeghe entities in the database.
 * The main input is a {@link SabegheCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SabegheDTO} or a {@link Page} of {@link SabegheDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SabegheQueryService extends QueryService<Sabeghe> {

    private final Logger log = LoggerFactory.getLogger(SabegheQueryService.class);

    private final SabegheRepository sabegheRepository;

    private final SabegheMapper sabegheMapper;

    public SabegheQueryService(SabegheRepository sabegheRepository, SabegheMapper sabegheMapper) {
        this.sabegheRepository = sabegheRepository;
        this.sabegheMapper = sabegheMapper;
    }

    /**
     * Return a {@link List} of {@link SabegheDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SabegheDTO> findByCriteria(SabegheCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Sabeghe> specification = createSpecification(criteria);
        return sabegheMapper.toDto(sabegheRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SabegheDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SabegheDTO> findByCriteria(SabegheCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Sabeghe> specification = createSpecification(criteria);
        return sabegheRepository.findAll(specification, page)
            .map(sabegheMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SabegheCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Sabeghe> specification = createSpecification(criteria);
        return sabegheRepository.count(specification);
    }

    /**
     * Function to convert SabegheCriteria to a {@link Specification}
     */
    private Specification<Sabeghe> createSpecification(SabegheCriteria criteria) {
        Specification<Sabeghe> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Sabeghe_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Sabeghe_.name));
            }
            if (criteria.getSharh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSharh(), Sabeghe_.sharh));
            }
            if (criteria.getFaal() != null) {
                specification = specification.and(buildSpecification(criteria.getFaal(), Sabeghe_.faal));
            }
        }
        return specification;
    }
}

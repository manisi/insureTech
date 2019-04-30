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

import ir.insurance.startup.domain.OnvanKhodro;
import ir.insurance.startup.domain.*; // for static metamodels
import ir.insurance.startup.repository.OnvanKhodroRepository;
import ir.insurance.startup.service.dto.OnvanKhodroCriteria;
import ir.insurance.startup.service.dto.OnvanKhodroDTO;
import ir.insurance.startup.service.mapper.OnvanKhodroMapper;

/**
 * Service for executing complex queries for OnvanKhodro entities in the database.
 * The main input is a {@link OnvanKhodroCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OnvanKhodroDTO} or a {@link Page} of {@link OnvanKhodroDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OnvanKhodroQueryService extends QueryService<OnvanKhodro> {

    private final Logger log = LoggerFactory.getLogger(OnvanKhodroQueryService.class);

    private final OnvanKhodroRepository onvanKhodroRepository;

    private final OnvanKhodroMapper onvanKhodroMapper;

    public OnvanKhodroQueryService(OnvanKhodroRepository onvanKhodroRepository, OnvanKhodroMapper onvanKhodroMapper) {
        this.onvanKhodroRepository = onvanKhodroRepository;
        this.onvanKhodroMapper = onvanKhodroMapper;
    }

    /**
     * Return a {@link List} of {@link OnvanKhodroDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OnvanKhodroDTO> findByCriteria(OnvanKhodroCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<OnvanKhodro> specification = createSpecification(criteria);
        return onvanKhodroMapper.toDto(onvanKhodroRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OnvanKhodroDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OnvanKhodroDTO> findByCriteria(OnvanKhodroCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<OnvanKhodro> specification = createSpecification(criteria);
        return onvanKhodroRepository.findAll(specification, page)
            .map(onvanKhodroMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(OnvanKhodroCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<OnvanKhodro> specification = createSpecification(criteria);
        return onvanKhodroRepository.count(specification);
    }

    /**
     * Function to convert OnvanKhodroCriteria to a {@link Specification}
     */
    private Specification<OnvanKhodro> createSpecification(OnvanKhodroCriteria criteria) {
        Specification<OnvanKhodro> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), OnvanKhodro_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), OnvanKhodro_.name));
            }
            if (criteria.getSharh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSharh(), OnvanKhodro_.sharh));
            }
            if (criteria.getAzTarikh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAzTarikh(), OnvanKhodro_.azTarikh));
            }
            if (criteria.getTaTarikh() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTaTarikh(), OnvanKhodro_.taTarikh));
            }
            if (criteria.getFaal() != null) {
                specification = specification.and(buildSpecification(criteria.getFaal(), OnvanKhodro_.faal));
            }
        }
        return specification;
    }
}

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

import ir.insurance.startup.domain.SalesJaniCalc;
import ir.insurance.startup.domain.*; // for static metamodels
import ir.insurance.startup.repository.SalesJaniCalcRepository;
import ir.insurance.startup.service.dto.SalesJaniCalcCriteria;
import ir.insurance.startup.service.dto.SalesJaniCalcDTO;
import ir.insurance.startup.service.mapper.SalesJaniCalcMapper;

/**
 * Service for executing complex queries for SalesJaniCalc entities in the database.
 * The main input is a {@link SalesJaniCalcCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SalesJaniCalcDTO} or a {@link Page} of {@link SalesJaniCalcDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SalesJaniCalcQueryService extends QueryService<SalesJaniCalc> {

    private final Logger log = LoggerFactory.getLogger(SalesJaniCalcQueryService.class);

    private final SalesJaniCalcRepository salesJaniCalcRepository;

    private final SalesJaniCalcMapper salesJaniCalcMapper;

    public SalesJaniCalcQueryService(SalesJaniCalcRepository salesJaniCalcRepository, SalesJaniCalcMapper salesJaniCalcMapper) {
        this.salesJaniCalcRepository = salesJaniCalcRepository;
        this.salesJaniCalcMapper = salesJaniCalcMapper;
    }

    /**
     * Return a {@link List} of {@link SalesJaniCalcDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SalesJaniCalcDTO> findByCriteria(SalesJaniCalcCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SalesJaniCalc> specification = createSpecification(criteria);
        return salesJaniCalcMapper.toDto(salesJaniCalcRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SalesJaniCalcDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SalesJaniCalcDTO> findByCriteria(SalesJaniCalcCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SalesJaniCalc> specification = createSpecification(criteria);
        return salesJaniCalcRepository.findAll(specification, page)
            .map(salesJaniCalcMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SalesJaniCalcCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SalesJaniCalc> specification = createSpecification(criteria);
        return salesJaniCalcRepository.count(specification);
    }

    /**
     * Function to convert SalesJaniCalcCriteria to a {@link Specification}
     */
    private Specification<SalesJaniCalc> createSpecification(SalesJaniCalcCriteria criteria) {
        Specification<SalesJaniCalc> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SalesJaniCalc_.id));
            }
            if (criteria.getMablaghJani() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMablaghJani(), SalesJaniCalc_.mablaghJani));
            }
            if (criteria.getMablaghMaliEjbari() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMablaghMaliEjbari(), SalesJaniCalc_.mablaghMaliEjbari));
            }
            if (criteria.getTedadRooz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTedadRooz(), SalesJaniCalc_.tedadRooz));
            }
            if (criteria.getTarikhShoroFaaliat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTarikhShoroFaaliat(), SalesJaniCalc_.tarikhShoroFaaliat));
            }
            if (criteria.getTarikhPayanFaaliat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTarikhPayanFaaliat(), SalesJaniCalc_.tarikhPayanFaaliat));
            }
            if (criteria.getNaamSherkat() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNaamSherkat(), SalesJaniCalc_.naamSherkat));
            }
            if (criteria.getHaghbime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHaghbime(), SalesJaniCalc_.haghbime));
            }
            if (criteria.getBimenameId() != null) {
                specification = specification.and(buildSpecification(criteria.getBimenameId(),
                    root -> root.join(SalesJaniCalc_.bimename, JoinType.LEFT).get(SherkatBime_.id)));
            }
            if (criteria.getGrouhKhodroId() != null) {
                specification = specification.and(buildSpecification(criteria.getGrouhKhodroId(),
                    root -> root.join(SalesJaniCalc_.grouhKhodro, JoinType.LEFT).get(GrouhKhodro_.id)));
            }
        }
        return specification;
    }
}

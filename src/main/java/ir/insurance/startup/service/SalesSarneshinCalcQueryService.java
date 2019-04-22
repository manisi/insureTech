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

import ir.insurance.startup.domain.SalesSarneshinCalc;
import ir.insurance.startup.domain.*; // for static metamodels
import ir.insurance.startup.repository.SalesSarneshinCalcRepository;
import ir.insurance.startup.service.dto.SalesSarneshinCalcCriteria;
import ir.insurance.startup.service.dto.SalesSarneshinCalcDTO;
import ir.insurance.startup.service.mapper.SalesSarneshinCalcMapper;

/**
 * Service for executing complex queries for SalesSarneshinCalc entities in the database.
 * The main input is a {@link SalesSarneshinCalcCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SalesSarneshinCalcDTO} or a {@link Page} of {@link SalesSarneshinCalcDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SalesSarneshinCalcQueryService extends QueryService<SalesSarneshinCalc> {

    private final Logger log = LoggerFactory.getLogger(SalesSarneshinCalcQueryService.class);

    private final SalesSarneshinCalcRepository salesSarneshinCalcRepository;

    private final SalesSarneshinCalcMapper salesSarneshinCalcMapper;

    public SalesSarneshinCalcQueryService(SalesSarneshinCalcRepository salesSarneshinCalcRepository, SalesSarneshinCalcMapper salesSarneshinCalcMapper) {
        this.salesSarneshinCalcRepository = salesSarneshinCalcRepository;
        this.salesSarneshinCalcMapper = salesSarneshinCalcMapper;
    }

    /**
     * Return a {@link List} of {@link SalesSarneshinCalcDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SalesSarneshinCalcDTO> findByCriteria(SalesSarneshinCalcCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SalesSarneshinCalc> specification = createSpecification(criteria);
        return salesSarneshinCalcMapper.toDto(salesSarneshinCalcRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SalesSarneshinCalcDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SalesSarneshinCalcDTO> findByCriteria(SalesSarneshinCalcCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SalesSarneshinCalc> specification = createSpecification(criteria);
        return salesSarneshinCalcRepository.findAll(specification, page)
            .map(salesSarneshinCalcMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SalesSarneshinCalcCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SalesSarneshinCalc> specification = createSpecification(criteria);
        return salesSarneshinCalcRepository.count(specification);
    }

    /**
     * Function to convert SalesSarneshinCalcCriteria to a {@link Specification}
     */
    private Specification<SalesSarneshinCalc> createSpecification(SalesSarneshinCalcCriteria criteria) {
        Specification<SalesSarneshinCalc> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SalesSarneshinCalc_.id));
            }
            if (criteria.getMablaghSalesMazad() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMablaghSalesMazad(), SalesSarneshinCalc_.mablaghSalesMazad));
            }
            if (criteria.getTedadRooz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTedadRooz(), SalesSarneshinCalc_.tedadRooz));
            }
            if (criteria.getMablaghHaghBime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMablaghHaghBime(), SalesSarneshinCalc_.mablaghHaghBime));
            }
            if (criteria.getTarikhShoroo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTarikhShoroo(), SalesSarneshinCalc_.tarikhShoroo));
            }
            if (criteria.getTarikhPayan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTarikhPayan(), SalesSarneshinCalc_.tarikhPayan));
            }
            if (criteria.getNamesherkatId() != null) {
                specification = specification.and(buildSpecification(criteria.getNamesherkatId(),
                    root -> root.join(SalesSarneshinCalc_.namesherkat, JoinType.LEFT).get(SherkatBime_.id)));
            }
            if (criteria.getGrouhKhodroId() != null) {
                specification = specification.and(buildSpecification(criteria.getGrouhKhodroId(),
                    root -> root.join(SalesSarneshinCalc_.grouhKhodro, JoinType.LEFT).get(GrouhKhodro_.id)));
            }
        }
        return specification;
    }
}

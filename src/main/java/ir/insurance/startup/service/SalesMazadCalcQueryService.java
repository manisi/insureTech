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

import ir.insurance.startup.domain.SalesMazadCalc;
import ir.insurance.startup.domain.*; // for static metamodels
import ir.insurance.startup.repository.SalesMazadCalcRepository;
import ir.insurance.startup.service.dto.SalesMazadCalcCriteria;
import ir.insurance.startup.service.dto.SalesMazadCalcDTO;
import ir.insurance.startup.service.mapper.SalesMazadCalcMapper;

/**
 * Service for executing complex queries for SalesMazadCalc entities in the database.
 * The main input is a {@link SalesMazadCalcCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SalesMazadCalcDTO} or a {@link Page} of {@link SalesMazadCalcDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SalesMazadCalcQueryService extends QueryService<SalesMazadCalc> {

    private final Logger log = LoggerFactory.getLogger(SalesMazadCalcQueryService.class);

    private final SalesMazadCalcRepository salesMazadCalcRepository;

    private final SalesMazadCalcMapper salesMazadCalcMapper;

    public SalesMazadCalcQueryService(SalesMazadCalcRepository salesMazadCalcRepository, SalesMazadCalcMapper salesMazadCalcMapper) {
        this.salesMazadCalcRepository = salesMazadCalcRepository;
        this.salesMazadCalcMapper = salesMazadCalcMapper;
    }

    /**
     * Return a {@link List} of {@link SalesMazadCalcDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SalesMazadCalcDTO> findByCriteria(SalesMazadCalcCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SalesMazadCalc> specification = createSpecification(criteria);
        return salesMazadCalcMapper.toDto(salesMazadCalcRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SalesMazadCalcDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SalesMazadCalcDTO> findByCriteria(SalesMazadCalcCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SalesMazadCalc> specification = createSpecification(criteria);
        return salesMazadCalcRepository.findAll(specification, page)
            .map(salesMazadCalcMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SalesMazadCalcCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SalesMazadCalc> specification = createSpecification(criteria);
        return salesMazadCalcRepository.count(specification);
    }

    /**
     * Function to convert SalesMazadCalcCriteria to a {@link Specification}
     */
    private Specification<SalesMazadCalc> createSpecification(SalesMazadCalcCriteria criteria) {
        Specification<SalesMazadCalc> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SalesMazadCalc_.id));
            }
            if (criteria.getMablaghSalesMazad() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMablaghSalesMazad(), SalesMazadCalc_.mablaghSalesMazad));
            }
            if (criteria.getTedadRooz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTedadRooz(), SalesMazadCalc_.tedadRooz));
            }
            if (criteria.getHaghBime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHaghBime(), SalesMazadCalc_.haghBime));
            }
            if (criteria.getTarikhShoroo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTarikhShoroo(), SalesMazadCalc_.tarikhShoroo));
            }
            if (criteria.getTarikhPayan() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTarikhPayan(), SalesMazadCalc_.tarikhPayan));
            }
            if (criteria.getNamesherkatId() != null) {
                specification = specification.and(buildSpecification(criteria.getNamesherkatId(),
                    root -> root.join(SalesMazadCalc_.namesherkat, JoinType.LEFT).get(SherkatBime_.id)));
            }
            if (criteria.getGrouhKhodroId() != null) {
                specification = specification.and(buildSpecification(criteria.getGrouhKhodroId(),
                    root -> root.join(SalesMazadCalc_.grouhKhodro, JoinType.LEFT).get(GrouhKhodro_.id)));
            }
        }
        return specification;
    }
}

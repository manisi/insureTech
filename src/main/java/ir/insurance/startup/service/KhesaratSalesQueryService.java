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

import ir.insurance.startup.domain.KhesaratSales;
import ir.insurance.startup.domain.*; // for static metamodels
import ir.insurance.startup.repository.KhesaratSalesRepository;
import ir.insurance.startup.service.dto.KhesaratSalesCriteria;
import ir.insurance.startup.service.dto.KhesaratSalesDTO;
import ir.insurance.startup.service.mapper.KhesaratSalesMapper;

/**
 * Service for executing complex queries for KhesaratSales entities in the database.
 * The main input is a {@link KhesaratSalesCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link KhesaratSalesDTO} or a {@link Page} of {@link KhesaratSalesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class KhesaratSalesQueryService extends QueryService<KhesaratSales> {

    private final Logger log = LoggerFactory.getLogger(KhesaratSalesQueryService.class);

    private final KhesaratSalesRepository khesaratSalesRepository;

    private final KhesaratSalesMapper khesaratSalesMapper;

    public KhesaratSalesQueryService(KhesaratSalesRepository khesaratSalesRepository, KhesaratSalesMapper khesaratSalesMapper) {
        this.khesaratSalesRepository = khesaratSalesRepository;
        this.khesaratSalesMapper = khesaratSalesMapper;
    }

    /**
     * Return a {@link List} of {@link KhesaratSalesDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<KhesaratSalesDTO> findByCriteria(KhesaratSalesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<KhesaratSales> specification = createSpecification(criteria);
        return khesaratSalesMapper.toDto(khesaratSalesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link KhesaratSalesDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<KhesaratSalesDTO> findByCriteria(KhesaratSalesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<KhesaratSales> specification = createSpecification(criteria);
        return khesaratSalesRepository.findAll(specification, page)
            .map(khesaratSalesMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(KhesaratSalesCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<KhesaratSales> specification = createSpecification(criteria);
        return khesaratSalesRepository.count(specification);
    }

    /**
     * Function to convert KhesaratSalesCriteria to a {@link Specification}
     */
    private Specification<KhesaratSales> createSpecification(KhesaratSalesCriteria criteria) {
        Specification<KhesaratSales> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), KhesaratSales_.id));
            }
            if (criteria.getNoe() != null) {
                specification = specification.and(buildSpecification(criteria.getNoe(), KhesaratSales_.noe));
            }
            if (criteria.getNerkhKhesarat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNerkhKhesarat(), KhesaratSales_.nerkhKhesarat));
            }
            if (criteria.getFaal() != null) {
                specification = specification.and(buildSpecification(criteria.getFaal(), KhesaratSales_.faal));
            }
            if (criteria.getSabegheId() != null) {
                specification = specification.and(buildSpecification(criteria.getSabegheId(),
                    root -> root.join(KhesaratSales_.sabeghe, JoinType.LEFT).get(Sabeghe_.id)));
            }
            if (criteria.getNoeSabegheId() != null) {
                specification = specification.and(buildSpecification(criteria.getNoeSabegheId(),
                    root -> root.join(KhesaratSales_.noeSabeghe, JoinType.LEFT).get(NoeSabeghe_.id)));
            }
        }
        return specification;
    }
}

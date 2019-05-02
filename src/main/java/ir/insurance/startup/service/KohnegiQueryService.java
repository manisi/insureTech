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

import ir.insurance.startup.domain.Kohnegi;
import ir.insurance.startup.domain.*; // for static metamodels
import ir.insurance.startup.repository.KohnegiRepository;
import ir.insurance.startup.service.dto.KohnegiCriteria;
import ir.insurance.startup.service.dto.KohnegiDTO;
import ir.insurance.startup.service.mapper.KohnegiMapper;

/**
 * Service for executing complex queries for Kohnegi entities in the database.
 * The main input is a {@link KohnegiCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link KohnegiDTO} or a {@link Page} of {@link KohnegiDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class KohnegiQueryService extends QueryService<Kohnegi> {

    private final Logger log = LoggerFactory.getLogger(KohnegiQueryService.class);

    private final KohnegiRepository kohnegiRepository;

    private final KohnegiMapper kohnegiMapper;

    public KohnegiQueryService(KohnegiRepository kohnegiRepository, KohnegiMapper kohnegiMapper) {
        this.kohnegiRepository = kohnegiRepository;
        this.kohnegiMapper = kohnegiMapper;
    }

    /**
     * Return a {@link List} of {@link KohnegiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<KohnegiDTO> findByCriteria(KohnegiCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Kohnegi> specification = createSpecification(criteria);
        return kohnegiMapper.toDto(kohnegiRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link KohnegiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<KohnegiDTO> findByCriteria(KohnegiCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Kohnegi> specification = createSpecification(criteria);
        return kohnegiRepository.findAll(specification, page)
            .map(kohnegiMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(KohnegiCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Kohnegi> specification = createSpecification(criteria);
        return kohnegiRepository.count(specification);
    }

    /**
     * Function to convert KohnegiCriteria to a {@link Specification}
     */
    private Specification<Kohnegi> createSpecification(KohnegiCriteria criteria) {
        Specification<Kohnegi> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Kohnegi_.id));
            }
            if (criteria.getDarsadHarSal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDarsadHarSal(), Kohnegi_.darsadHarSal));
            }
            if (criteria.getMaxDarsad() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaxDarsad(), Kohnegi_.maxDarsad));
            }
            if (criteria.getFaal() != null) {
                specification = specification.and(buildSpecification(criteria.getFaal(), Kohnegi_.faal));
            }
            if (criteria.getGrouhKhodroId() != null) {
                specification = specification.and(buildSpecification(criteria.getGrouhKhodroId(),
                    root -> root.join(Kohnegi_.grouhKhodro, JoinType.LEFT).get(GrouhKhodro_.id)));
            }
            if (criteria.getSherkatBimeId() != null) {
                specification = specification.and(buildSpecification(criteria.getSherkatBimeId(),
                    root -> root.join(Kohnegi_.sherkatBime, JoinType.LEFT).get(SherkatBime_.id)));
            }
        }
        return specification;
    }
}

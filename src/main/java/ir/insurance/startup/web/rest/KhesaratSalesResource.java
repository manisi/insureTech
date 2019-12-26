package ir.insurance.startup.web.rest;
import ir.insurance.startup.domain.KhesaratSales;
import ir.insurance.startup.security.AuthoritiesConstants;
import ir.insurance.startup.service.KhesaratSalesService;
import ir.insurance.startup.service.mapper.AdamKhesaratBadaneMapper;
import ir.insurance.startup.service.mapper.KhesaratSalesMapper;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.KhesaratSalesDTO;
import ir.insurance.startup.service.dto.KhesaratSalesCriteria;
import ir.insurance.startup.service.KhesaratSalesQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import ir.insurance.startup.web.rest.vm.KeyAndValueVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing KhesaratSales.
 */
@RestController
@RequestMapping("/api")
public class KhesaratSalesResource {

    private final Logger log = LoggerFactory.getLogger(KhesaratSalesResource.class);

    private static final String ENTITY_NAME = "khesaratSales";

    private final KhesaratSalesService khesaratSalesService;

    private final KhesaratSalesQueryService khesaratSalesQueryService;

    public KhesaratSalesResource(KhesaratSalesService khesaratSalesService, KhesaratSalesQueryService khesaratSalesQueryService) {
        this.khesaratSalesService = khesaratSalesService;
        this.khesaratSalesQueryService = khesaratSalesQueryService;
    }

    /**
     * POST  /khesarat-sales : Create a new khesaratSales.
     *
     * @param khesaratSalesDTO the khesaratSalesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new khesaratSalesDTO, or with status 400 (Bad Request) if the khesaratSales has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/khesarat-sales")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<KhesaratSalesDTO> createKhesaratSales(@Valid @RequestBody KhesaratSalesDTO khesaratSalesDTO) throws URISyntaxException {
        log.debug("REST request to save KhesaratSales : {}", khesaratSalesDTO);
        if (khesaratSalesDTO.getId() != null) {
            throw new BadRequestAlertException("A new khesaratSales cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KhesaratSalesDTO result = khesaratSalesService.save(khesaratSalesDTO);
        return ResponseEntity.created(new URI("/api/khesarat-sales/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /khesarat-sales : Updates an existing khesaratSales.
     *
     * @param khesaratSalesDTO the khesaratSalesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated khesaratSalesDTO,
     * or with status 400 (Bad Request) if the khesaratSalesDTO is not valid,
     * or with status 500 (Internal Server Error) if the khesaratSalesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/khesarat-sales")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<KhesaratSalesDTO> updateKhesaratSales(@Valid @RequestBody KhesaratSalesDTO khesaratSalesDTO) throws URISyntaxException {
        log.debug("REST request to update KhesaratSales : {}", khesaratSalesDTO);
        if (khesaratSalesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KhesaratSalesDTO result = khesaratSalesService.save(khesaratSalesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, khesaratSalesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /khesarat-sales : get all the khesaratSales.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of khesaratSales in body
     */
    @GetMapping("/khesarat-sales")
    public ResponseEntity<List<KhesaratSalesDTO>> getAllKhesaratSales(KhesaratSalesCriteria criteria, Pageable pageable) {
        log.debug("REST request to get KhesaratSales by criteria: {}", criteria);
        Page<KhesaratSalesDTO> page = khesaratSalesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/khesarat-sales");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/khesarat-sales-lookup")
    public ResponseEntity<List<KeyAndValueVM>> getAllKhesaratSales() {
        log.debug("REST request to get KhesaratSalesLookup");
        List<KhesaratSales> list = khesaratSalesService.findAllforlookup();
        List<KeyAndValueVM> res = new ArrayList<>();
        for (KhesaratSales  row: list) {
            res.add(new KeyAndValueVM(row.getId().toString(),row.getSabeghe().getName()));
        }
        log.debug("REST request to get KhesaratSalesLookup"+res.get(0).getKey());
        return ResponseEntity.ok().body(res);
    }

    /**
    * GET  /khesarat-sales/count : count all the khesaratSales.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/khesarat-sales/count")
    public ResponseEntity<Long> countKhesaratSales(KhesaratSalesCriteria criteria) {
        log.debug("REST request to count KhesaratSales by criteria: {}", criteria);
        return ResponseEntity.ok().body(khesaratSalesQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /khesarat-sales/:id : get the "id" khesaratSales.
     *
     * @param id the id of the khesaratSalesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the khesaratSalesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/khesarat-sales/{id}")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<KhesaratSalesDTO> getKhesaratSales(@PathVariable Long id) {
        log.debug("REST request to get KhesaratSales : {}", id);
        Optional<KhesaratSalesDTO> khesaratSalesDTO = khesaratSalesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(khesaratSalesDTO);
    }

    /**
     * DELETE  /khesarat-sales/:id : delete the "id" khesaratSales.
     *
     * @param id the id of the khesaratSalesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/khesarat-sales/{id}")
    public ResponseEntity<Void> deleteKhesaratSales(@PathVariable Long id) {
        log.debug("REST request to delete KhesaratSales : {}", id);
        khesaratSalesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

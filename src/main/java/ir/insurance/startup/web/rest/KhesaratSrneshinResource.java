package ir.insurance.startup.web.rest;
import ir.insurance.startup.security.AuthoritiesConstants;
import ir.insurance.startup.service.KhesaratSrneshinService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.KhesaratSrneshinDTO;
import ir.insurance.startup.service.dto.KhesaratSrneshinCriteria;
import ir.insurance.startup.service.KhesaratSrneshinQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing KhesaratSrneshin.
 */
@RestController
@RequestMapping("/api")
public class KhesaratSrneshinResource {

    private final Logger log = LoggerFactory.getLogger(KhesaratSrneshinResource.class);

    private static final String ENTITY_NAME = "khesaratSrneshin";

    private final KhesaratSrneshinService khesaratSrneshinService;

    private final KhesaratSrneshinQueryService khesaratSrneshinQueryService;

    public KhesaratSrneshinResource(KhesaratSrneshinService khesaratSrneshinService, KhesaratSrneshinQueryService khesaratSrneshinQueryService) {
        this.khesaratSrneshinService = khesaratSrneshinService;
        this.khesaratSrneshinQueryService = khesaratSrneshinQueryService;
    }

    /**
     * POST  /khesarat-srneshins : Create a new khesaratSrneshin.
     *
     * @param khesaratSrneshinDTO the khesaratSrneshinDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new khesaratSrneshinDTO, or with status 400 (Bad Request) if the khesaratSrneshin has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/khesarat-srneshins")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<KhesaratSrneshinDTO> createKhesaratSrneshin(@Valid @RequestBody KhesaratSrneshinDTO khesaratSrneshinDTO) throws URISyntaxException {
        log.debug("REST request to save KhesaratSrneshin : {}", khesaratSrneshinDTO);
        if (khesaratSrneshinDTO.getId() != null) {
            throw new BadRequestAlertException("A new khesaratSrneshin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KhesaratSrneshinDTO result = khesaratSrneshinService.save(khesaratSrneshinDTO);
        return ResponseEntity.created(new URI("/api/khesarat-srneshins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /khesarat-srneshins : Updates an existing khesaratSrneshin.
     *
     * @param khesaratSrneshinDTO the khesaratSrneshinDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated khesaratSrneshinDTO,
     * or with status 400 (Bad Request) if the khesaratSrneshinDTO is not valid,
     * or with status 500 (Internal Server Error) if the khesaratSrneshinDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/khesarat-srneshins")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<KhesaratSrneshinDTO> updateKhesaratSrneshin(@Valid @RequestBody KhesaratSrneshinDTO khesaratSrneshinDTO) throws URISyntaxException {
        log.debug("REST request to update KhesaratSrneshin : {}", khesaratSrneshinDTO);
        if (khesaratSrneshinDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KhesaratSrneshinDTO result = khesaratSrneshinService.save(khesaratSrneshinDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, khesaratSrneshinDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /khesarat-srneshins : get all the khesaratSrneshins.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of khesaratSrneshins in body
     */
    @GetMapping("/khesarat-srneshins")
    public ResponseEntity<List<KhesaratSrneshinDTO>> getAllKhesaratSrneshins(KhesaratSrneshinCriteria criteria, Pageable pageable) {
        log.debug("REST request to get KhesaratSrneshins by criteria: {}", criteria);
        Page<KhesaratSrneshinDTO> page = khesaratSrneshinQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/khesarat-srneshins");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /khesarat-srneshins/count : count all the khesaratSrneshins.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/khesarat-srneshins/count")
    public ResponseEntity<Long> countKhesaratSrneshins(KhesaratSrneshinCriteria criteria) {
        log.debug("REST request to count KhesaratSrneshins by criteria: {}", criteria);
        return ResponseEntity.ok().body(khesaratSrneshinQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /khesarat-srneshins/:id : get the "id" khesaratSrneshin.
     *
     * @param id the id of the khesaratSrneshinDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the khesaratSrneshinDTO, or with status 404 (Not Found)
     */
    @GetMapping("/khesarat-srneshins/{id}")
    public ResponseEntity<KhesaratSrneshinDTO> getKhesaratSrneshin(@PathVariable Long id) {
        log.debug("REST request to get KhesaratSrneshin : {}", id);
        Optional<KhesaratSrneshinDTO> khesaratSrneshinDTO = khesaratSrneshinService.findOne(id);
        return ResponseUtil.wrapOrNotFound(khesaratSrneshinDTO);
    }

    /**
     * DELETE  /khesarat-srneshins/:id : delete the "id" khesaratSrneshin.
     *
     * @param id the id of the khesaratSrneshinDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/khesarat-srneshins/{id}")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteKhesaratSrneshin(@PathVariable Long id) {
        log.debug("REST request to delete KhesaratSrneshin : {}", id);
        khesaratSrneshinService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

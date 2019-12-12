package ir.insurance.startup.web.rest;
import ir.insurance.startup.security.AuthoritiesConstants;
import ir.insurance.startup.service.AdamKhesaratService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.AdamKhesaratDTO;
import ir.insurance.startup.service.dto.AdamKhesaratCriteria;
import ir.insurance.startup.service.AdamKhesaratQueryService;
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
 * REST controller for managing AdamKhesarat.
 */
@RestController
@RequestMapping("/api")
public class AdamKhesaratResource {

    private final Logger log = LoggerFactory.getLogger(AdamKhesaratResource.class);

    private static final String ENTITY_NAME = "adamKhesarat";

    private final AdamKhesaratService adamKhesaratService;

    private final AdamKhesaratQueryService adamKhesaratQueryService;

    public AdamKhesaratResource(AdamKhesaratService adamKhesaratService, AdamKhesaratQueryService adamKhesaratQueryService) {
        this.adamKhesaratService = adamKhesaratService;
        this.adamKhesaratQueryService = adamKhesaratQueryService;
    }

    /**
     * POST  /adam-khesarats : Create a new adamKhesarat.
     *
     * @param adamKhesaratDTO the adamKhesaratDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new adamKhesaratDTO, or with status 400 (Bad Request) if the adamKhesarat has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/adam-khesarats")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<AdamKhesaratDTO> createAdamKhesarat(@Valid @RequestBody AdamKhesaratDTO adamKhesaratDTO) throws URISyntaxException {
        log.debug("REST request to save AdamKhesarat : {}", adamKhesaratDTO);
        if (adamKhesaratDTO.getId() != null) {
            throw new BadRequestAlertException("A new adamKhesarat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdamKhesaratDTO result = adamKhesaratService.save(adamKhesaratDTO);
        return ResponseEntity.created(new URI("/api/adam-khesarats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /adam-khesarats : Updates an existing adamKhesarat.
     *
     * @param adamKhesaratDTO the adamKhesaratDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated adamKhesaratDTO,
     * or with status 400 (Bad Request) if the adamKhesaratDTO is not valid,
     * or with status 500 (Internal Server Error) if the adamKhesaratDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/adam-khesarats")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<AdamKhesaratDTO> updateAdamKhesarat(@Valid @RequestBody AdamKhesaratDTO adamKhesaratDTO) throws URISyntaxException {
        log.debug("REST request to update AdamKhesarat : {}", adamKhesaratDTO);
        if (adamKhesaratDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdamKhesaratDTO result = adamKhesaratService.save(adamKhesaratDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, adamKhesaratDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /adam-khesarats : get all the adamKhesarats.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of adamKhesarats in body
     */
    @GetMapping("/adam-khesarats")
    public ResponseEntity<List<AdamKhesaratDTO>> getAllAdamKhesarats(AdamKhesaratCriteria criteria, Pageable pageable) {
        log.debug("REST request to get AdamKhesarats by criteria: {}", criteria);
        Page<AdamKhesaratDTO> page = adamKhesaratQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/adam-khesarats");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /adam-khesarats/count : count all the adamKhesarats.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/adam-khesarats/count")
    public ResponseEntity<Long> countAdamKhesarats(AdamKhesaratCriteria criteria) {
        log.debug("REST request to count AdamKhesarats by criteria: {}", criteria);
        return ResponseEntity.ok().body(adamKhesaratQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /adam-khesarats/:id : get the "id" adamKhesarat.
     *
     * @param id the id of the adamKhesaratDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the adamKhesaratDTO, or with status 404 (Not Found)
     */
    @GetMapping("/adam-khesarats/{id}")
    public ResponseEntity<AdamKhesaratDTO> getAdamKhesarat(@PathVariable Long id) {
        log.debug("REST request to get AdamKhesarat : {}", id);
        Optional<AdamKhesaratDTO> adamKhesaratDTO = adamKhesaratService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adamKhesaratDTO);
    }

    /**
     * DELETE  /adam-khesarats/:id : delete the "id" adamKhesarat.
     *
     * @param id the id of the adamKhesaratDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/adam-khesarats/{id}")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteAdamKhesarat(@PathVariable Long id) {
        log.debug("REST request to delete AdamKhesarat : {}", id);
        adamKhesaratService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

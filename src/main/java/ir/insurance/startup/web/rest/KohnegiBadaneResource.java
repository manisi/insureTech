package ir.insurance.startup.web.rest;
import ir.insurance.startup.service.KohnegiBadaneService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.KohnegiBadaneDTO;
import ir.insurance.startup.service.dto.KohnegiBadaneCriteria;
import ir.insurance.startup.service.KohnegiBadaneQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing KohnegiBadane.
 */
@RestController
@RequestMapping("/api")
public class KohnegiBadaneResource {

    private final Logger log = LoggerFactory.getLogger(KohnegiBadaneResource.class);

    private static final String ENTITY_NAME = "kohnegiBadane";

    private final KohnegiBadaneService kohnegiBadaneService;

    private final KohnegiBadaneQueryService kohnegiBadaneQueryService;

    public KohnegiBadaneResource(KohnegiBadaneService kohnegiBadaneService, KohnegiBadaneQueryService kohnegiBadaneQueryService) {
        this.kohnegiBadaneService = kohnegiBadaneService;
        this.kohnegiBadaneQueryService = kohnegiBadaneQueryService;
    }

    /**
     * POST  /kohnegi-badanes : Create a new kohnegiBadane.
     *
     * @param kohnegiBadaneDTO the kohnegiBadaneDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new kohnegiBadaneDTO, or with status 400 (Bad Request) if the kohnegiBadane has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/kohnegi-badanes")
    public ResponseEntity<KohnegiBadaneDTO> createKohnegiBadane(@Valid @RequestBody KohnegiBadaneDTO kohnegiBadaneDTO) throws URISyntaxException {
        log.debug("REST request to save KohnegiBadane : {}", kohnegiBadaneDTO);
        if (kohnegiBadaneDTO.getId() != null) {
            throw new BadRequestAlertException("A new kohnegiBadane cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KohnegiBadaneDTO result = kohnegiBadaneService.save(kohnegiBadaneDTO);
        return ResponseEntity.created(new URI("/api/kohnegi-badanes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /kohnegi-badanes : Updates an existing kohnegiBadane.
     *
     * @param kohnegiBadaneDTO the kohnegiBadaneDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated kohnegiBadaneDTO,
     * or with status 400 (Bad Request) if the kohnegiBadaneDTO is not valid,
     * or with status 500 (Internal Server Error) if the kohnegiBadaneDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/kohnegi-badanes")
    public ResponseEntity<KohnegiBadaneDTO> updateKohnegiBadane(@Valid @RequestBody KohnegiBadaneDTO kohnegiBadaneDTO) throws URISyntaxException {
        log.debug("REST request to update KohnegiBadane : {}", kohnegiBadaneDTO);
        if (kohnegiBadaneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KohnegiBadaneDTO result = kohnegiBadaneService.save(kohnegiBadaneDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, kohnegiBadaneDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /kohnegi-badanes : get all the kohnegiBadanes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of kohnegiBadanes in body
     */
    @GetMapping("/kohnegi-badanes")
    public ResponseEntity<List<KohnegiBadaneDTO>> getAllKohnegiBadanes(KohnegiBadaneCriteria criteria, Pageable pageable) {
        log.debug("REST request to get KohnegiBadanes by criteria: {}", criteria);
        Page<KohnegiBadaneDTO> page = kohnegiBadaneQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/kohnegi-badanes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /kohnegi-badanes/count : count all the kohnegiBadanes.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/kohnegi-badanes/count")
    public ResponseEntity<Long> countKohnegiBadanes(KohnegiBadaneCriteria criteria) {
        log.debug("REST request to count KohnegiBadanes by criteria: {}", criteria);
        return ResponseEntity.ok().body(kohnegiBadaneQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /kohnegi-badanes/:id : get the "id" kohnegiBadane.
     *
     * @param id the id of the kohnegiBadaneDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the kohnegiBadaneDTO, or with status 404 (Not Found)
     */
    @GetMapping("/kohnegi-badanes/{id}")
    public ResponseEntity<KohnegiBadaneDTO> getKohnegiBadane(@PathVariable Long id) {
        log.debug("REST request to get KohnegiBadane : {}", id);
        Optional<KohnegiBadaneDTO> kohnegiBadaneDTO = kohnegiBadaneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(kohnegiBadaneDTO);
    }

    /**
     * DELETE  /kohnegi-badanes/:id : delete the "id" kohnegiBadane.
     *
     * @param id the id of the kohnegiBadaneDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/kohnegi-badanes/{id}")
    public ResponseEntity<Void> deleteKohnegiBadane(@PathVariable Long id) {
        log.debug("REST request to delete KohnegiBadane : {}", id);
        kohnegiBadaneService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

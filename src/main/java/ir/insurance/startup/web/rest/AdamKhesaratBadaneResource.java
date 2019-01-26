package ir.insurance.startup.web.rest;
import ir.insurance.startup.service.AdamKhesaratBadaneService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.AdamKhesaratBadaneDTO;
import ir.insurance.startup.service.dto.AdamKhesaratBadaneCriteria;
import ir.insurance.startup.service.AdamKhesaratBadaneQueryService;
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
 * REST controller for managing AdamKhesaratBadane.
 */
@RestController
@RequestMapping("/api")
public class AdamKhesaratBadaneResource {

    private final Logger log = LoggerFactory.getLogger(AdamKhesaratBadaneResource.class);

    private static final String ENTITY_NAME = "adamKhesaratBadane";

    private final AdamKhesaratBadaneService adamKhesaratBadaneService;

    private final AdamKhesaratBadaneQueryService adamKhesaratBadaneQueryService;

    public AdamKhesaratBadaneResource(AdamKhesaratBadaneService adamKhesaratBadaneService, AdamKhesaratBadaneQueryService adamKhesaratBadaneQueryService) {
        this.adamKhesaratBadaneService = adamKhesaratBadaneService;
        this.adamKhesaratBadaneQueryService = adamKhesaratBadaneQueryService;
    }

    /**
     * POST  /adam-khesarat-badanes : Create a new adamKhesaratBadane.
     *
     * @param adamKhesaratBadaneDTO the adamKhesaratBadaneDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new adamKhesaratBadaneDTO, or with status 400 (Bad Request) if the adamKhesaratBadane has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/adam-khesarat-badanes")
    public ResponseEntity<AdamKhesaratBadaneDTO> createAdamKhesaratBadane(@Valid @RequestBody AdamKhesaratBadaneDTO adamKhesaratBadaneDTO) throws URISyntaxException {
        log.debug("REST request to save AdamKhesaratBadane : {}", adamKhesaratBadaneDTO);
        if (adamKhesaratBadaneDTO.getId() != null) {
            throw new BadRequestAlertException("A new adamKhesaratBadane cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdamKhesaratBadaneDTO result = adamKhesaratBadaneService.save(adamKhesaratBadaneDTO);
        return ResponseEntity.created(new URI("/api/adam-khesarat-badanes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /adam-khesarat-badanes : Updates an existing adamKhesaratBadane.
     *
     * @param adamKhesaratBadaneDTO the adamKhesaratBadaneDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated adamKhesaratBadaneDTO,
     * or with status 400 (Bad Request) if the adamKhesaratBadaneDTO is not valid,
     * or with status 500 (Internal Server Error) if the adamKhesaratBadaneDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/adam-khesarat-badanes")
    public ResponseEntity<AdamKhesaratBadaneDTO> updateAdamKhesaratBadane(@Valid @RequestBody AdamKhesaratBadaneDTO adamKhesaratBadaneDTO) throws URISyntaxException {
        log.debug("REST request to update AdamKhesaratBadane : {}", adamKhesaratBadaneDTO);
        if (adamKhesaratBadaneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdamKhesaratBadaneDTO result = adamKhesaratBadaneService.save(adamKhesaratBadaneDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, adamKhesaratBadaneDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /adam-khesarat-badanes : get all the adamKhesaratBadanes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of adamKhesaratBadanes in body
     */
    @GetMapping("/adam-khesarat-badanes")
    public ResponseEntity<List<AdamKhesaratBadaneDTO>> getAllAdamKhesaratBadanes(AdamKhesaratBadaneCriteria criteria, Pageable pageable) {
        log.debug("REST request to get AdamKhesaratBadanes by criteria: {}", criteria);
        Page<AdamKhesaratBadaneDTO> page = adamKhesaratBadaneQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/adam-khesarat-badanes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /adam-khesarat-badanes/count : count all the adamKhesaratBadanes.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/adam-khesarat-badanes/count")
    public ResponseEntity<Long> countAdamKhesaratBadanes(AdamKhesaratBadaneCriteria criteria) {
        log.debug("REST request to count AdamKhesaratBadanes by criteria: {}", criteria);
        return ResponseEntity.ok().body(adamKhesaratBadaneQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /adam-khesarat-badanes/:id : get the "id" adamKhesaratBadane.
     *
     * @param id the id of the adamKhesaratBadaneDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the adamKhesaratBadaneDTO, or with status 404 (Not Found)
     */
    @GetMapping("/adam-khesarat-badanes/{id}")
    public ResponseEntity<AdamKhesaratBadaneDTO> getAdamKhesaratBadane(@PathVariable Long id) {
        log.debug("REST request to get AdamKhesaratBadane : {}", id);
        Optional<AdamKhesaratBadaneDTO> adamKhesaratBadaneDTO = adamKhesaratBadaneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adamKhesaratBadaneDTO);
    }

    /**
     * DELETE  /adam-khesarat-badanes/:id : delete the "id" adamKhesaratBadane.
     *
     * @param id the id of the adamKhesaratBadaneDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/adam-khesarat-badanes/{id}")
    public ResponseEntity<Void> deleteAdamKhesaratBadane(@PathVariable Long id) {
        log.debug("REST request to delete AdamKhesaratBadane : {}", id);
        adamKhesaratBadaneService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

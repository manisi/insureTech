package ir.insurance.startup.web.rest;

import com.codahale.metrics.annotation.Timed;
import ir.insurance.startup.service.JarimeDirkardService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.JarimeDirkardDTO;
import ir.insurance.startup.service.dto.JarimeDirkardCriteria;
import ir.insurance.startup.service.JarimeDirkardQueryService;
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
 * REST controller for managing JarimeDirkard.
 */
@RestController
@RequestMapping("/api")
public class JarimeDirkardResource {

    private final Logger log = LoggerFactory.getLogger(JarimeDirkardResource.class);

    private static final String ENTITY_NAME = "jarimeDirkard";

    private final JarimeDirkardService jarimeDirkardService;

    private final JarimeDirkardQueryService jarimeDirkardQueryService;

    public JarimeDirkardResource(JarimeDirkardService jarimeDirkardService, JarimeDirkardQueryService jarimeDirkardQueryService) {
        this.jarimeDirkardService = jarimeDirkardService;
        this.jarimeDirkardQueryService = jarimeDirkardQueryService;
    }

    /**
     * POST  /jarime-dirkards : Create a new jarimeDirkard.
     *
     * @param jarimeDirkardDTO the jarimeDirkardDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new jarimeDirkardDTO, or with status 400 (Bad Request) if the jarimeDirkard has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/jarime-dirkards")
    @Timed
    public ResponseEntity<JarimeDirkardDTO> createJarimeDirkard(@Valid @RequestBody JarimeDirkardDTO jarimeDirkardDTO) throws URISyntaxException {
        log.debug("REST request to save JarimeDirkard : {}", jarimeDirkardDTO);
        if (jarimeDirkardDTO.getId() != null) {
            throw new BadRequestAlertException("A new jarimeDirkard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JarimeDirkardDTO result = jarimeDirkardService.save(jarimeDirkardDTO);
        return ResponseEntity.created(new URI("/api/jarime-dirkards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /jarime-dirkards : Updates an existing jarimeDirkard.
     *
     * @param jarimeDirkardDTO the jarimeDirkardDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated jarimeDirkardDTO,
     * or with status 400 (Bad Request) if the jarimeDirkardDTO is not valid,
     * or with status 500 (Internal Server Error) if the jarimeDirkardDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/jarime-dirkards")
    @Timed
    public ResponseEntity<JarimeDirkardDTO> updateJarimeDirkard(@Valid @RequestBody JarimeDirkardDTO jarimeDirkardDTO) throws URISyntaxException {
        log.debug("REST request to update JarimeDirkard : {}", jarimeDirkardDTO);
        if (jarimeDirkardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        JarimeDirkardDTO result = jarimeDirkardService.save(jarimeDirkardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, jarimeDirkardDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /jarime-dirkards : get all the jarimeDirkards.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of jarimeDirkards in body
     */
    @GetMapping("/jarime-dirkards")
    @Timed
    public ResponseEntity<List<JarimeDirkardDTO>> getAllJarimeDirkards(JarimeDirkardCriteria criteria, Pageable pageable) {
        log.debug("REST request to get JarimeDirkards by criteria: {}", criteria);
        Page<JarimeDirkardDTO> page = jarimeDirkardQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/jarime-dirkards");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /jarime-dirkards/count : count all the jarimeDirkards.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/jarime-dirkards/count")
    @Timed
    public ResponseEntity<Long> countJarimeDirkards(JarimeDirkardCriteria criteria) {
        log.debug("REST request to count JarimeDirkards by criteria: {}", criteria);
        return ResponseEntity.ok().body(jarimeDirkardQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /jarime-dirkards/:id : get the "id" jarimeDirkard.
     *
     * @param id the id of the jarimeDirkardDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the jarimeDirkardDTO, or with status 404 (Not Found)
     */
    @GetMapping("/jarime-dirkards/{id}")
    @Timed
    public ResponseEntity<JarimeDirkardDTO> getJarimeDirkard(@PathVariable Long id) {
        log.debug("REST request to get JarimeDirkard : {}", id);
        Optional<JarimeDirkardDTO> jarimeDirkardDTO = jarimeDirkardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jarimeDirkardDTO);
    }

    /**
     * DELETE  /jarime-dirkards/:id : delete the "id" jarimeDirkard.
     *
     * @param id the id of the jarimeDirkardDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/jarime-dirkards/{id}")
    @Timed
    public ResponseEntity<Void> deleteJarimeDirkard(@PathVariable Long id) {
        log.debug("REST request to delete JarimeDirkard : {}", id);
        jarimeDirkardService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

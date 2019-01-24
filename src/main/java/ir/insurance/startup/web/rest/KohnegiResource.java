package ir.insurance.startup.web.rest;

import com.codahale.metrics.annotation.Timed;
import ir.insurance.startup.service.KohnegiService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.KohnegiDTO;
import ir.insurance.startup.service.dto.KohnegiCriteria;
import ir.insurance.startup.service.KohnegiQueryService;
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
 * REST controller for managing Kohnegi.
 */
@RestController
@RequestMapping("/api")
public class KohnegiResource {

    private final Logger log = LoggerFactory.getLogger(KohnegiResource.class);

    private static final String ENTITY_NAME = "kohnegi";

    private final KohnegiService kohnegiService;

    private final KohnegiQueryService kohnegiQueryService;

    public KohnegiResource(KohnegiService kohnegiService, KohnegiQueryService kohnegiQueryService) {
        this.kohnegiService = kohnegiService;
        this.kohnegiQueryService = kohnegiQueryService;
    }

    /**
     * POST  /kohnegis : Create a new kohnegi.
     *
     * @param kohnegiDTO the kohnegiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new kohnegiDTO, or with status 400 (Bad Request) if the kohnegi has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/kohnegis")
    @Timed
    public ResponseEntity<KohnegiDTO> createKohnegi(@Valid @RequestBody KohnegiDTO kohnegiDTO) throws URISyntaxException {
        log.debug("REST request to save Kohnegi : {}", kohnegiDTO);
        if (kohnegiDTO.getId() != null) {
            throw new BadRequestAlertException("A new kohnegi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KohnegiDTO result = kohnegiService.save(kohnegiDTO);
        return ResponseEntity.created(new URI("/api/kohnegis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /kohnegis : Updates an existing kohnegi.
     *
     * @param kohnegiDTO the kohnegiDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated kohnegiDTO,
     * or with status 400 (Bad Request) if the kohnegiDTO is not valid,
     * or with status 500 (Internal Server Error) if the kohnegiDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/kohnegis")
    @Timed
    public ResponseEntity<KohnegiDTO> updateKohnegi(@Valid @RequestBody KohnegiDTO kohnegiDTO) throws URISyntaxException {
        log.debug("REST request to update Kohnegi : {}", kohnegiDTO);
        if (kohnegiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KohnegiDTO result = kohnegiService.save(kohnegiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, kohnegiDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /kohnegis : get all the kohnegis.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of kohnegis in body
     */
    @GetMapping("/kohnegis")
    @Timed
    public ResponseEntity<List<KohnegiDTO>> getAllKohnegis(KohnegiCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Kohnegis by criteria: {}", criteria);
        Page<KohnegiDTO> page = kohnegiQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/kohnegis");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /kohnegis/count : count all the kohnegis.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/kohnegis/count")
    @Timed
    public ResponseEntity<Long> countKohnegis(KohnegiCriteria criteria) {
        log.debug("REST request to count Kohnegis by criteria: {}", criteria);
        return ResponseEntity.ok().body(kohnegiQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /kohnegis/:id : get the "id" kohnegi.
     *
     * @param id the id of the kohnegiDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the kohnegiDTO, or with status 404 (Not Found)
     */
    @GetMapping("/kohnegis/{id}")
    @Timed
    public ResponseEntity<KohnegiDTO> getKohnegi(@PathVariable Long id) {
        log.debug("REST request to get Kohnegi : {}", id);
        Optional<KohnegiDTO> kohnegiDTO = kohnegiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(kohnegiDTO);
    }

    /**
     * DELETE  /kohnegis/:id : delete the "id" kohnegi.
     *
     * @param id the id of the kohnegiDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/kohnegis/{id}")
    @Timed
    public ResponseEntity<Void> deleteKohnegi(@PathVariable Long id) {
        log.debug("REST request to delete Kohnegi : {}", id);
        kohnegiService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

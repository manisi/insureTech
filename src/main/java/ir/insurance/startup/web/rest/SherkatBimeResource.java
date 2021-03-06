package ir.insurance.startup.web.rest;
import ir.insurance.startup.domain.SherkatBime;
import ir.insurance.startup.security.AuthoritiesConstants;
import ir.insurance.startup.service.SherkatBimeService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.SherkatBimeDTO;
import io.github.jhipster.web.util.ResponseUtil;
import ir.insurance.startup.web.rest.vm.KeyAndValueVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SherkatBime.
 */
@RestController
@RequestMapping("/api")
public class SherkatBimeResource {

    private final Logger log = LoggerFactory.getLogger(SherkatBimeResource.class);

    private static final String ENTITY_NAME = "sherkatBime";

    private final SherkatBimeService sherkatBimeService;

    public SherkatBimeResource(SherkatBimeService sherkatBimeService) {
        this.sherkatBimeService = sherkatBimeService;
    }

    /**
     * POST  /sherkat-bimes : Create a new sherkatBime.
     *
     * @param sherkatBimeDTO the sherkatBimeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sherkatBimeDTO, or with status 400 (Bad Request) if the sherkatBime has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sherkat-bimes")
    public ResponseEntity<SherkatBimeDTO> createSherkatBime(@RequestBody SherkatBimeDTO sherkatBimeDTO) throws URISyntaxException {
        log.debug("REST request to save SherkatBime : {}", sherkatBimeDTO);
        if (sherkatBimeDTO.getId() != null) {
            throw new BadRequestAlertException("A new sherkatBime cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SherkatBimeDTO result = sherkatBimeService.save(sherkatBimeDTO);
        return ResponseEntity.created(new URI("/api/sherkat-bimes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sherkat-bimes : Updates an existing sherkatBime.
     *
     * @param sherkatBimeDTO the sherkatBimeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sherkatBimeDTO,
     * or with status 400 (Bad Request) if the sherkatBimeDTO is not valid,
     * or with status 500 (Internal Server Error) if the sherkatBimeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sherkat-bimes")
    public ResponseEntity<SherkatBimeDTO> updateSherkatBime(@RequestBody SherkatBimeDTO sherkatBimeDTO) throws URISyntaxException {
        log.debug("REST request to update SherkatBime : {}", sherkatBimeDTO);
        if (sherkatBimeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SherkatBimeDTO result = sherkatBimeService.save(sherkatBimeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sherkatBimeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sherkat-bimes : get all the sherkatBimes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of sherkatBimes in body
     */
    @GetMapping("/sherkat-bimes")
    public ResponseEntity<List<SherkatBimeDTO>> getAllSherkatBimes(Pageable pageable) {
        log.debug("REST request to get a page of SherkatBimes");
        Page<SherkatBimeDTO> page = sherkatBimeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sherkat-bimes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/sherkat-bimes-lookup")
    public ResponseEntity<List<KeyAndValueVM>> getAllSherkatBimesLookup() {
        log.debug("REST request to getAllSherkatBimesLookup");
        List<SherkatBime> list = sherkatBimeService.findAllforlookup();
        List<KeyAndValueVM> res = new ArrayList<>();
        for (SherkatBime  row: list) {
            res.add(new KeyAndValueVM(row.getId().toString(),row.getName()));
        }
        return ResponseEntity.ok().body(res);
    }

    /**
     * GET  /sherkat-bimes/:id : get the "id" sherkatBime.
     *
     * @param id the id of the sherkatBimeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sherkatBimeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/sherkat-bimes/{id}")
    public ResponseEntity<SherkatBimeDTO> getSherkatBime(@PathVariable Long id) {
        log.debug("REST request to get SherkatBime : {}", id);
        Optional<SherkatBimeDTO> sherkatBimeDTO = sherkatBimeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sherkatBimeDTO);
    }

    /**
     * DELETE  /sherkat-bimes/:id : delete the "id" sherkatBime.
     *
     * @param id the id of the sherkatBimeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sherkat-bimes/{id}")
    public ResponseEntity<Void> deleteSherkatBime(@PathVariable Long id) {
        log.debug("REST request to delete SherkatBime : {}", id);
        sherkatBimeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

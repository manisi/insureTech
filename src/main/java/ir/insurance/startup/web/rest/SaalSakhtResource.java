package ir.insurance.startup.web.rest;
import ir.insurance.startup.domain.SaalSakht;
import ir.insurance.startup.security.AuthoritiesConstants;
import ir.insurance.startup.service.SaalSakhtService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.SaalSakhtDTO;
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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SaalSakht.
 */
@RestController
@RequestMapping("/api")
public class SaalSakhtResource {

    private final Logger log = LoggerFactory.getLogger(SaalSakhtResource.class);

    private static final String ENTITY_NAME = "saalSakht";

    private final SaalSakhtService saalSakhtService;

    public SaalSakhtResource(SaalSakhtService saalSakhtService) {
        this.saalSakhtService = saalSakhtService;
    }

    /**
     * POST  /saal-sakhts : Create a new saalSakht.
     *
     * @param saalSakhtDTO the saalSakhtDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new saalSakhtDTO, or with status 400 (Bad Request) if the saalSakht has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/saal-sakhts")
    public ResponseEntity<SaalSakhtDTO> createSaalSakht(@Valid @RequestBody SaalSakhtDTO saalSakhtDTO) throws URISyntaxException {
        log.debug("REST request to save SaalSakht : {}", saalSakhtDTO);
        if (saalSakhtDTO.getId() != null) {
            throw new BadRequestAlertException("A new saalSakht cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SaalSakhtDTO result = saalSakhtService.save(saalSakhtDTO);
        return ResponseEntity.created(new URI("/api/saal-sakhts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /saal-sakhts : Updates an existing saalSakht.
     *
     * @param saalSakhtDTO the saalSakhtDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated saalSakhtDTO,
     * or with status 400 (Bad Request) if the saalSakhtDTO is not valid,
     * or with status 500 (Internal Server Error) if the saalSakhtDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/saal-sakhts")
    public ResponseEntity<SaalSakhtDTO> updateSaalSakht(@Valid @RequestBody SaalSakhtDTO saalSakhtDTO) throws URISyntaxException {
        log.debug("REST request to update SaalSakht : {}", saalSakhtDTO);
        if (saalSakhtDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SaalSakhtDTO result = saalSakhtService.save(saalSakhtDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, saalSakhtDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /saal-sakhts : get all the saalSakhts.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of saalSakhts in body
     */
    @GetMapping("/saal-sakhts")
    public ResponseEntity<List<SaalSakhtDTO>> getAllSaalSakhts(Pageable pageable) {
        log.debug("REST request to get a page of SaalSakhts");
        Page<SaalSakhtDTO> page = saalSakhtService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/saal-sakhts");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    @GetMapping("/saal-sakhts-lookup")
    public ResponseEntity<List<KeyAndValueVM>> getAllSaalSakhtsLookup() {
        log.debug("REST request to getAllSaalSakhtsLookup");
        List<SaalSakht> list = saalSakhtService.findAllforlookup();
        List<KeyAndValueVM> res = new ArrayList<>();
        for (SaalSakht  row: list) {
            res.add(new KeyAndValueVM(row.getId().toString(),row.getSaalShamsi()+" - "+row.getSaalMiladi()));
        }
        return ResponseEntity.ok().body(res);
    }
    /**
     * GET  /saal-sakhts/:id : get the "id" saalSakht.
     *
     * @param id the id of the saalSakhtDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the saalSakhtDTO, or with status 404 (Not Found)
     */
    @GetMapping("/saal-sakhts/{id}")
    public ResponseEntity<SaalSakhtDTO> getSaalSakht(@PathVariable Long id) {
        log.debug("REST request to get SaalSakht : {}", id);
        Optional<SaalSakhtDTO> saalSakhtDTO = saalSakhtService.findOne(id);
        return ResponseUtil.wrapOrNotFound(saalSakhtDTO);
    }

    /**
     * DELETE  /saal-sakhts/:id : delete the "id" saalSakht.
     *
     * @param id the id of the saalSakhtDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/saal-sakhts/{id}")
    public ResponseEntity<Void> deleteSaalSakht(@PathVariable Long id) {
        log.debug("REST request to delete SaalSakht : {}", id);
        saalSakhtService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

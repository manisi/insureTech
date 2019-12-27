package ir.insurance.startup.web.rest;
import ir.insurance.startup.domain.AnvaeKhodro;
import ir.insurance.startup.security.AuthoritiesConstants;
import ir.insurance.startup.service.AnvaeKhodroService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.AnvaeKhodroDTO;
import ir.insurance.startup.service.dto.AnvaeKhodroCriteria;
import ir.insurance.startup.service.AnvaeKhodroQueryService;
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
 * REST controller for managing AnvaeKhodro.
 */
@RestController
@RequestMapping("/api")
public class AnvaeKhodroResource {

    private final Logger log = LoggerFactory.getLogger(AnvaeKhodroResource.class);

    private static final String ENTITY_NAME = "anvaeKhodro";

    private final AnvaeKhodroService anvaeKhodroService;

    private final AnvaeKhodroQueryService anvaeKhodroQueryService;

    public AnvaeKhodroResource(AnvaeKhodroService anvaeKhodroService, AnvaeKhodroQueryService anvaeKhodroQueryService) {
        this.anvaeKhodroService = anvaeKhodroService;
        this.anvaeKhodroQueryService = anvaeKhodroQueryService;
    }

    /**
     * POST  /anvae-khodros : Create a new anvaeKhodro.
     *
     * @param anvaeKhodroDTO the anvaeKhodroDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new anvaeKhodroDTO, or with status 400 (Bad Request) if the anvaeKhodro has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/anvae-khodros")
    public ResponseEntity<AnvaeKhodroDTO> createAnvaeKhodro(@Valid @RequestBody AnvaeKhodroDTO anvaeKhodroDTO) throws URISyntaxException {
        log.debug("REST request to save AnvaeKhodro : {}", anvaeKhodroDTO);
        if (anvaeKhodroDTO.getId() != null) {
            throw new BadRequestAlertException("A new anvaeKhodro cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AnvaeKhodroDTO result = anvaeKhodroService.save(anvaeKhodroDTO);
        return ResponseEntity.created(new URI("/api/anvae-khodros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /anvae-khodros : Updates an existing anvaeKhodro.
     *
     * @param anvaeKhodroDTO the anvaeKhodroDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated anvaeKhodroDTO,
     * or with status 400 (Bad Request) if the anvaeKhodroDTO is not valid,
     * or with status 500 (Internal Server Error) if the anvaeKhodroDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/anvae-khodros")
    public ResponseEntity<AnvaeKhodroDTO> updateAnvaeKhodro(@Valid @RequestBody AnvaeKhodroDTO anvaeKhodroDTO) throws URISyntaxException {
        log.debug("REST request to update AnvaeKhodro : {}", anvaeKhodroDTO);
        if (anvaeKhodroDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AnvaeKhodroDTO result = anvaeKhodroService.save(anvaeKhodroDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, anvaeKhodroDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /anvae-khodros : get all the anvaeKhodros.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of anvaeKhodros in body
     */
    @GetMapping("/anvae-khodros")
    public ResponseEntity<List<AnvaeKhodroDTO>> getAllAnvaeKhodros(AnvaeKhodroCriteria criteria, Pageable pageable) {
        log.debug("REST request to get AnvaeKhodros by criteria: {}", criteria);
        Page<AnvaeKhodroDTO> page = anvaeKhodroQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/anvae-khodros");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    @GetMapping("/anvae-khodros-lookup")
    public ResponseEntity<List<KeyAndValueVM>> getAllAnvaeKhodros() {
        log.debug("REST request to get AnvaeKhodroLookup");
        List<AnvaeKhodro> list = anvaeKhodroService.findAllforlookup();
        List<KeyAndValueVM> res = new ArrayList<>();
        for (AnvaeKhodro  row: list) {
            res.add(new KeyAndValueVM(row.getId().toString(),(row.getGrouhVasile()+"-"+row.getSystemVasile()+"-"+row.getOnvan())));
        }
        return ResponseEntity.ok().body(res);
    }
    /**
    * GET  /anvae-khodros/count : count all the anvaeKhodros.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/anvae-khodros/count")
    public ResponseEntity<Long> countAnvaeKhodros(AnvaeKhodroCriteria criteria) {
        log.debug("REST request to count AnvaeKhodros by criteria: {}", criteria);
        return ResponseEntity.ok().body(anvaeKhodroQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /anvae-khodros/:id : get the "id" anvaeKhodro.
     *
     * @param id the id of the anvaeKhodroDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the anvaeKhodroDTO, or with status 404 (Not Found)
     */
    @GetMapping("/anvae-khodros/{id}")
    public ResponseEntity<AnvaeKhodroDTO> getAnvaeKhodro(@PathVariable Long id) {
        log.debug("REST request to get AnvaeKhodro : {}", id);
        Optional<AnvaeKhodroDTO> anvaeKhodroDTO = anvaeKhodroService.findOne(id);
        return ResponseUtil.wrapOrNotFound(anvaeKhodroDTO);
    }

    /**
     * DELETE  /anvae-khodros/:id : delete the "id" anvaeKhodro.
     *
     * @param id the id of the anvaeKhodroDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/anvae-khodros/{id}")
    public ResponseEntity<Void> deleteAnvaeKhodro(@PathVariable Long id) {
        log.debug("REST request to delete AnvaeKhodro : {}", id);
        anvaeKhodroService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

package ir.insurance.startup.web.rest;
import ir.insurance.startup.domain.OnvanKhodro;
import ir.insurance.startup.security.AuthoritiesConstants;
import ir.insurance.startup.service.OnvanKhodroService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.OnvanKhodroDTO;
import ir.insurance.startup.service.dto.OnvanKhodroCriteria;
import ir.insurance.startup.service.OnvanKhodroQueryService;
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
 * REST controller for managing OnvanKhodro.
 */
@RestController
@RequestMapping("/api")
public class OnvanKhodroResource {

    private final Logger log = LoggerFactory.getLogger(OnvanKhodroResource.class);

    private static final String ENTITY_NAME = "onvanKhodro";

    private final OnvanKhodroService onvanKhodroService;

    private final OnvanKhodroQueryService onvanKhodroQueryService;

    public OnvanKhodroResource(OnvanKhodroService onvanKhodroService, OnvanKhodroQueryService onvanKhodroQueryService) {
        this.onvanKhodroService = onvanKhodroService;
        this.onvanKhodroQueryService = onvanKhodroQueryService;
    }

    /**
     * POST  /onvan-khodros : Create a new onvanKhodro.
     *
     * @param onvanKhodroDTO the onvanKhodroDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new onvanKhodroDTO, or with status 400 (Bad Request) if the onvanKhodro has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/onvan-khodros")
    public ResponseEntity<OnvanKhodroDTO> createOnvanKhodro(@Valid @RequestBody OnvanKhodroDTO onvanKhodroDTO) throws URISyntaxException {
        log.debug("REST request to save OnvanKhodro : {}", onvanKhodroDTO);
        if (onvanKhodroDTO.getId() != null) {
            throw new BadRequestAlertException("A new onvanKhodro cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OnvanKhodroDTO result = onvanKhodroService.save(onvanKhodroDTO);
        return ResponseEntity.created(new URI("/api/onvan-khodros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /onvan-khodros : Updates an existing onvanKhodro.
     *
     * @param onvanKhodroDTO the onvanKhodroDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated onvanKhodroDTO,
     * or with status 400 (Bad Request) if the onvanKhodroDTO is not valid,
     * or with status 500 (Internal Server Error) if the onvanKhodroDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/onvan-khodros")
    public ResponseEntity<OnvanKhodroDTO> updateOnvanKhodro(@Valid @RequestBody OnvanKhodroDTO onvanKhodroDTO) throws URISyntaxException {
        log.debug("REST request to update OnvanKhodro : {}", onvanKhodroDTO);
        if (onvanKhodroDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OnvanKhodroDTO result = onvanKhodroService.save(onvanKhodroDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, onvanKhodroDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /onvan-khodros : get all the onvanKhodros.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of onvanKhodros in body
     */
    @GetMapping("/onvan-khodros")
    public ResponseEntity<List<OnvanKhodroDTO>> getAllOnvanKhodros(OnvanKhodroCriteria criteria, Pageable pageable) {
        log.debug("REST request to get OnvanKhodros by criteria: {}", criteria);
        Page<OnvanKhodroDTO> page = onvanKhodroQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/onvan-khodros");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/onvan-khodros-lookup")
    public ResponseEntity<List<KeyAndValueVM>> getAllOnvanKhodrosLookup() {
        log.debug("REST request to getAllOnvanKhodrosLookup");
        List<OnvanKhodro> list = onvanKhodroService.findAllforlookup();
        List<KeyAndValueVM> res = new ArrayList<>();
        for (OnvanKhodro  row: list) {
            res.add(new KeyAndValueVM(row.getId().toString(),row.getName()));
        }
        return ResponseEntity.ok().body(res);
    }

    /**
    * GET  /onvan-khodros/count : count all the onvanKhodros.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/onvan-khodros/count")
    public ResponseEntity<Long> countOnvanKhodros(OnvanKhodroCriteria criteria) {
        log.debug("REST request to count OnvanKhodros by criteria: {}", criteria);
        return ResponseEntity.ok().body(onvanKhodroQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /onvan-khodros/:id : get the "id" onvanKhodro.
     *
     * @param id the id of the onvanKhodroDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the onvanKhodroDTO, or with status 404 (Not Found)
     */
    @GetMapping("/onvan-khodros/{id}")
    public ResponseEntity<OnvanKhodroDTO> getOnvanKhodro(@PathVariable Long id) {
        log.debug("REST request to get OnvanKhodro : {}", id);
        Optional<OnvanKhodroDTO> onvanKhodroDTO = onvanKhodroService.findOne(id);
        return ResponseUtil.wrapOrNotFound(onvanKhodroDTO);
    }

    /**
     * DELETE  /onvan-khodros/:id : delete the "id" onvanKhodro.
     *
     * @param id the id of the onvanKhodroDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/onvan-khodros/{id}")
    public ResponseEntity<Void> deleteOnvanKhodro(@PathVariable Long id) {
        log.debug("REST request to delete OnvanKhodro : {}", id);
        onvanKhodroService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

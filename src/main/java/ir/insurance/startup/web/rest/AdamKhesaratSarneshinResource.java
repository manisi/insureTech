package ir.insurance.startup.web.rest;
import ir.insurance.startup.domain.AdamKhesaratSarneshin;
import ir.insurance.startup.security.AuthoritiesConstants;
import ir.insurance.startup.service.AdamKhesaratSarneshinService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.AdamKhesaratSarneshinDTO;
import ir.insurance.startup.service.dto.AdamKhesaratSarneshinCriteria;
import ir.insurance.startup.service.AdamKhesaratSarneshinQueryService;
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
 * REST controller for managing AdamKhesaratSarneshin.
 */
@RestController
@RequestMapping("/api")
public class AdamKhesaratSarneshinResource {

    private final Logger log = LoggerFactory.getLogger(AdamKhesaratSarneshinResource.class);

    private static final String ENTITY_NAME = "adamKhesaratSarneshin";

    private final AdamKhesaratSarneshinService adamKhesaratSarneshinService;

    private final AdamKhesaratSarneshinQueryService adamKhesaratSarneshinQueryService;

    public AdamKhesaratSarneshinResource(AdamKhesaratSarneshinService adamKhesaratSarneshinService, AdamKhesaratSarneshinQueryService adamKhesaratSarneshinQueryService) {
        this.adamKhesaratSarneshinService = adamKhesaratSarneshinService;
        this.adamKhesaratSarneshinQueryService = adamKhesaratSarneshinQueryService;
    }

    /**
     * POST  /adam-khesarat-sarneshins : Create a new adamKhesaratSarneshin.
     *
     * @param adamKhesaratSarneshinDTO the adamKhesaratSarneshinDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new adamKhesaratSarneshinDTO, or with status 400 (Bad Request) if the adamKhesaratSarneshin has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/adam-khesarat-sarneshins")
    public ResponseEntity<AdamKhesaratSarneshinDTO> createAdamKhesaratSarneshin(@Valid @RequestBody AdamKhesaratSarneshinDTO adamKhesaratSarneshinDTO) throws URISyntaxException {
        log.debug("REST request to save AdamKhesaratSarneshin : {}", adamKhesaratSarneshinDTO);
        if (adamKhesaratSarneshinDTO.getId() != null) {
            throw new BadRequestAlertException("A new adamKhesaratSarneshin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdamKhesaratSarneshinDTO result = adamKhesaratSarneshinService.save(adamKhesaratSarneshinDTO);
        return ResponseEntity.created(new URI("/api/adam-khesarat-sarneshins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /adam-khesarat-sarneshins : Updates an existing adamKhesaratSarneshin.
     *
     * @param adamKhesaratSarneshinDTO the adamKhesaratSarneshinDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated adamKhesaratSarneshinDTO,
     * or with status 400 (Bad Request) if the adamKhesaratSarneshinDTO is not valid,
     * or with status 500 (Internal Server Error) if the adamKhesaratSarneshinDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/adam-khesarat-sarneshins")
    public ResponseEntity<AdamKhesaratSarneshinDTO> updateAdamKhesaratSarneshin(@Valid @RequestBody AdamKhesaratSarneshinDTO adamKhesaratSarneshinDTO) throws URISyntaxException {
        log.debug("REST request to update AdamKhesaratSarneshin : {}", adamKhesaratSarneshinDTO);
        if (adamKhesaratSarneshinDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdamKhesaratSarneshinDTO result = adamKhesaratSarneshinService.save(adamKhesaratSarneshinDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, adamKhesaratSarneshinDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /adam-khesarat-sarneshins : get all the adamKhesaratSarneshins.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of adamKhesaratSarneshins in body
     */
    @GetMapping("/adam-khesarat-sarneshins")
    public ResponseEntity<List<AdamKhesaratSarneshinDTO>> getAllAdamKhesaratSarneshins(AdamKhesaratSarneshinCriteria criteria, Pageable pageable) {
        log.debug("REST request to get AdamKhesaratSarneshins by criteria: {}", criteria);
        Page<AdamKhesaratSarneshinDTO> page = adamKhesaratSarneshinQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/adam-khesarat-sarneshins");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/adam-khesarat-sarneshins-lookup")
    public ResponseEntity<List<KeyAndValueVM>> getAllAdamKhesarats() {
        log.debug("REST request to get adamkhesaratsarneshinsLookup");
        List<AdamKhesaratSarneshin> list = adamKhesaratSarneshinService.findAllforlookup();
        List<KeyAndValueVM> res = new ArrayList<>();
        for (AdamKhesaratSarneshin  row: list) {
            res.add(new KeyAndValueVM(row.getId().toString(),row.getSabeghe().getName()));
        }
        return ResponseEntity.ok().body(res);
    }

    /**
    * GET  /adam-khesarat-sarneshins/count : count all the adamKhesaratSarneshins.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/adam-khesarat-sarneshins/count")
    public ResponseEntity<Long> countAdamKhesaratSarneshins(AdamKhesaratSarneshinCriteria criteria) {
        log.debug("REST request to count AdamKhesaratSarneshins by criteria: {}", criteria);
        return ResponseEntity.ok().body(adamKhesaratSarneshinQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /adam-khesarat-sarneshins/:id : get the "id" adamKhesaratSarneshin.
     *
     * @param id the id of the adamKhesaratSarneshinDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the adamKhesaratSarneshinDTO, or with status 404 (Not Found)
     */
    @GetMapping("/adam-khesarat-sarneshins/{id}")
    public ResponseEntity<AdamKhesaratSarneshinDTO> getAdamKhesaratSarneshin(@PathVariable Long id) {
        log.debug("REST request to get AdamKhesaratSarneshin : {}", id);
        Optional<AdamKhesaratSarneshinDTO> adamKhesaratSarneshinDTO = adamKhesaratSarneshinService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adamKhesaratSarneshinDTO);
    }

    /**
     * DELETE  /adam-khesarat-sarneshins/:id : delete the "id" adamKhesaratSarneshin.
     *
     * @param id the id of the adamKhesaratSarneshinDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/adam-khesarat-sarneshins/{id}")
    public ResponseEntity<Void> deleteAdamKhesaratSarneshin(@PathVariable Long id) {
        log.debug("REST request to delete AdamKhesaratSarneshin : {}", id);
        adamKhesaratSarneshinService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

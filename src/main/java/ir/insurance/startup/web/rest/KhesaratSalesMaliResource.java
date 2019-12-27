package ir.insurance.startup.web.rest;
import ir.insurance.startup.domain.KhesaratSales;
import ir.insurance.startup.domain.KhesaratSalesMali;
import ir.insurance.startup.service.KhesaratSalesMaliService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.KhesaratSalesMaliDTO;
import io.github.jhipster.web.util.ResponseUtil;
import ir.insurance.startup.web.rest.vm.KeyAndValueVM;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing KhesaratSalesMali.
 */
@RestController
@RequestMapping("/api")
public class KhesaratSalesMaliResource {

    private final Logger log = LoggerFactory.getLogger(KhesaratSalesMaliResource.class);

    private static final String ENTITY_NAME = "khesaratSalesMali";

    private final KhesaratSalesMaliService khesaratSalesMaliService;

    public KhesaratSalesMaliResource(KhesaratSalesMaliService khesaratSalesMaliService) {
        this.khesaratSalesMaliService = khesaratSalesMaliService;
    }

    /**
     * POST  /khesarat-sales-malis : Create a new khesaratSalesMali.
     *
     * @param khesaratSalesMaliDTO the khesaratSalesMaliDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new khesaratSalesMaliDTO, or with status 400 (Bad Request) if the khesaratSalesMali has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/khesarat-sales-malis")
    public ResponseEntity<KhesaratSalesMaliDTO> createKhesaratSalesMali(@Valid @RequestBody KhesaratSalesMaliDTO khesaratSalesMaliDTO) throws URISyntaxException {
        log.debug("REST request to save KhesaratSalesMali : {}", khesaratSalesMaliDTO);
        if (khesaratSalesMaliDTO.getId() != null) {
            throw new BadRequestAlertException("A new khesaratSalesMali cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KhesaratSalesMaliDTO result = khesaratSalesMaliService.save(khesaratSalesMaliDTO);
        return ResponseEntity.created(new URI("/api/khesarat-sales-malis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /khesarat-sales-malis : Updates an existing khesaratSalesMali.
     *
     * @param khesaratSalesMaliDTO the khesaratSalesMaliDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated khesaratSalesMaliDTO,
     * or with status 400 (Bad Request) if the khesaratSalesMaliDTO is not valid,
     * or with status 500 (Internal Server Error) if the khesaratSalesMaliDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/khesarat-sales-malis")
    public ResponseEntity<KhesaratSalesMaliDTO> updateKhesaratSalesMali(@Valid @RequestBody KhesaratSalesMaliDTO khesaratSalesMaliDTO) throws URISyntaxException {
        log.debug("REST request to update KhesaratSalesMali : {}", khesaratSalesMaliDTO);
        if (khesaratSalesMaliDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KhesaratSalesMaliDTO result = khesaratSalesMaliService.save(khesaratSalesMaliDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, khesaratSalesMaliDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /khesarat-sales-malis : get all the khesaratSalesMalis.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of khesaratSalesMalis in body
     */
    @GetMapping("/khesarat-sales-malis")
    public ResponseEntity<List<KhesaratSalesMaliDTO>> getAllKhesaratSalesMalis(Pageable pageable) {
        log.debug("REST request to get a page of KhesaratSalesMalis");
        Page<KhesaratSalesMaliDTO> page = khesaratSalesMaliService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/khesarat-sales-malis");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/khesarat-sales-malis-lookup")
    public ResponseEntity<List<KeyAndValueVM>> getAllKhesaratSales() {
        log.debug("REST request to get KhesaratSalesmaliLookup");
        List<KhesaratSalesMali> list = khesaratSalesMaliService.findAllforlookup();
        List<KeyAndValueVM> res = new ArrayList<>();
        for (KhesaratSalesMali  row: list) {
            res.add(new KeyAndValueVM(row.getId().toString(),row.getSabeghe().getName()));
        }
        return ResponseEntity.ok().body(res);
    }

    /**
     * GET  /khesarat-sales-malis/:id : get the "id" khesaratSalesMali.
     *
     * @param id the id of the khesaratSalesMaliDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the khesaratSalesMaliDTO, or with status 404 (Not Found)
     */
    @GetMapping("/khesarat-sales-malis/{id}")
    public ResponseEntity<KhesaratSalesMaliDTO> getKhesaratSalesMali(@PathVariable Long id) {
        log.debug("REST request to get KhesaratSalesMali : {}", id);
        Optional<KhesaratSalesMaliDTO> khesaratSalesMaliDTO = khesaratSalesMaliService.findOne(id);
        return ResponseUtil.wrapOrNotFound(khesaratSalesMaliDTO);
    }

    /**
     * DELETE  /khesarat-sales-malis/:id : delete the "id" khesaratSalesMali.
     *
     * @param id the id of the khesaratSalesMaliDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/khesarat-sales-malis/{id}")
    public ResponseEntity<Void> deleteKhesaratSalesMali(@PathVariable Long id) {
        log.debug("REST request to delete KhesaratSalesMali : {}", id);
        khesaratSalesMaliService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

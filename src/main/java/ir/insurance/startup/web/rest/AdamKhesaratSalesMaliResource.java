package ir.insurance.startup.web.rest;
import ir.insurance.startup.service.AdamKhesaratSalesMaliService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.AdamKhesaratSalesMaliDTO;
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
 * REST controller for managing AdamKhesaratSalesMali.
 */
@RestController
@RequestMapping("/api")
public class AdamKhesaratSalesMaliResource {

    private final Logger log = LoggerFactory.getLogger(AdamKhesaratSalesMaliResource.class);

    private static final String ENTITY_NAME = "adamKhesaratSalesMali";

    private final AdamKhesaratSalesMaliService adamKhesaratSalesMaliService;

    public AdamKhesaratSalesMaliResource(AdamKhesaratSalesMaliService adamKhesaratSalesMaliService) {
        this.adamKhesaratSalesMaliService = adamKhesaratSalesMaliService;
    }

    /**
     * POST  /adam-khesarat-sales-malis : Create a new adamKhesaratSalesMali.
     *
     * @param adamKhesaratSalesMaliDTO the adamKhesaratSalesMaliDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new adamKhesaratSalesMaliDTO, or with status 400 (Bad Request) if the adamKhesaratSalesMali has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/adam-khesarat-sales-malis")
    public ResponseEntity<AdamKhesaratSalesMaliDTO> createAdamKhesaratSalesMali(@Valid @RequestBody AdamKhesaratSalesMaliDTO adamKhesaratSalesMaliDTO) throws URISyntaxException {
        log.debug("REST request to save AdamKhesaratSalesMali : {}", adamKhesaratSalesMaliDTO);
        if (adamKhesaratSalesMaliDTO.getId() != null) {
            throw new BadRequestAlertException("A new adamKhesaratSalesMali cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdamKhesaratSalesMaliDTO result = adamKhesaratSalesMaliService.save(adamKhesaratSalesMaliDTO);
        return ResponseEntity.created(new URI("/api/adam-khesarat-sales-malis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /adam-khesarat-sales-malis : Updates an existing adamKhesaratSalesMali.
     *
     * @param adamKhesaratSalesMaliDTO the adamKhesaratSalesMaliDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated adamKhesaratSalesMaliDTO,
     * or with status 400 (Bad Request) if the adamKhesaratSalesMaliDTO is not valid,
     * or with status 500 (Internal Server Error) if the adamKhesaratSalesMaliDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/adam-khesarat-sales-malis")
    public ResponseEntity<AdamKhesaratSalesMaliDTO> updateAdamKhesaratSalesMali(@Valid @RequestBody AdamKhesaratSalesMaliDTO adamKhesaratSalesMaliDTO) throws URISyntaxException {
        log.debug("REST request to update AdamKhesaratSalesMali : {}", adamKhesaratSalesMaliDTO);
        if (adamKhesaratSalesMaliDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdamKhesaratSalesMaliDTO result = adamKhesaratSalesMaliService.save(adamKhesaratSalesMaliDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, adamKhesaratSalesMaliDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /adam-khesarat-sales-malis : get all the adamKhesaratSalesMalis.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of adamKhesaratSalesMalis in body
     */
    @GetMapping("/adam-khesarat-sales-malis")
    public ResponseEntity<List<AdamKhesaratSalesMaliDTO>> getAllAdamKhesaratSalesMalis(Pageable pageable) {
        log.debug("REST request to get a page of AdamKhesaratSalesMalis");
        Page<AdamKhesaratSalesMaliDTO> page = adamKhesaratSalesMaliService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/adam-khesarat-sales-malis");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /adam-khesarat-sales-malis/:id : get the "id" adamKhesaratSalesMali.
     *
     * @param id the id of the adamKhesaratSalesMaliDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the adamKhesaratSalesMaliDTO, or with status 404 (Not Found)
     */
    @GetMapping("/adam-khesarat-sales-malis/{id}")
    public ResponseEntity<AdamKhesaratSalesMaliDTO> getAdamKhesaratSalesMali(@PathVariable Long id) {
        log.debug("REST request to get AdamKhesaratSalesMali : {}", id);
        Optional<AdamKhesaratSalesMaliDTO> adamKhesaratSalesMaliDTO = adamKhesaratSalesMaliService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adamKhesaratSalesMaliDTO);
    }

    /**
     * DELETE  /adam-khesarat-sales-malis/:id : delete the "id" adamKhesaratSalesMali.
     *
     * @param id the id of the adamKhesaratSalesMaliDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/adam-khesarat-sales-malis/{id}")
    public ResponseEntity<Void> deleteAdamKhesaratSalesMali(@PathVariable Long id) {
        log.debug("REST request to delete AdamKhesaratSalesMali : {}", id);
        adamKhesaratSalesMaliService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

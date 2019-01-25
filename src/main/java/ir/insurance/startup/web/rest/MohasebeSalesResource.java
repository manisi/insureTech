package ir.insurance.startup.web.rest;

import com.codahale.metrics.annotation.Timed;
import ir.insurance.startup.service.MohasebeSalesService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.MohasebeSalesDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MohasebeSales.
 */
@RestController
@RequestMapping("/api")
public class MohasebeSalesResource {

    private final Logger log = LoggerFactory.getLogger(MohasebeSalesResource.class);

    private static final String ENTITY_NAME = "mohasebeSales";

    private final MohasebeSalesService mohasebeSalesService;

    public MohasebeSalesResource(MohasebeSalesService mohasebeSalesService) {
        this.mohasebeSalesService = mohasebeSalesService;
    }

    /**
     * POST  /mohasebe-sales : Create a new mohasebeSales.
     *
     * @param mohasebeSalesDTO the mohasebeSalesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mohasebeSalesDTO, or with status 400 (Bad Request) if the mohasebeSales has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/mohasebe-sales")
    @Timed
    public ResponseEntity<MohasebeSalesDTO> createMohasebeSales(@RequestBody MohasebeSalesDTO mohasebeSalesDTO) throws URISyntaxException {
        log.debug("REST request to save MohasebeSales : {}", mohasebeSalesDTO);
        if (mohasebeSalesDTO.getId() != null) {
            throw new BadRequestAlertException("A new mohasebeSales cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MohasebeSalesDTO result = mohasebeSalesService.save(mohasebeSalesDTO);
        return ResponseEntity.created(new URI("/api/mohasebe-sales/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mohasebe-sales : Updates an existing mohasebeSales.
     *
     * @param mohasebeSalesDTO the mohasebeSalesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mohasebeSalesDTO,
     * or with status 400 (Bad Request) if the mohasebeSalesDTO is not valid,
     * or with status 500 (Internal Server Error) if the mohasebeSalesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/mohasebe-sales")
    @Timed
    public ResponseEntity<MohasebeSalesDTO> updateMohasebeSales(@RequestBody MohasebeSalesDTO mohasebeSalesDTO) throws URISyntaxException {
        log.debug("REST request to update MohasebeSales : {}", mohasebeSalesDTO);
        if (mohasebeSalesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MohasebeSalesDTO result = mohasebeSalesService.save(mohasebeSalesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mohasebeSalesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mohasebe-sales : get all the mohasebeSales.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of mohasebeSales in body
     */
    @GetMapping("/mohasebe-sales")
    @Timed
    public ResponseEntity<List<MohasebeSalesDTO>> getAllMohasebeSales(Pageable pageable) {
        log.debug("REST request to get a page of MohasebeSales");
        Page<MohasebeSalesDTO> page = mohasebeSalesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mohasebe-sales");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /mohasebe-sales/:id : get the "id" mohasebeSales.
     *
     * @param id the id of the mohasebeSalesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mohasebeSalesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/mohasebe-sales/{id}")
    @Timed
    public ResponseEntity<MohasebeSalesDTO> getMohasebeSales(@PathVariable Long id) {
        log.debug("REST request to get MohasebeSales : {}", id);
        Optional<MohasebeSalesDTO> mohasebeSalesDTO = mohasebeSalesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mohasebeSalesDTO);
    }

    /**
     * DELETE  /mohasebe-sales/:id : delete the "id" mohasebeSales.
     *
     * @param id the id of the mohasebeSalesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/mohasebe-sales/{id}")
    @Timed
    public ResponseEntity<Void> deleteMohasebeSales(@PathVariable Long id) {
        log.debug("REST request to delete MohasebeSales : {}", id);
        mohasebeSalesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

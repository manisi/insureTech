package ir.insurance.startup.web.rest;
import ir.insurance.startup.domain.MoredEstefadeSales;
import ir.insurance.startup.service.MoredEstefadeSalesService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.MoredEstefadeSalesDTO;
import ir.insurance.startup.service.dto.MoredEstefadeSalesCriteria;
import ir.insurance.startup.service.MoredEstefadeSalesQueryService;
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
 * REST controller for managing MoredEstefadeSales.
 */
@RestController
@RequestMapping("/api")
public class MoredEstefadeSalesResource {

    private final Logger log = LoggerFactory.getLogger(MoredEstefadeSalesResource.class);

    private static final String ENTITY_NAME = "moredEstefadeSales";

    private final MoredEstefadeSalesService moredEstefadeSalesService;

    private final MoredEstefadeSalesQueryService moredEstefadeSalesQueryService;

    public MoredEstefadeSalesResource(MoredEstefadeSalesService moredEstefadeSalesService, MoredEstefadeSalesQueryService moredEstefadeSalesQueryService) {
        this.moredEstefadeSalesService = moredEstefadeSalesService;
        this.moredEstefadeSalesQueryService = moredEstefadeSalesQueryService;
    }

    /**
     * POST  /mored-estefade-sales : Create a new moredEstefadeSales.
     *
     * @param moredEstefadeSalesDTO the moredEstefadeSalesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new moredEstefadeSalesDTO, or with status 400 (Bad Request) if the moredEstefadeSales has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/mored-estefade-sales")
    public ResponseEntity<MoredEstefadeSalesDTO> createMoredEstefadeSales(@Valid @RequestBody MoredEstefadeSalesDTO moredEstefadeSalesDTO) throws URISyntaxException {
        log.debug("REST request to save MoredEstefadeSales : {}", moredEstefadeSalesDTO);
        if (moredEstefadeSalesDTO.getId() != null) {
            throw new BadRequestAlertException("A new moredEstefadeSales cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MoredEstefadeSalesDTO result = moredEstefadeSalesService.save(moredEstefadeSalesDTO);
        return ResponseEntity.created(new URI("/api/mored-estefade-sales/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mored-estefade-sales : Updates an existing moredEstefadeSales.
     *
     * @param moredEstefadeSalesDTO the moredEstefadeSalesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated moredEstefadeSalesDTO,
     * or with status 400 (Bad Request) if the moredEstefadeSalesDTO is not valid,
     * or with status 500 (Internal Server Error) if the moredEstefadeSalesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/mored-estefade-sales")
    public ResponseEntity<MoredEstefadeSalesDTO> updateMoredEstefadeSales(@Valid @RequestBody MoredEstefadeSalesDTO moredEstefadeSalesDTO) throws URISyntaxException {
        log.debug("REST request to update MoredEstefadeSales : {}", moredEstefadeSalesDTO);
        if (moredEstefadeSalesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MoredEstefadeSalesDTO result = moredEstefadeSalesService.save(moredEstefadeSalesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, moredEstefadeSalesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mored-estefade-sales : get all the moredEstefadeSales.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of moredEstefadeSales in body
     */
    @GetMapping("/mored-estefade-sales")
    public ResponseEntity<List<MoredEstefadeSalesDTO>> getAllMoredEstefadeSales(MoredEstefadeSalesCriteria criteria, Pageable pageable) {
        log.debug("REST request to get MoredEstefadeSales by criteria: {}", criteria);
        Page<MoredEstefadeSalesDTO> page = moredEstefadeSalesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mored-estefade-sales");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    /**
    * GET  /mored-estefade-sales/count : count all the moredEstefadeSales.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/mored-estefade-sales/count")
    public ResponseEntity<Long> countMoredEstefadeSales(MoredEstefadeSalesCriteria criteria) {
        log.debug("REST request to count MoredEstefadeSales by criteria: {}", criteria);
        return ResponseEntity.ok().body(moredEstefadeSalesQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /mored-estefade-sales/:id : get the "id" moredEstefadeSales.
     *
     * @param id the id of the moredEstefadeSalesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the moredEstefadeSalesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/mored-estefade-sales/{id}")
    public ResponseEntity<MoredEstefadeSalesDTO> getMoredEstefadeSales(@PathVariable Long id) {
        log.debug("REST request to get MoredEstefadeSales : {}", id);
        Optional<MoredEstefadeSalesDTO> moredEstefadeSalesDTO = moredEstefadeSalesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(moredEstefadeSalesDTO);
    }

    /**
     * DELETE  /mored-estefade-sales/:id : delete the "id" moredEstefadeSales.
     *
     * @param id the id of the moredEstefadeSalesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/mored-estefade-sales/{id}")
    public ResponseEntity<Void> deleteMoredEstefadeSales(@PathVariable Long id) {
        log.debug("REST request to delete MoredEstefadeSales : {}", id);
        moredEstefadeSalesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

package ir.insurance.startup.web.rest;
import ir.insurance.startup.service.GroupOperationsDataService;
import ir.insurance.startup.web.rest.errors.BadRequestAlertException;
import ir.insurance.startup.web.rest.util.HeaderUtil;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.service.dto.GroupOperationsDataDTO;
import ir.insurance.startup.service.dto.GroupOperationsDataCriteria;
import ir.insurance.startup.service.GroupOperationsDataQueryService;
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
 * REST controller for managing GroupOperationsData.
 */
@RestController
@RequestMapping("/api")
public class GroupOperationsDataResource {

    private final Logger log = LoggerFactory.getLogger(GroupOperationsDataResource.class);

    private static final String ENTITY_NAME = "groupOperationsData";

    private final GroupOperationsDataService groupOperationsDataService;

    private final GroupOperationsDataQueryService groupOperationsDataQueryService;

    public GroupOperationsDataResource(GroupOperationsDataService groupOperationsDataService, GroupOperationsDataQueryService groupOperationsDataQueryService) {
        this.groupOperationsDataService = groupOperationsDataService;
        this.groupOperationsDataQueryService = groupOperationsDataQueryService;
    }

    /**
     * POST  /group-operations-data : Create a new groupOperationsData.
     *
     * @param groupOperationsDataDTO the groupOperationsDataDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new groupOperationsDataDTO, or with status 400 (Bad Request) if the groupOperationsData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/group-operations-data")
    public ResponseEntity<GroupOperationsDataDTO> createGroupOperationsData(@Valid @RequestBody GroupOperationsDataDTO groupOperationsDataDTO) throws URISyntaxException {
        log.debug("REST request to save GroupOperationsData : {}", groupOperationsDataDTO);
        if (groupOperationsDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new groupOperationsData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GroupOperationsDataDTO result = groupOperationsDataService.save(groupOperationsDataDTO);
        return ResponseEntity.created(new URI("/api/group-operations-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /group-operations-data : Updates an existing groupOperationsData.
     *
     * @param groupOperationsDataDTO the groupOperationsDataDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated groupOperationsDataDTO,
     * or with status 400 (Bad Request) if the groupOperationsDataDTO is not valid,
     * or with status 500 (Internal Server Error) if the groupOperationsDataDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/group-operations-data")
    public ResponseEntity<GroupOperationsDataDTO> updateGroupOperationsData(@Valid @RequestBody GroupOperationsDataDTO groupOperationsDataDTO) throws URISyntaxException {
        log.debug("REST request to update GroupOperationsData : {}", groupOperationsDataDTO);
        if (groupOperationsDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GroupOperationsDataDTO result = groupOperationsDataService.save(groupOperationsDataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, groupOperationsDataDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /group-operations-data : get all the groupOperationsData.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of groupOperationsData in body
     */
    @GetMapping("/group-operations-data")
    public ResponseEntity<List<GroupOperationsDataDTO>> getAllGroupOperationsData(GroupOperationsDataCriteria criteria, Pageable pageable) {
        log.debug("REST request to get GroupOperationsData by criteria: {}", criteria);
        Page<GroupOperationsDataDTO> page = groupOperationsDataQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/group-operations-data");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /group-operations-data/count : count all the groupOperationsData.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/group-operations-data/count")
    public ResponseEntity<Long> countGroupOperationsData(GroupOperationsDataCriteria criteria) {
        log.debug("REST request to count GroupOperationsData by criteria: {}", criteria);
        return ResponseEntity.ok().body(groupOperationsDataQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /group-operations-data/:id : get the "id" groupOperationsData.
     *
     * @param id the id of the groupOperationsDataDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the groupOperationsDataDTO, or with status 404 (Not Found)
     */
    @GetMapping("/group-operations-data/{id}")
    public ResponseEntity<GroupOperationsDataDTO> getGroupOperationsData(@PathVariable Long id) {
        log.debug("REST request to get GroupOperationsData : {}", id);
        Optional<GroupOperationsDataDTO> groupOperationsDataDTO = groupOperationsDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(groupOperationsDataDTO);
    }

    /**
     * DELETE  /group-operations-data/:id : delete the "id" groupOperationsData.
     *
     * @param id the id of the groupOperationsDataDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/group-operations-data/{id}")
    public ResponseEntity<Void> deleteGroupOperationsData(@PathVariable Long id) {
        log.debug("REST request to delete GroupOperationsData : {}", id);
        groupOperationsDataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

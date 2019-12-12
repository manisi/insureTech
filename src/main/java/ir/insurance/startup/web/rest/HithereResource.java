package ir.insurance.startup.web.rest;

import io.github.jhipster.web.util.ResponseUtil;
import ir.insurance.startup.service.AuditEventService;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

/**
 * REST controller for getting the audit events.
 */
@RestController
@RequestMapping("/api/hi-there")
public class HithereResource {

    private final AuditEventService auditEventService;

    public HithereResource(AuditEventService auditEventService) {
        this.auditEventService = auditEventService;
    }
    private final Logger log = LoggerFactory.getLogger(HithereResource.class);

//    @GetMapping
//    public ResponseEntity<List<AuditEvent>> getAll(Pageable pageable) {
//        Page<AuditEvent> page = auditEventService.findAll(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/hi-there");
//        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
//    }

    @GetMapping(params = {"anvaeKhodro", "saalSakht","onvanKhodro","adamKhesarat","adamKhesaratSarneshin","khesaratSrneshin","khesaratSales"})
    public ResponseEntity<List<AuditEvent>> getByDates(
        @RequestParam(value = "anvaeKhodro") String anvaeKhodro,
        @RequestParam(value = "saalSakht") String saalSakht,
        @RequestParam(value = "onvanKhodro") String onvanKhodro,
        @RequestParam(value = "adamKhesarat") String adamKhesarat,
        @RequestParam(value = "adamKhesaratSarneshin") String adamKhesaratSarneshin,
        @RequestParam(value = "khesaratSrneshin") String khesaratSrneshin,
        @RequestParam(value = "khesaratSales") String khesaratSales,
        Pageable pageable) {
log.debug("**************************************************************************"+anvaeKhodro);
        Page<AuditEvent> page =auditEventService.findAll(pageable);
//            auditEventService.findByDates(
//            fromDate.atStartOfDay(ZoneId.systemDefault()).toInstant(),
//            toDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1).toInstant(),
//            pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/hi-there");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
       // return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    @GetMapping("/{id:.+}")
    public ResponseEntity<AuditEvent> get(@PathVariable Long id) {
        return ResponseUtil.wrapOrNotFound(auditEventService.find(id));
    }
}

package ir.insurance.startup.web.rest;

import io.github.jhipster.web.util.ResponseUtil;
import ir.insurance.startup.service.AuditEventService;
import ir.insurance.startup.web.rest.util.PaginationUtil;
import ir.insurance.startup.web.rest.vm.EstelamSalesNerkhVM;
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
import java.util.Date;
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


    @GetMapping(params = {"anvaeKhodro", "saalSakht","vaziatBime","onvanKhodro","adamKhesarat","adamKhesaratSarneshin","khesaratSrneshin","khesaratSales",
        "khesaratSalesmali","sherkatBime","tarikhEtebar","codeyekta","modateBimename","sabegheKhesarat"})
    public ResponseEntity<List<EstelamSalesNerkhVM>> getByDates(
        @RequestParam(value = "anvaeKhodro") String anvaeKhodro,
        @RequestParam(value = "saalSakht") String saalSakht,
        @RequestParam(value = "vaziatBime") String vaziatBime,
        @RequestParam(value = "onvanKhodro") String onvanKhodro,
        @RequestParam(value = "adamKhesarat") String adamKhesarat,
        @RequestParam(value = "adamKhesaratSarneshin") String adamKhesaratSarneshin,
        @RequestParam(value = "khesaratSrneshin") String khesaratSrneshin,
        @RequestParam(value = "khesaratSales") String khesaratSales,
        @RequestParam(value = "khesaratSalesmali") String khesaratSalesmali,
        @RequestParam(value = "sherkatBime") String sherkatBime,
        @RequestParam(value = "tarikhEtebar") String tarikhEtebar,
        @RequestParam(value = "codeyekta") String codeyekta,
        @RequestParam(value = "modateBimename") String modateBimename,
        @RequestParam(value = "sabegheKhesarat") String sabegheKhesarat,
        Pageable pageable) {
log.debug("**************************************************************************"+tarikhEtebar.toString());
        Page<EstelamSalesNerkhVM> page =null;//auditEventService.findAll(pageable);
//            auditEventService.findByDates(
//            fromDate.atStartOfDay(ZoneId.systemDefault()).toInstant(),
//            toDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1).toInstant(),
//            pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/hi-there");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
       // return ResponseEntity.ok().headers(headers).body(page.getContent());
    }



//    var cities = new ArrayList<EstelamSalesNerkhVM>();
//
//        cities.add(new City(1L, "Bratislava", 432000));
//        cities.add(new City(2L, "Budapest", 1759000));
//        cities.add(new City(3L, "Prague", 1280000));
//        cities.add(new City(4L, "Warsaw", 1748000));
//        cities.add(new City(5L, "Los Angeles", 3971000));
//        cities.add(new City(6L, "New York", 8550000));
//        cities.add(new City(7L, "Edinburgh", 464000));
//        cities.add(new City(8L, "Berlin", 3671000));
//
//        return cities;
//}




    @GetMapping("/{id:.+}")
    public ResponseEntity<AuditEvent> get(@PathVariable Long id) {
        return ResponseUtil.wrapOrNotFound(auditEventService.find(id));
    }
}

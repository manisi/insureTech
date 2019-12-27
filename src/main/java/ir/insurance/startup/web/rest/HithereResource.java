package ir.insurance.startup.web.rest;

import ir.insurance.startup.domain.AnvaeKhodro;
import ir.insurance.startup.domain.SalesJaniCalc;
import ir.insurance.startup.domain.SalesSarneshinCalc;
import ir.insurance.startup.repository.SalesJaniCalcRepository;
import ir.insurance.startup.service.*;
import ir.insurance.startup.service.dto.SalesJaniCalcDTO;
import ir.insurance.startup.service.dto.SalesSarneshinCalcDTO;
import ir.insurance.startup.web.rest.vm.EstelamSalesNerkhVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for getting the audit events.
 */
@RestController
@RequestMapping("/api/hi-there")
public class HithereResource {

    private final KhesaratSalesService khesaratSalesService;
    private final AnvaeKhodroService anvaeKhodroService;
    private final GrouhKhodroService grouhKhodroService;
    private final SalesJaniCalcService salesJaniCalcService;
    private final SalesSarneshinCalcService salesSarneshinCalcService;

    public HithereResource(KhesaratSalesService khesaratSalesService, AnvaeKhodroService anvaeKhodroService, GrouhKhodroService grouhKhodroService, MohasebeSalesService mohasebeSalesService, SalesJaniCalcService salesJaniCalcService, SalesSarneshinCalcService salesSarneshinCalcService, SalesJaniCalcRepository salesJaniCalcRepository) {
        this.khesaratSalesService = khesaratSalesService;
        this.anvaeKhodroService = anvaeKhodroService;
        this.grouhKhodroService = grouhKhodroService;
        this.salesJaniCalcService = salesJaniCalcService;
        this.salesSarneshinCalcService = salesSarneshinCalcService;
    }

    private final Logger log = LoggerFactory.getLogger(HithereResource.class);

//    @GetMapping
//    public ResponseEntity<List<AuditEvent>> getAll(Pageable pageable) {
//        Page<AuditEvent> page = auditEventService.findAll(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/hi-there");
//        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
//    }


    @GetMapping(params = {"anvaeKhodro", "saalSakht", "vaziatBime", "onvanKhodro", "adamKhesarat", "adamKhesaratSarneshin", "khesaratSrneshin", "khesaratSales",
        "khesaratSalesmali", "sherkatBime", "tarikhEtebar", "codeyekta", "modateBimename", "sabegheKhesarat"})
    public ResponseEntity<List<EstelamSalesNerkhVM>> getByParams(
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
        @RequestParam(value = "sabegheKhesarat") String sabegheKhesarat) {
        log.debug("**************************************************************************" + tarikhEtebar.toString());
        List<EstelamSalesNerkhVM> res = new ArrayList<>();
        Optional<AnvaeKhodro> khodro = anvaeKhodroService.findById(Long.valueOf(anvaeKhodro));

        if(khodro.isPresent()){
            final Long id = khodro.get().getGrouhKhodro().getId();
            List<SalesJaniCalc> all = salesJaniCalcService.findAllByGrouhKhodroId(id);
            List<SalesSarneshinCalc> allSarneshin = salesSarneshinCalcService.findAllByGrouhKhodroId(id);

            EstelamSalesNerkhVM estelam = null;
            for (SalesJaniCalc sj: all) {
                 estelam=new EstelamSalesNerkhVM();
                estelam.setMablaghJani( sj.getMablaghJani().toString());
                estelam.setMablaghMali( sj.getMablaghMaliEjbari().toString());
                 estelam.setTedadRooz(sj.getTedadRooz().toString());
                 estelam.setNaamSherkat(sj.getBimename().getName());
                estelam.setHaghbimeSalesJani(String.valueOf(sj.getHaghbime().intValue()));
                res.add(estelam);
            }
//            for (SalesSarneshinCalc ss: allSarneshin) {
//                estelam.setHaghbimeSalesSarneshin(ss.getMablaghHaghBime().toString());
//                res.add(estelam);
//            }

        }

        return ResponseEntity.ok().body(res);
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


//    @GetMapping("/{id:.+}")
//    public ResponseEntity<AuditEvent> get(@PathVariable Long id) {
//        return ResponseUtil.wrapOrNotFound(auditEventService.find(id));
//    }
}

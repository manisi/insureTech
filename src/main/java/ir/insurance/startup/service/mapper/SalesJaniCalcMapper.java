package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.SalesJaniCalcDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SalesJaniCalc and its DTO SalesJaniCalcDTO.
 */
@Mapper(componentModel = "spring", uses = {SherkatBimeMapper.class, GrouhKhodroMapper.class})
public interface SalesJaniCalcMapper extends EntityMapper<SalesJaniCalcDTO, SalesJaniCalc> {

    @Mapping(source = "bimename.id", target = "bimenameId")
    @Mapping(source = "bimename.name", target = "bimenameName")
    @Mapping(source = "grouhKhodro.id", target = "grouhKhodroId")
    @Mapping(source = "grouhKhodro.name", target = "grouhKhodroName")
    SalesJaniCalcDTO toDto(SalesJaniCalc salesJaniCalc);

    @Mapping(source = "bimenameId", target = "bimename")
    @Mapping(source = "grouhKhodroId", target = "grouhKhodro")
    SalesJaniCalc toEntity(SalesJaniCalcDTO salesJaniCalcDTO);

    default SalesJaniCalc fromId(Long id) {
        if (id == null) {
            return null;
        }
        SalesJaniCalc salesJaniCalc = new SalesJaniCalc();
        salesJaniCalc.setId(id);
        return salesJaniCalc;
    }
}

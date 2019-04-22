package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.SalesMazadCalcDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SalesMazadCalc and its DTO SalesMazadCalcDTO.
 */
@Mapper(componentModel = "spring", uses = {SherkatBimeMapper.class, GrouhKhodroMapper.class})
public interface SalesMazadCalcMapper extends EntityMapper<SalesMazadCalcDTO, SalesMazadCalc> {

    @Mapping(source = "namesherkat.id", target = "namesherkatId")
    @Mapping(source = "namesherkat.name", target = "namesherkatName")
    @Mapping(source = "grouhKhodro.id", target = "grouhKhodroId")
    @Mapping(source = "grouhKhodro.name", target = "grouhKhodroName")
    SalesMazadCalcDTO toDto(SalesMazadCalc salesMazadCalc);

    @Mapping(source = "namesherkatId", target = "namesherkat")
    @Mapping(source = "grouhKhodroId", target = "grouhKhodro")
    SalesMazadCalc toEntity(SalesMazadCalcDTO salesMazadCalcDTO);

    default SalesMazadCalc fromId(Long id) {
        if (id == null) {
            return null;
        }
        SalesMazadCalc salesMazadCalc = new SalesMazadCalc();
        salesMazadCalc.setId(id);
        return salesMazadCalc;
    }
}

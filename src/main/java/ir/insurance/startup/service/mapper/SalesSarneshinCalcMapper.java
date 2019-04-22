package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.SalesSarneshinCalcDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SalesSarneshinCalc and its DTO SalesSarneshinCalcDTO.
 */
@Mapper(componentModel = "spring", uses = {SherkatBimeMapper.class, GrouhKhodroMapper.class})
public interface SalesSarneshinCalcMapper extends EntityMapper<SalesSarneshinCalcDTO, SalesSarneshinCalc> {

    @Mapping(source = "namesherkat.id", target = "namesherkatId")
    @Mapping(source = "namesherkat.name", target = "namesherkatName")
    @Mapping(source = "grouhKhodro.id", target = "grouhKhodroId")
    @Mapping(source = "grouhKhodro.name", target = "grouhKhodroName")
    SalesSarneshinCalcDTO toDto(SalesSarneshinCalc salesSarneshinCalc);

    @Mapping(source = "namesherkatId", target = "namesherkat")
    @Mapping(source = "grouhKhodroId", target = "grouhKhodro")
    SalesSarneshinCalc toEntity(SalesSarneshinCalcDTO salesSarneshinCalcDTO);

    default SalesSarneshinCalc fromId(Long id) {
        if (id == null) {
            return null;
        }
        SalesSarneshinCalc salesSarneshinCalc = new SalesSarneshinCalc();
        salesSarneshinCalc.setId(id);
        return salesSarneshinCalc;
    }
}

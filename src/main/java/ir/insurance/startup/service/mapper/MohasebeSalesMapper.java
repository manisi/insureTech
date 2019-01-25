package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.MohasebeSalesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MohasebeSales and its DTO MohasebeSalesDTO.
 */
@Mapper(componentModel = "spring", uses = {TipKhodroMapper.class})
public interface MohasebeSalesMapper extends EntityMapper<MohasebeSalesDTO, MohasebeSales> {

    @Mapping(source = "tips.id", target = "tipsId")
    MohasebeSalesDTO toDto(MohasebeSales mohasebeSales);

    @Mapping(source = "tipsId", target = "tips")
    MohasebeSales toEntity(MohasebeSalesDTO mohasebeSalesDTO);

    default MohasebeSales fromId(Long id) {
        if (id == null) {
            return null;
        }
        MohasebeSales mohasebeSales = new MohasebeSales();
        mohasebeSales.setId(id);
        return mohasebeSales;
    }
}

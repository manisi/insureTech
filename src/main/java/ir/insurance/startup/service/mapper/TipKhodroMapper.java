package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.TipKhodroDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TipKhodro and its DTO TipKhodroDTO.
 */
@Mapper(componentModel = "spring", uses = {KhodroMapper.class})
public interface TipKhodroMapper extends EntityMapper<TipKhodroDTO, TipKhodro> {

    @Mapping(source = "khodro.id", target = "khodroId")
    TipKhodroDTO toDto(TipKhodro tipKhodro);

    @Mapping(source = "khodroId", target = "khodro")
    TipKhodro toEntity(TipKhodroDTO tipKhodroDTO);

    default TipKhodro fromId(Long id) {
        if (id == null) {
            return null;
        }
        TipKhodro tipKhodro = new TipKhodro();
        tipKhodro.setId(id);
        return tipKhodro;
    }
}

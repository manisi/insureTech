package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.AnvaeKhodroDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AnvaeKhodro and its DTO AnvaeKhodroDTO.
 */
@Mapper(componentModel = "spring", uses = {GrouhKhodroMapper.class})
public interface AnvaeKhodroMapper extends EntityMapper<AnvaeKhodroDTO, AnvaeKhodro> {

    @Mapping(source = "grouhKhodro.id", target = "grouhKhodroId")
    @Mapping(source = "grouhKhodro.code", target = "grouhKhodroCode")
    @Mapping(source = "grouhKhodro.name", target = "grouhKhodroName")
    AnvaeKhodroDTO toDto(AnvaeKhodro anvaeKhodro);

    @Mapping(source = "grouhKhodroId", target = "grouhKhodro")
    AnvaeKhodro toEntity(AnvaeKhodroDTO anvaeKhodroDTO);

    default AnvaeKhodro fromId(Long id) {
        if (id == null) {
            return null;
        }
        AnvaeKhodro anvaeKhodro = new AnvaeKhodro();
        anvaeKhodro.setId(id);
        return anvaeKhodro;
    }
}

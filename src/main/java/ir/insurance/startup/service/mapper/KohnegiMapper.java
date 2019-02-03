package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.KohnegiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Kohnegi and its DTO KohnegiDTO.
 */
@Mapper(componentModel = "spring", uses = {GrouhKhodroMapper.class})
public interface KohnegiMapper extends EntityMapper<KohnegiDTO, Kohnegi> {

    @Mapping(source = "grouhKhodro.id", target = "grouhKhodroId")
    @Mapping(source = "grouhKhodro.code", target = "grouhKhodroCode")
    KohnegiDTO toDto(Kohnegi kohnegi);

    @Mapping(source = "grouhKhodroId", target = "grouhKhodro")
    Kohnegi toEntity(KohnegiDTO kohnegiDTO);

    default Kohnegi fromId(Long id) {
        if (id == null) {
            return null;
        }
        Kohnegi kohnegi = new Kohnegi();
        kohnegi.setId(id);
        return kohnegi;
    }
}

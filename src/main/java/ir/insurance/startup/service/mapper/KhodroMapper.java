package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.KhodroDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Khodro and its DTO KhodroDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface KhodroMapper extends EntityMapper<KhodroDTO, Khodro> {


    @Mapping(target = "tips", ignore = true)
    Khodro toEntity(KhodroDTO khodroDTO);

    default Khodro fromId(Long id) {
        if (id == null) {
            return null;
        }
        Khodro khodro = new Khodro();
        khodro.setId(id);
        return khodro;
    }
}

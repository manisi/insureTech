package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.KohnegiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Kohnegi and its DTO KohnegiDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface KohnegiMapper extends EntityMapper<KohnegiDTO, Kohnegi> {



    default Kohnegi fromId(Long id) {
        if (id == null) {
            return null;
        }
        Kohnegi kohnegi = new Kohnegi();
        kohnegi.setId(id);
        return kohnegi;
    }
}

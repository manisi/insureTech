package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.GrouhKhodroDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity GrouhKhodro and its DTO GrouhKhodroDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GrouhKhodroMapper extends EntityMapper<GrouhKhodroDTO, GrouhKhodro> {



    default GrouhKhodro fromId(Long id) {
        if (id == null) {
            return null;
        }
        GrouhKhodro grouhKhodro = new GrouhKhodro();
        grouhKhodro.setId(id);
        return grouhKhodro;
    }
}

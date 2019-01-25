package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.AshkhasDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Ashkhas and its DTO AshkhasDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AshkhasMapper extends EntityMapper<AshkhasDTO, Ashkhas> {



    default Ashkhas fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ashkhas ashkhas = new Ashkhas();
        ashkhas.setId(id);
        return ashkhas;
    }
}

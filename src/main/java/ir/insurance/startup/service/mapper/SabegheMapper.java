package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.SabegheDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Sabeghe and its DTO SabegheDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SabegheMapper extends EntityMapper<SabegheDTO, Sabeghe> {



    default Sabeghe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Sabeghe sabeghe = new Sabeghe();
        sabeghe.setId(id);
        return sabeghe;
    }
}

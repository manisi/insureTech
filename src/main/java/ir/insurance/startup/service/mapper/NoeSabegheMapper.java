package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.NoeSabegheDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NoeSabeghe and its DTO NoeSabegheDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NoeSabegheMapper extends EntityMapper<NoeSabegheDTO, NoeSabeghe> {



    default NoeSabeghe fromId(Long id) {
        if (id == null) {
            return null;
        }
        NoeSabeghe noeSabeghe = new NoeSabeghe();
        noeSabeghe.setId(id);
        return noeSabeghe;
    }
}

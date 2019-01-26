package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.KohnegiBadaneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity KohnegiBadane and its DTO KohnegiBadaneDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface KohnegiBadaneMapper extends EntityMapper<KohnegiBadaneDTO, KohnegiBadane> {



    default KohnegiBadane fromId(Long id) {
        if (id == null) {
            return null;
        }
        KohnegiBadane kohnegiBadane = new KohnegiBadane();
        kohnegiBadane.setId(id);
        return kohnegiBadane;
    }
}

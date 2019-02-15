package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.KohnegiBadaneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity KohnegiBadane and its DTO KohnegiBadaneDTO.
 */
@Mapper(componentModel = "spring", uses = {GrouhKhodroMapper.class})
public interface KohnegiBadaneMapper extends EntityMapper<KohnegiBadaneDTO, KohnegiBadane> {

    @Mapping(source = "grouhKhodro.id", target = "grouhKhodroId")
    @Mapping(source = "grouhKhodro.code", target = "grouhKhodroCode")
    @Mapping(source = "grouhKhodro.name", target = "grouhKhodroName")
    KohnegiBadaneDTO toDto(KohnegiBadane kohnegiBadane);

    @Mapping(source = "grouhKhodroId", target = "grouhKhodro")
    KohnegiBadane toEntity(KohnegiBadaneDTO kohnegiBadaneDTO);

    default KohnegiBadane fromId(Long id) {
        if (id == null) {
            return null;
        }
        KohnegiBadane kohnegiBadane = new KohnegiBadane();
        kohnegiBadane.setId(id);
        return kohnegiBadane;
    }
}

package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.KohnegiBadaneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity KohnegiBadane and its DTO KohnegiBadaneDTO.
 */
@Mapper(componentModel = "spring", uses = {GrouhKhodroMapper.class, SherkatBimeMapper.class})
public interface KohnegiBadaneMapper extends EntityMapper<KohnegiBadaneDTO, KohnegiBadane> {

    @Mapping(source = "grouhKhodro.id", target = "grouhKhodroId")
    @Mapping(source = "grouhKhodro.code", target = "grouhKhodroCode")
    @Mapping(source = "grouhKhodro.name", target = "grouhKhodroName")
    @Mapping(source = "sherkatBime.id", target = "sherkatBimeId")
    @Mapping(source = "sherkatBime.name", target = "sherkatBimeName")
    KohnegiBadaneDTO toDto(KohnegiBadane kohnegiBadane);

    @Mapping(source = "grouhKhodroId", target = "grouhKhodro")
    @Mapping(source = "sherkatBimeId", target = "sherkatBime")
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

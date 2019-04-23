package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.MoredEstefadeSalesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MoredEstefadeSales and its DTO MoredEstefadeSalesDTO.
 */
@Mapper(componentModel = "spring", uses = {GrouhKhodroMapper.class, SherkatBimeMapper.class})
public interface MoredEstefadeSalesMapper extends EntityMapper<MoredEstefadeSalesDTO, MoredEstefadeSales> {

    @Mapping(source = "grouhKhodro.id", target = "grouhKhodroId")
    @Mapping(source = "grouhKhodro.name", target = "grouhKhodroName")
    @Mapping(source = "sherkatBime.id", target = "sherkatBimeId")
    @Mapping(source = "sherkatBime.name", target = "sherkatBimeName")
    MoredEstefadeSalesDTO toDto(MoredEstefadeSales moredEstefadeSales);

    @Mapping(source = "grouhKhodroId", target = "grouhKhodro")
    @Mapping(source = "sherkatBimeId", target = "sherkatBime")
    MoredEstefadeSales toEntity(MoredEstefadeSalesDTO moredEstefadeSalesDTO);

    default MoredEstefadeSales fromId(Long id) {
        if (id == null) {
            return null;
        }
        MoredEstefadeSales moredEstefadeSales = new MoredEstefadeSales();
        moredEstefadeSales.setId(id);
        return moredEstefadeSales;
    }
}

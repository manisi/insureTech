package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.NerkhDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Nerkh and its DTO NerkhDTO.
 */
@Mapper(componentModel = "spring", uses = {SherkatBimeMapper.class})
public interface NerkhMapper extends EntityMapper<NerkhDTO, Nerkh> {


    @Mapping(target = "nerkhs", ignore = true)
    Nerkh toEntity(NerkhDTO nerkhDTO);

    default Nerkh fromId(Long id) {
        if (id == null) {
            return null;
        }
        Nerkh nerkh = new Nerkh();
        nerkh.setId(id);
        return nerkh;
    }
}

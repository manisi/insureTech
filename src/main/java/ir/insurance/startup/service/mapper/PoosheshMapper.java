package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.PoosheshDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Pooshesh and its DTO PoosheshDTO.
 */
@Mapper(componentModel = "spring", uses = {NerkhMapper.class})
public interface PoosheshMapper extends EntityMapper<PoosheshDTO, Pooshesh> {

    @Mapping(source = "nerkh.id", target = "nerkhId")
    PoosheshDTO toDto(Pooshesh pooshesh);

    @Mapping(source = "nerkhId", target = "nerkh")
    Pooshesh toEntity(PoosheshDTO poosheshDTO);

    default Pooshesh fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pooshesh pooshesh = new Pooshesh();
        pooshesh.setId(id);
        return pooshesh;
    }
}

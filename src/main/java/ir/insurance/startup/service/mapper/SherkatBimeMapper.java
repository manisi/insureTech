package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.SherkatBimeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SherkatBime and its DTO SherkatBimeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SherkatBimeMapper extends EntityMapper<SherkatBimeDTO, SherkatBime> {


    @Mapping(target = "names", ignore = true)
    SherkatBime toEntity(SherkatBimeDTO sherkatBimeDTO);

    default SherkatBime fromId(Long id) {
        if (id == null) {
            return null;
        }
        SherkatBime sherkatBime = new SherkatBime();
        sherkatBime.setId(id);
        return sherkatBime;
    }
}

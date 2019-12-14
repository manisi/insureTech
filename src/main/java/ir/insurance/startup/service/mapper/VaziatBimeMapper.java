package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.VaziatBimeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity VaziatBime and its DTO VaziatBimeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface VaziatBimeMapper extends EntityMapper<VaziatBimeDTO, VaziatBime> {



    default VaziatBime fromId(Long id) {
        if (id == null) {
            return null;
        }
        VaziatBime vaziatBime = new VaziatBime();
        vaziatBime.setId(id);
        return vaziatBime;
    }
}

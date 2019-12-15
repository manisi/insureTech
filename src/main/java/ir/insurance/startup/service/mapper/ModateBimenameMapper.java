package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.ModateBimenameDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ModateBimename and its DTO ModateBimenameDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ModateBimenameMapper extends EntityMapper<ModateBimenameDTO, ModateBimename> {



    default ModateBimename fromId(Long id) {
        if (id == null) {
            return null;
        }
        ModateBimename modateBimename = new ModateBimename();
        modateBimename.setId(id);
        return modateBimename;
    }
}

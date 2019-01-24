package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.JarimeDirkardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity JarimeDirkard and its DTO JarimeDirkardDTO.
 */
@Mapper(componentModel = "spring", uses = {GrouhKhodroMapper.class})
public interface JarimeDirkardMapper extends EntityMapper<JarimeDirkardDTO, JarimeDirkard> {

    @Mapping(source = "grouhKhodro.id", target = "grouhKhodroId")
    @Mapping(source = "grouhKhodro.name", target = "grouhKhodroName")
    JarimeDirkardDTO toDto(JarimeDirkard jarimeDirkard);

    @Mapping(source = "grouhKhodroId", target = "grouhKhodro")
    JarimeDirkard toEntity(JarimeDirkardDTO jarimeDirkardDTO);

    default JarimeDirkard fromId(Long id) {
        if (id == null) {
            return null;
        }
        JarimeDirkard jarimeDirkard = new JarimeDirkard();
        jarimeDirkard.setId(id);
        return jarimeDirkard;
    }
}

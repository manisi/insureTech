package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.JarimeDirkardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity JarimeDirkard and its DTO JarimeDirkardDTO.
 */
@Mapper(componentModel = "spring", uses = {GrouhKhodroMapper.class, SherkatBimeMapper.class})
public interface JarimeDirkardMapper extends EntityMapper<JarimeDirkardDTO, JarimeDirkard> {

    @Mapping(source = "grouhKhodro.id", target = "grouhKhodroId")
    @Mapping(source = "grouhKhodro.name", target = "grouhKhodroName")
    @Mapping(source = "sherkatBime.id", target = "sherkatBimeId")
    @Mapping(source = "sherkatBime.name", target = "sherkatBimeName")
    JarimeDirkardDTO toDto(JarimeDirkard jarimeDirkard);

    @Mapping(source = "grouhKhodroId", target = "grouhKhodro")
    @Mapping(source = "sherkatBimeId", target = "sherkatBime")
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

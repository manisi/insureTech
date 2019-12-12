package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.SaalSakhtDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SaalSakht and its DTO SaalSakhtDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SaalSakhtMapper extends EntityMapper<SaalSakhtDTO, SaalSakht> {



    default SaalSakht fromId(Long id) {
        if (id == null) {
            return null;
        }
        SaalSakht saalSakht = new SaalSakht();
        saalSakht.setId(id);
        return saalSakht;
    }
}

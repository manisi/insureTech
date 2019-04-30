package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.OnvanKhodroDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OnvanKhodro and its DTO OnvanKhodroDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OnvanKhodroMapper extends EntityMapper<OnvanKhodroDTO, OnvanKhodro> {



    default OnvanKhodro fromId(Long id) {
        if (id == null) {
            return null;
        }
        OnvanKhodro onvanKhodro = new OnvanKhodro();
        onvanKhodro.setId(id);
        return onvanKhodro;
    }
}

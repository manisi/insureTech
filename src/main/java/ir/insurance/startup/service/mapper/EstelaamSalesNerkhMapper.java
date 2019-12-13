package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.EstelaamSalesNerkhDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EstelaamSalesNerkh and its DTO EstelaamSalesNerkhDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EstelaamSalesNerkhMapper extends EntityMapper<EstelaamSalesNerkhDTO, EstelaamSalesNerkh> {



    default EstelaamSalesNerkh fromId(Long id) {
        if (id == null) {
            return null;
        }
        EstelaamSalesNerkh estelaamSalesNerkh = new EstelaamSalesNerkh();
        estelaamSalesNerkh.setId(id);
        return estelaamSalesNerkh;
    }
}

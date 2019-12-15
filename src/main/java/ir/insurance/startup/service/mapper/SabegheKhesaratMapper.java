package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.SabegheKhesaratDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SabegheKhesarat and its DTO SabegheKhesaratDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SabegheKhesaratMapper extends EntityMapper<SabegheKhesaratDTO, SabegheKhesarat> {



    default SabegheKhesarat fromId(Long id) {
        if (id == null) {
            return null;
        }
        SabegheKhesarat sabegheKhesarat = new SabegheKhesarat();
        sabegheKhesarat.setId(id);
        return sabegheKhesarat;
    }
}

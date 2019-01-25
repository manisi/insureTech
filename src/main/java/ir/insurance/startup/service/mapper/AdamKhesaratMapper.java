package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.AdamKhesaratDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AdamKhesarat and its DTO AdamKhesaratDTO.
 */
@Mapper(componentModel = "spring", uses = {SabegheMapper.class, NoeSabegheMapper.class})
public interface AdamKhesaratMapper extends EntityMapper<AdamKhesaratDTO, AdamKhesarat> {

    @Mapping(source = "sabeghe.id", target = "sabegheId")
    @Mapping(source = "sabeghe.name", target = "sabegheName")
    @Mapping(source = "noeSabeghe.id", target = "noeSabegheId")
    @Mapping(source = "noeSabeghe.name", target = "noeSabegheName")
    AdamKhesaratDTO toDto(AdamKhesarat adamKhesarat);

    @Mapping(source = "sabegheId", target = "sabeghe")
    @Mapping(source = "noeSabegheId", target = "noeSabeghe")
    AdamKhesarat toEntity(AdamKhesaratDTO adamKhesaratDTO);

    default AdamKhesarat fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdamKhesarat adamKhesarat = new AdamKhesarat();
        adamKhesarat.setId(id);
        return adamKhesarat;
    }
}

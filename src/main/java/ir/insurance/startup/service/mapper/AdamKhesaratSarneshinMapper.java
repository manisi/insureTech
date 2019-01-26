package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.AdamKhesaratSarneshinDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AdamKhesaratSarneshin and its DTO AdamKhesaratSarneshinDTO.
 */
@Mapper(componentModel = "spring", uses = {NoeSabegheMapper.class, SabegheMapper.class})
public interface AdamKhesaratSarneshinMapper extends EntityMapper<AdamKhesaratSarneshinDTO, AdamKhesaratSarneshin> {

    @Mapping(source = "noeSabeghe.id", target = "noeSabegheId")
    @Mapping(source = "noeSabeghe.name", target = "noeSabegheName")
    @Mapping(source = "sabeghe.id", target = "sabegheId")
    @Mapping(source = "sabeghe.name", target = "sabegheName")
    AdamKhesaratSarneshinDTO toDto(AdamKhesaratSarneshin adamKhesaratSarneshin);

    @Mapping(source = "noeSabegheId", target = "noeSabeghe")
    @Mapping(source = "sabegheId", target = "sabeghe")
    AdamKhesaratSarneshin toEntity(AdamKhesaratSarneshinDTO adamKhesaratSarneshinDTO);

    default AdamKhesaratSarneshin fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdamKhesaratSarneshin adamKhesaratSarneshin = new AdamKhesaratSarneshin();
        adamKhesaratSarneshin.setId(id);
        return adamKhesaratSarneshin;
    }
}

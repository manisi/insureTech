package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.KhesaratSrneshinDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity KhesaratSrneshin and its DTO KhesaratSrneshinDTO.
 */
@Mapper(componentModel = "spring", uses = {NoeSabegheMapper.class, SabegheMapper.class})
public interface KhesaratSrneshinMapper extends EntityMapper<KhesaratSrneshinDTO, KhesaratSrneshin> {

    @Mapping(source = "noeSabeghe.id", target = "noeSabegheId")
    @Mapping(source = "noeSabeghe.name", target = "noeSabegheName")
    @Mapping(source = "sabeghe.id", target = "sabegheId")
    @Mapping(source = "sabeghe.name", target = "sabegheName")
    KhesaratSrneshinDTO toDto(KhesaratSrneshin khesaratSrneshin);

    @Mapping(source = "noeSabegheId", target = "noeSabeghe")
    @Mapping(source = "sabegheId", target = "sabeghe")
    KhesaratSrneshin toEntity(KhesaratSrneshinDTO khesaratSrneshinDTO);

    default KhesaratSrneshin fromId(Long id) {
        if (id == null) {
            return null;
        }
        KhesaratSrneshin khesaratSrneshin = new KhesaratSrneshin();
        khesaratSrneshin.setId(id);
        return khesaratSrneshin;
    }
}

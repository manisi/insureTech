package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.KhesaratSalesMaliDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity KhesaratSalesMali and its DTO KhesaratSalesMaliDTO.
 */
@Mapper(componentModel = "spring", uses = {SabegheMapper.class, NoeSabegheMapper.class})
public interface KhesaratSalesMaliMapper extends EntityMapper<KhesaratSalesMaliDTO, KhesaratSalesMali> {

    @Mapping(source = "sabeghe.id", target = "sabegheId")
    @Mapping(source = "sabeghe.name", target = "sabegheName")
    @Mapping(source = "noeSabeghe.id", target = "noeSabegheId")
    @Mapping(source = "noeSabeghe.name", target = "noeSabegheName")
    KhesaratSalesMaliDTO toDto(KhesaratSalesMali khesaratSalesMali);

    @Mapping(source = "sabegheId", target = "sabeghe")
    @Mapping(source = "noeSabegheId", target = "noeSabeghe")
    KhesaratSalesMali toEntity(KhesaratSalesMaliDTO khesaratSalesMaliDTO);

    default KhesaratSalesMali fromId(Long id) {
        if (id == null) {
            return null;
        }
        KhesaratSalesMali khesaratSalesMali = new KhesaratSalesMali();
        khesaratSalesMali.setId(id);
        return khesaratSalesMali;
    }
}

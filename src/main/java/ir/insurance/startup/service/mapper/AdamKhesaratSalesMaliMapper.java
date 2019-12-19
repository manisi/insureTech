package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.AdamKhesaratSalesMaliDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AdamKhesaratSalesMali and its DTO AdamKhesaratSalesMaliDTO.
 */
@Mapper(componentModel = "spring", uses = {SabegheMapper.class, NoeSabegheMapper.class})
public interface AdamKhesaratSalesMaliMapper extends EntityMapper<AdamKhesaratSalesMaliDTO, AdamKhesaratSalesMali> {

    @Mapping(source = "sabeghe.id", target = "sabegheId")
    @Mapping(source = "sabeghe.name", target = "sabegheName")
    @Mapping(source = "noeSabeghe.id", target = "noeSabegheId")
    @Mapping(source = "noeSabeghe.name", target = "noeSabegheName")
    AdamKhesaratSalesMaliDTO toDto(AdamKhesaratSalesMali adamKhesaratSalesMali);

    @Mapping(source = "sabegheId", target = "sabeghe")
    @Mapping(source = "noeSabegheId", target = "noeSabeghe")
    AdamKhesaratSalesMali toEntity(AdamKhesaratSalesMaliDTO adamKhesaratSalesMaliDTO);

    default AdamKhesaratSalesMali fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdamKhesaratSalesMali adamKhesaratSalesMali = new AdamKhesaratSalesMali();
        adamKhesaratSalesMali.setId(id);
        return adamKhesaratSalesMali;
    }
}

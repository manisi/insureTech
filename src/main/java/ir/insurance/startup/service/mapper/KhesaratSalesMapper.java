package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.KhesaratSalesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity KhesaratSales and its DTO KhesaratSalesDTO.
 */
@Mapper(componentModel = "spring", uses = {SabegheMapper.class, NoeSabegheMapper.class})
public interface KhesaratSalesMapper extends EntityMapper<KhesaratSalesDTO, KhesaratSales> {

    @Mapping(source = "sabeghe.id", target = "sabegheId")
    @Mapping(source = "sabeghe.name", target = "sabegheName")
    @Mapping(source = "noeSabeghe.id", target = "noeSabegheId")
    @Mapping(source = "noeSabeghe.name", target = "noeSabegheName")
    KhesaratSalesDTO toDto(KhesaratSales khesaratSales);

    @Mapping(source = "sabegheId", target = "sabeghe")
    @Mapping(source = "noeSabegheId", target = "noeSabeghe")
    KhesaratSales toEntity(KhesaratSalesDTO khesaratSalesDTO);

    default KhesaratSales fromId(Long id) {
        if (id == null) {
            return null;
        }
        KhesaratSales khesaratSales = new KhesaratSales();
        khesaratSales.setId(id);
        return khesaratSales;
    }
}

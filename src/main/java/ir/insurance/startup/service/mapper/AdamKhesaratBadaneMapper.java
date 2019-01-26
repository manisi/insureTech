package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.AdamKhesaratBadaneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AdamKhesaratBadane and its DTO AdamKhesaratBadaneDTO.
 */
@Mapper(componentModel = "spring", uses = {NoeSabegheMapper.class, SabegheMapper.class})
public interface AdamKhesaratBadaneMapper extends EntityMapper<AdamKhesaratBadaneDTO, AdamKhesaratBadane> {

    @Mapping(source = "noeSabeghe.id", target = "noeSabegheId")
    @Mapping(source = "noeSabeghe.name", target = "noeSabegheName")
    @Mapping(source = "sabeghe.id", target = "sabegheId")
    @Mapping(source = "sabeghe.name", target = "sabegheName")
    AdamKhesaratBadaneDTO toDto(AdamKhesaratBadane adamKhesaratBadane);

    @Mapping(source = "noeSabegheId", target = "noeSabeghe")
    @Mapping(source = "sabegheId", target = "sabeghe")
    AdamKhesaratBadane toEntity(AdamKhesaratBadaneDTO adamKhesaratBadaneDTO);

    default AdamKhesaratBadane fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdamKhesaratBadane adamKhesaratBadane = new AdamKhesaratBadane();
        adamKhesaratBadane.setId(id);
        return adamKhesaratBadane;
    }
}

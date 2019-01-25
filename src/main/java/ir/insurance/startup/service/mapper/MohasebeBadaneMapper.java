package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.MohasebeBadaneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MohasebeBadane and its DTO MohasebeBadaneDTO.
 */
@Mapper(componentModel = "spring", uses = {TipKhodroMapper.class})
public interface MohasebeBadaneMapper extends EntityMapper<MohasebeBadaneDTO, MohasebeBadane> {

    @Mapping(source = "tips.id", target = "tipsId")
    MohasebeBadaneDTO toDto(MohasebeBadane mohasebeBadane);

    @Mapping(source = "tipsId", target = "tips")
    MohasebeBadane toEntity(MohasebeBadaneDTO mohasebeBadaneDTO);

    default MohasebeBadane fromId(Long id) {
        if (id == null) {
            return null;
        }
        MohasebeBadane mohasebeBadane = new MohasebeBadane();
        mohasebeBadane.setId(id);
        return mohasebeBadane;
    }
}

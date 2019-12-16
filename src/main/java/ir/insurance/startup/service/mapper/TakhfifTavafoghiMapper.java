package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.TakhfifTavafoghiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TakhfifTavafoghi and its DTO TakhfifTavafoghiDTO.
 */
@Mapper(componentModel = "spring", uses = {SherkatBimeMapper.class})
public interface TakhfifTavafoghiMapper extends EntityMapper<TakhfifTavafoghiDTO, TakhfifTavafoghi> {

    @Mapping(source = "sherkatBime.id", target = "sherkatBimeId")
    @Mapping(source = "sherkatBime.name", target = "sherkatBimeName")
    TakhfifTavafoghiDTO toDto(TakhfifTavafoghi takhfifTavafoghi);

    @Mapping(source = "sherkatBimeId", target = "sherkatBime")
    TakhfifTavafoghi toEntity(TakhfifTavafoghiDTO takhfifTavafoghiDTO);

    default TakhfifTavafoghi fromId(Long id) {
        if (id == null) {
            return null;
        }
        TakhfifTavafoghi takhfifTavafoghi = new TakhfifTavafoghi();
        takhfifTavafoghi.setId(id);
        return takhfifTavafoghi;
    }
}

package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.CityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity City and its DTO CityDTO.
 */
@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public interface CityMapper extends EntityMapper<CityDTO, City> {

    @Mapping(source = "tips.id", target = "tipsId")
    CityDTO toDto(City city);

    @Mapping(source = "tipsId", target = "tips")
    City toEntity(CityDTO cityDTO);

    default City fromId(Long id) {
        if (id == null) {
            return null;
        }
        City city = new City();
        city.setId(id);
        return city;
    }
}

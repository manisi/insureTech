package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.GroupOperationsDataDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity GroupOperationsData and its DTO GroupOperationsDataDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GroupOperationsDataMapper extends EntityMapper<GroupOperationsDataDTO, GroupOperationsData> {



    default GroupOperationsData fromId(Long id) {
        if (id == null) {
            return null;
        }
        GroupOperationsData groupOperationsData = new GroupOperationsData();
        groupOperationsData.setId(id);
        return groupOperationsData;
    }
}

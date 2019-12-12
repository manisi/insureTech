package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.EstelaamSalesNerkhDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EstelaamSalesNerkh} and its DTO {@link EstelaamSalesNerkhDTO}.
 */
@Mapper(componentModel = "spring", uses = {AnvaeKhodroMapper.class, SaalSakhtMapper.class, OnvanKhodroMapper.class, AdamKhesaratMapper.class, AdamKhesaratSarneshinMapper.class, KhesaratSrneshinMapper.class, KhesaratSalesMapper.class})
public interface EstelaamSalesNerkhMapper extends EntityMapper<EstelaamSalesNerkhDTO, EstelaamSalesNerkh> {

    @Mapping(source = "anvaeKhodro.id", target = "anvaeKhodroId")
    @Mapping(source = "anvaeKhodro.onvan", target = "anvaeKhodroOnvan")
    @Mapping(source = "saalSakht.id", target = "saalSakhtId")
    @Mapping(source = "saalSakht.saalShamsi", target = "saalSakhtSaalShamsi")
    @Mapping(source = "onvanKhodro.id", target = "onvanKhodroId")
    @Mapping(source = "onvanKhodro.name", target = "onvanKhodroName")
    @Mapping(source = "adamKhesarat.id", target = "adamKhesaratId")
    @Mapping(source = "adamKhesarat.sales", target = "adamKhesaratSales")
    @Mapping(source = "adamKhesaratSarneshin.id", target = "adamKhesaratSarneshinId")
    @Mapping(source = "adamKhesaratSarneshin.sarneshin", target = "adamKhesaratSarneshinSarneshin")
    @Mapping(source = "khesaratSrneshin.id", target = "khesaratSrneshinId")
    @Mapping(source = "khesaratSrneshin.nerkhKhesarat", target = "khesaratSrneshinNerkhKhesarat")
    @Mapping(source = "khesaratSales.id", target = "khesaratSalesId")
    @Mapping(source = "khesaratSales.nerkhKhesarat", target = "khesaratSalesNerkhKhesarat")
    EstelaamSalesNerkhDTO toDto(EstelaamSalesNerkh estelaamSalesNerkh);

    @Mapping(source = "anvaeKhodroId", target = "anvaeKhodro")
    @Mapping(source = "saalSakhtId", target = "saalSakht")
    @Mapping(source = "onvanKhodroId", target = "onvanKhodro")
    @Mapping(source = "adamKhesaratId", target = "adamKhesarat")
    @Mapping(source = "adamKhesaratSarneshinId", target = "adamKhesaratSarneshin")
    @Mapping(source = "khesaratSrneshinId", target = "khesaratSrneshin")
    @Mapping(source = "khesaratSalesId", target = "khesaratSales")
    EstelaamSalesNerkh toEntity(EstelaamSalesNerkhDTO estelaamSalesNerkhDTO);

    default EstelaamSalesNerkh fromId(Long id) {
        if (id == null) {
            return null;
        }
        EstelaamSalesNerkh estelaamSalesNerkh = new EstelaamSalesNerkh();
        estelaamSalesNerkh.setId(id);
        return estelaamSalesNerkh;
    }
}

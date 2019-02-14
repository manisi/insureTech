package ir.insurance.startup.service.mapper;

import ir.insurance.startup.domain.*;
import ir.insurance.startup.service.dto.SabegheDTO;

//import org.mapstruct.*;
import ir.insurance.startup.service.util.CalendarUtil;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Mapper for the entity Sabeghe and its DTO SabegheDTO.
 */
//@Mapper(componentModel = "spring", uses = {})
//public interface SabegheMapper extends EntityMapper<SabegheDTO, Sabeghe> {
@Service
public class SabegheMapper  {

    public Sabeghe toEntity(SabegheDTO arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Sabeghe sabeghe = new Sabeghe();

        sabeghe.setId( arg0.getId() );
        sabeghe.setName( arg0.getName() );
        sabeghe.setSharh( arg0.getSharh() );
        sabeghe.setFaal( arg0.isFaal() );
        if ( arg0.getTarikh() != null ) {
            sabeghe.setTarikh( jalaliToGregorian(arg0.getTarikh()));
        }

        return sabeghe;
    }


    public SabegheDTO toDto(Sabeghe arg0) {
        if ( arg0 == null ) {
            return null;
        }

        SabegheDTO sabegheDTO = new SabegheDTO();

        sabegheDTO.setId( arg0.getId() );
        sabegheDTO.setName( arg0.getName() );
        sabegheDTO.setSharh( arg0.getSharh() );
        sabegheDTO.setFaal( arg0.isFaal() );
        if ( arg0.getTarikh() != null ) {
          // sabegheDTO.setTarikh( GregorianToJalali(arg0.getTarikh())); //error in ui for overflow date
           sabegheDTO.setTarikh( DateTimeFormatter.ISO_LOCAL_DATE.format( arg0.getTarikh()).toString() );// need pipe jalali in ui
        }

        return sabegheDTO;
    }


    public List<Sabeghe> toEntity(List<SabegheDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Sabeghe> list = new ArrayList<Sabeghe>( arg0.size() );
        for ( SabegheDTO sabegheDTO : arg0 ) {
            list.add( toEntity( sabegheDTO ) );
        }

        return list;
    }


    public List<SabegheDTO> toDto(List<Sabeghe> arg0) {
        if ( arg0 == null ) {
            return null;
        }
        List<SabegheDTO> list = new ArrayList<SabegheDTO>( arg0.size() );
        for ( Sabeghe sabeghe : arg0 ) {
            list.add( toDto( sabeghe ) );
        }

        return list;
    }

    public Sabeghe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Sabeghe sabeghe = new Sabeghe();
        sabeghe.setId(id);
        return sabeghe;
    }


    private LocalDate jalaliToGregorian(String tarikh){
        String[] str=tarikh.split("-");
        GregorianCalendar codeCreationDate = new CalendarUtil(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2])).toGregorian();
        LocalDate d = LocalDate.of(codeCreationDate.get(Calendar.YEAR), codeCreationDate.get(Calendar.MONTH)+1, codeCreationDate.get(Calendar.DAY_OF_MONTH));
        return d;
    }

    private String GregorianToJalali(LocalDate tarikh) {
        CalendarUtil codeCreationDate = new CalendarUtil(new GregorianCalendar(tarikh.getYear(), tarikh.getMonth().getValue()-1, tarikh.getDayOfMonth()));
        return codeCreationDate.toString();
    }
}

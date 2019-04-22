package ir.insurance.startup.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the SalesMazadCalc entity.
 */
public class SalesMazadCalcDTO implements Serializable {

    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    private Float mablaghSalesMazad;

    @NotNull
    @Min(value = 0)
    private Integer tedadRooz;

    @NotNull
    @DecimalMin(value = "0")
    private Float haghBime;

    @NotNull
    private LocalDate tarikhShoroo;

    @NotNull
    private LocalDate tarikhPayan;


    private Long namesherkatId;

    private String namesherkatName;

    private Long grouhKhodroId;

    private String grouhKhodroName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getMablaghSalesMazad() {
        return mablaghSalesMazad;
    }

    public void setMablaghSalesMazad(Float mablaghSalesMazad) {
        this.mablaghSalesMazad = mablaghSalesMazad;
    }

    public Integer getTedadRooz() {
        return tedadRooz;
    }

    public void setTedadRooz(Integer tedadRooz) {
        this.tedadRooz = tedadRooz;
    }

    public Float getHaghBime() {
        return haghBime;
    }

    public void setHaghBime(Float haghBime) {
        this.haghBime = haghBime;
    }

    public LocalDate getTarikhShoroo() {
        return tarikhShoroo;
    }

    public void setTarikhShoroo(LocalDate tarikhShoroo) {
        this.tarikhShoroo = tarikhShoroo;
    }

    public LocalDate getTarikhPayan() {
        return tarikhPayan;
    }

    public void setTarikhPayan(LocalDate tarikhPayan) {
        this.tarikhPayan = tarikhPayan;
    }

    public Long getNamesherkatId() {
        return namesherkatId;
    }

    public void setNamesherkatId(Long sherkatBimeId) {
        this.namesherkatId = sherkatBimeId;
    }

    public String getNamesherkatName() {
        return namesherkatName;
    }

    public void setNamesherkatName(String sherkatBimeName) {
        this.namesherkatName = sherkatBimeName;
    }

    public Long getGrouhKhodroId() {
        return grouhKhodroId;
    }

    public void setGrouhKhodroId(Long grouhKhodroId) {
        this.grouhKhodroId = grouhKhodroId;
    }

    public String getGrouhKhodroName() {
        return grouhKhodroName;
    }

    public void setGrouhKhodroName(String grouhKhodroName) {
        this.grouhKhodroName = grouhKhodroName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SalesMazadCalcDTO salesMazadCalcDTO = (SalesMazadCalcDTO) o;
        if (salesMazadCalcDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), salesMazadCalcDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SalesMazadCalcDTO{" +
            "id=" + getId() +
            ", mablaghSalesMazad=" + getMablaghSalesMazad() +
            ", tedadRooz=" + getTedadRooz() +
            ", haghBime=" + getHaghBime() +
            ", tarikhShoroo='" + getTarikhShoroo() + "'" +
            ", tarikhPayan='" + getTarikhPayan() + "'" +
            ", namesherkat=" + getNamesherkatId() +
            ", namesherkat='" + getNamesherkatName() + "'" +
            ", grouhKhodro=" + getGrouhKhodroId() +
            ", grouhKhodro='" + getGrouhKhodroName() + "'" +
            "}";
    }
}

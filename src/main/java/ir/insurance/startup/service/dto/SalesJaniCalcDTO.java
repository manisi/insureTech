package ir.insurance.startup.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the SalesJaniCalc entity.
 */
public class SalesJaniCalcDTO implements Serializable {

    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    private Float mablaghJani;

    @NotNull
    @DecimalMin(value = "0")
    private Float mablaghMaliEjbari;

    @NotNull
    private Integer tedadRooz;

    @NotNull
    private LocalDate tarikhShoroFaaliat;

    @NotNull
    private LocalDate tarikhPayanFaaliat;

    private String naamSherkat;

    @NotNull
    private Float haghbime;


    private Long bimenameId;

    private String bimenameName;

    private Long grouhKhodroId;

    private String grouhKhodroName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getMablaghJani() {
        return mablaghJani;
    }

    public void setMablaghJani(Float mablaghJani) {
        this.mablaghJani = mablaghJani;
    }

    public Float getMablaghMaliEjbari() {
        return mablaghMaliEjbari;
    }

    public void setMablaghMaliEjbari(Float mablaghMaliEjbari) {
        this.mablaghMaliEjbari = mablaghMaliEjbari;
    }

    public Integer getTedadRooz() {
        return tedadRooz;
    }

    public void setTedadRooz(Integer tedadRooz) {
        this.tedadRooz = tedadRooz;
    }

    public LocalDate getTarikhShoroFaaliat() {
        return tarikhShoroFaaliat;
    }

    public void setTarikhShoroFaaliat(LocalDate tarikhShoroFaaliat) {
        this.tarikhShoroFaaliat = tarikhShoroFaaliat;
    }

    public LocalDate getTarikhPayanFaaliat() {
        return tarikhPayanFaaliat;
    }

    public void setTarikhPayanFaaliat(LocalDate tarikhPayanFaaliat) {
        this.tarikhPayanFaaliat = tarikhPayanFaaliat;
    }

    public String getNaamSherkat() {
        return naamSherkat;
    }

    public void setNaamSherkat(String naamSherkat) {
        this.naamSherkat = naamSherkat;
    }

    public Float getHaghbime() {
        return haghbime;
    }

    public void setHaghbime(Float haghbime) {
        this.haghbime = haghbime;
    }

    public Long getBimenameId() {
        return bimenameId;
    }

    public void setBimenameId(Long sherkatBimeId) {
        this.bimenameId = sherkatBimeId;
    }

    public String getBimenameName() {
        return bimenameName;
    }

    public void setBimenameName(String sherkatBimeName) {
        this.bimenameName = sherkatBimeName;
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

        SalesJaniCalcDTO salesJaniCalcDTO = (SalesJaniCalcDTO) o;
        if (salesJaniCalcDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), salesJaniCalcDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SalesJaniCalcDTO{" +
            "id=" + getId() +
            ", mablaghJani=" + getMablaghJani() +
            ", mablaghMaliEjbari=" + getMablaghMaliEjbari() +
            ", tedadRooz=" + getTedadRooz() +
            ", tarikhShoroFaaliat='" + getTarikhShoroFaaliat() + "'" +
            ", tarikhPayanFaaliat='" + getTarikhPayanFaaliat() + "'" +
            ", naamSherkat='" + getNaamSherkat() + "'" +
            ", haghbime=" + getHaghbime() +
            ", bimename=" + getBimenameId() +
            ", bimename='" + getBimenameName() + "'" +
            ", grouhKhodro=" + getGrouhKhodroId() +
            ", grouhKhodro='" + getGrouhKhodroName() + "'" +
            "}";
    }
}

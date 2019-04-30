package ir.insurance.startup.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MoredEstefadeSales entity.
 */
public class MoredEstefadeSalesDTO implements Serializable {

    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    private Float darsadEzafeNerkh;

    @NotNull
    private LocalDate azTarikh;

    @NotNull
    private LocalDate taTarikh;

    @NotNull
    private Boolean faal;


    private Long grouhKhodroId;

    private String grouhKhodroName;

    private Long sherkatBimeId;

    private String sherkatBimeName;

    private Long onvanKhodroId;

    private String onvanKhodroName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getDarsadEzafeNerkh() {
        return darsadEzafeNerkh;
    }

    public void setDarsadEzafeNerkh(Float darsadEzafeNerkh) {
        this.darsadEzafeNerkh = darsadEzafeNerkh;
    }

    public LocalDate getAzTarikh() {
        return azTarikh;
    }

    public void setAzTarikh(LocalDate azTarikh) {
        this.azTarikh = azTarikh;
    }

    public LocalDate getTaTarikh() {
        return taTarikh;
    }

    public void setTaTarikh(LocalDate taTarikh) {
        this.taTarikh = taTarikh;
    }

    public Boolean isFaal() {
        return faal;
    }

    public void setFaal(Boolean faal) {
        this.faal = faal;
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

    public Long getSherkatBimeId() {
        return sherkatBimeId;
    }

    public void setSherkatBimeId(Long sherkatBimeId) {
        this.sherkatBimeId = sherkatBimeId;
    }

    public String getSherkatBimeName() {
        return sherkatBimeName;
    }

    public void setSherkatBimeName(String sherkatBimeName) {
        this.sherkatBimeName = sherkatBimeName;
    }

    public Long getOnvanKhodroId() {
        return onvanKhodroId;
    }

    public void setOnvanKhodroId(Long onvanKhodroId) {
        this.onvanKhodroId = onvanKhodroId;
    }

    public String getOnvanKhodroName() {
        return onvanKhodroName;
    }

    public void setOnvanKhodroName(String onvanKhodroName) {
        this.onvanKhodroName = onvanKhodroName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MoredEstefadeSalesDTO moredEstefadeSalesDTO = (MoredEstefadeSalesDTO) o;
        if (moredEstefadeSalesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), moredEstefadeSalesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MoredEstefadeSalesDTO{" +
            "id=" + getId() +
            ", darsadEzafeNerkh=" + getDarsadEzafeNerkh() +
            ", azTarikh='" + getAzTarikh() + "'" +
            ", taTarikh='" + getTaTarikh() + "'" +
            ", faal='" + isFaal() + "'" +
            ", grouhKhodro=" + getGrouhKhodroId() +
            ", grouhKhodro='" + getGrouhKhodroName() + "'" +
            ", sherkatBime=" + getSherkatBimeId() +
            ", sherkatBime='" + getSherkatBimeName() + "'" +
            ", onvanKhodro=" + getOnvanKhodroId() +
            ", onvanKhodro='" + getOnvanKhodroName() + "'" +
            "}";
    }
}

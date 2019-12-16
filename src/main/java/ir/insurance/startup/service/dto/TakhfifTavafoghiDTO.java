package ir.insurance.startup.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TakhfifTavafoghi entity.
 */
public class TakhfifTavafoghiDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Float darsadTakhfif;

    @NotNull
    private LocalDate azTarikh;

    @NotNull
    private Boolean faal;


    private Long sherkatBimeId;

    private String sherkatBimeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDarsadTakhfif() {
        return darsadTakhfif;
    }

    public void setDarsadTakhfif(Float darsadTakhfif) {
        this.darsadTakhfif = darsadTakhfif;
    }

    public LocalDate getAzTarikh() {
        return azTarikh;
    }

    public void setAzTarikh(LocalDate azTarikh) {
        this.azTarikh = azTarikh;
    }

    public Boolean isFaal() {
        return faal;
    }

    public void setFaal(Boolean faal) {
        this.faal = faal;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TakhfifTavafoghiDTO takhfifTavafoghiDTO = (TakhfifTavafoghiDTO) o;
        if (takhfifTavafoghiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), takhfifTavafoghiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TakhfifTavafoghiDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", darsadTakhfif=" + getDarsadTakhfif() +
            ", azTarikh='" + getAzTarikh() + "'" +
            ", faal='" + isFaal() + "'" +
            ", sherkatBime=" + getSherkatBimeId() +
            ", sherkatBime='" + getSherkatBimeName() + "'" +
            "}";
    }
}

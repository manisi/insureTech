package ir.insurance.startup.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the OnvanKhodro entity.
 */
public class OnvanKhodroDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String sharh;

    @NotNull
    private LocalDate azTarikh;

    @NotNull
    private LocalDate taTarikh;

    @NotNull
    private Boolean faal;


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

    public String getSharh() {
        return sharh;
    }

    public void setSharh(String sharh) {
        this.sharh = sharh;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OnvanKhodroDTO onvanKhodroDTO = (OnvanKhodroDTO) o;
        if (onvanKhodroDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), onvanKhodroDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OnvanKhodroDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sharh='" + getSharh() + "'" +
            ", azTarikh='" + getAzTarikh() + "'" +
            ", taTarikh='" + getTaTarikh() + "'" +
            ", faal='" + isFaal() + "'" +
            "}";
    }
}

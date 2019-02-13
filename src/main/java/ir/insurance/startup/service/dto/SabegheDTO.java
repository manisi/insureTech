package ir.insurance.startup.service.dto;

import ir.insurance.startup.service.util.CalendarUtil;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

import java.util.Objects;

/**
 * A DTO for the Sabeghe entity.
 */
public class SabegheDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String sharh;

    @NotNull
    private Boolean faal;

    private String tarikh;

    public SabegheDTO() {
        // Empty constructor needed for Jackson.
    }
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

    public Boolean isFaal() {
        return faal;
    }

    public void setFaal(Boolean faal) {
        this.faal = faal;
    }

    public String getTarikh() {
        return tarikh;
    }



    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SabegheDTO sabegheDTO = (SabegheDTO) o;
        if (sabegheDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sabegheDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SabegheDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sharh='" + getSharh() + "'" +
            ", faal='" + isFaal() + "'" +
            ", tarikh='" + getTarikh() + "'" +
            "}";
    }
}

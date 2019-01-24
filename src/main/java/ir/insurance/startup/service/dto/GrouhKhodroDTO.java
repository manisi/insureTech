package ir.insurance.startup.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the GrouhKhodro entity.
 */
public class GrouhKhodroDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

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

        GrouhKhodroDTO grouhKhodroDTO = (GrouhKhodroDTO) o;
        if (grouhKhodroDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), grouhKhodroDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GrouhKhodroDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", faal='" + isFaal() + "'" +
            "}";
    }
}

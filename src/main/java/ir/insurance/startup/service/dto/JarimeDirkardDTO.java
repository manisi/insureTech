package ir.insurance.startup.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the JarimeDirkard entity.
 */
public class JarimeDirkardDTO implements Serializable {

    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    private Float roozane;

    @NotNull
    private Boolean faal;


    private Long grouhKhodroId;

    private String grouhKhodroName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getRoozane() {
        return roozane;
    }

    public void setRoozane(Float roozane) {
        this.roozane = roozane;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JarimeDirkardDTO jarimeDirkardDTO = (JarimeDirkardDTO) o;
        if (jarimeDirkardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jarimeDirkardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JarimeDirkardDTO{" +
            "id=" + getId() +
            ", roozane=" + getRoozane() +
            ", faal='" + isFaal() + "'" +
            ", grouhKhodro=" + getGrouhKhodroId() +
            ", grouhKhodro='" + getGrouhKhodroName() + "'" +
            "}";
    }
}

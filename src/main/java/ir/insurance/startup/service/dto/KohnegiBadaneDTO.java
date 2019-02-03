package ir.insurance.startup.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the KohnegiBadane entity.
 */
public class KohnegiBadaneDTO implements Serializable {

    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Float darsadHarSal;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Float maxDarsad;

    @Lob
    private String sharh;

    @NotNull
    private Boolean faal;

    private Long grouhKhodroId;

    private String grouhKhodroCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getDarsadHarSal() {
        return darsadHarSal;
    }

    public void setDarsadHarSal(Float darsadHarSal) {
        this.darsadHarSal = darsadHarSal;
    }

    public Float getMaxDarsad() {
        return maxDarsad;
    }

    public void setMaxDarsad(Float maxDarsad) {
        this.maxDarsad = maxDarsad;
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

    public Long getGrouhKhodroId() {
        return grouhKhodroId;
    }

    public void setGrouhKhodroId(Long grouhKhodroId) {
        this.grouhKhodroId = grouhKhodroId;
    }

    public String getGrouhKhodroCode() {
        return grouhKhodroCode;
    }

    public void setGrouhKhodroCode(String grouhKhodroCode) {
        this.grouhKhodroCode = grouhKhodroCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        KohnegiBadaneDTO kohnegiBadaneDTO = (KohnegiBadaneDTO) o;
        if (kohnegiBadaneDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), kohnegiBadaneDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "KohnegiBadaneDTO{" +
            "id=" + getId() +
            ", darsadHarSal=" + getDarsadHarSal() +
            ", maxDarsad=" + getMaxDarsad() +
            ", sharh='" + getSharh() + "'" +
            ", faal='" + isFaal() + "'" +
            ", grouhKhodro=" + getGrouhKhodroId() +
            ", grouhKhodro='" + getGrouhKhodroCode() + "'" +
            "}";
    }
}

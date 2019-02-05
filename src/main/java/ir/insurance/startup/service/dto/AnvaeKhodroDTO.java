package ir.insurance.startup.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AnvaeKhodro entity.
 */
public class AnvaeKhodroDTO implements Serializable {

    private Long id;

    @NotNull
    private String grouhVasile;

    @NotNull
    private String systemVasile;

    @NotNull
    private String onvan;

    private String tonazh;

    @NotNull
    private String tedadSarneshin;

    @NotNull
    private String tedadSilandre;

    private String dasteBandi;

    @NotNull
    private String savariType;

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

    public String getGrouhVasile() {
        return grouhVasile;
    }

    public void setGrouhVasile(String grouhVasile) {
        this.grouhVasile = grouhVasile;
    }

    public String getSystemVasile() {
        return systemVasile;
    }

    public void setSystemVasile(String systemVasile) {
        this.systemVasile = systemVasile;
    }

    public String getOnvan() {
        return onvan;
    }

    public void setOnvan(String onvan) {
        this.onvan = onvan;
    }

    public String getTonazh() {
        return tonazh;
    }

    public void setTonazh(String tonazh) {
        this.tonazh = tonazh;
    }

    public String getTedadSarneshin() {
        return tedadSarneshin;
    }

    public void setTedadSarneshin(String tedadSarneshin) {
        this.tedadSarneshin = tedadSarneshin;
    }

    public String getTedadSilandre() {
        return tedadSilandre;
    }

    public void setTedadSilandre(String tedadSilandre) {
        this.tedadSilandre = tedadSilandre;
    }

    public String getDasteBandi() {
        return dasteBandi;
    }

    public void setDasteBandi(String dasteBandi) {
        this.dasteBandi = dasteBandi;
    }

    public String getSavariType() {
        return savariType;
    }

    public void setSavariType(String savariType) {
        this.savariType = savariType;
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

        AnvaeKhodroDTO anvaeKhodroDTO = (AnvaeKhodroDTO) o;
        if (anvaeKhodroDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), anvaeKhodroDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AnvaeKhodroDTO{" +
            "id=" + getId() +
            ", grouhVasile='" + getGrouhVasile() + "'" +
            ", systemVasile='" + getSystemVasile() + "'" +
            ", onvan='" + getOnvan() + "'" +
            ", tonazh='" + getTonazh() + "'" +
            ", tedadSarneshin='" + getTedadSarneshin() + "'" +
            ", tedadSilandre='" + getTedadSilandre() + "'" +
            ", dasteBandi='" + getDasteBandi() + "'" +
            ", savariType='" + getSavariType() + "'" +
            ", faal='" + isFaal() + "'" +
            ", grouhKhodro=" + getGrouhKhodroId() +
            ", grouhKhodro='" + getGrouhKhodroCode() + "'" +
            "}";
    }
}

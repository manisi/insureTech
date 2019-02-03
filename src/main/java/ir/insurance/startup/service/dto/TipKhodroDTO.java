package ir.insurance.startup.service.dto;
import java.io.Serializable;
import java.util.Objects;
import ir.insurance.startup.domain.enumeration.NoeKhodro;

/**
 * A DTO for the TipKhodro entity.
 */
public class TipKhodroDTO implements Serializable {

    private Long id;

    private String name;

    private String model;

    private Boolean faal;

    private NoeKhodro noe;


    private Long khodroId;

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean isFaal() {
        return faal;
    }

    public void setFaal(Boolean faal) {
        this.faal = faal;
    }

    public NoeKhodro getNoe() {
        return noe;
    }

    public void setNoe(NoeKhodro noe) {
        this.noe = noe;
    }

    public Long getKhodroId() {
        return khodroId;
    }

    public void setKhodroId(Long khodroId) {
        this.khodroId = khodroId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TipKhodroDTO tipKhodroDTO = (TipKhodroDTO) o;
        if (tipKhodroDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tipKhodroDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TipKhodroDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", model='" + getModel() + "'" +
            ", faal='" + isFaal() + "'" +
            ", noe='" + getNoe() + "'" +
            ", khodro=" + getKhodroId() +
            "}";
    }
}

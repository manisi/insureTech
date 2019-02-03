package ir.insurance.startup.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MohasebeSales entity.
 */
public class MohasebeSalesDTO implements Serializable {

    private Long id;

    private String nameSherkat;

    private Integer saltakhfif;


    private Long tipsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSherkat() {
        return nameSherkat;
    }

    public void setNameSherkat(String nameSherkat) {
        this.nameSherkat = nameSherkat;
    }

    public Integer getSaltakhfif() {
        return saltakhfif;
    }

    public void setSaltakhfif(Integer saltakhfif) {
        this.saltakhfif = saltakhfif;
    }

    public Long getTipsId() {
        return tipsId;
    }

    public void setTipsId(Long tipKhodroId) {
        this.tipsId = tipKhodroId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MohasebeSalesDTO mohasebeSalesDTO = (MohasebeSalesDTO) o;
        if (mohasebeSalesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mohasebeSalesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MohasebeSalesDTO{" +
            "id=" + getId() +
            ", nameSherkat='" + getNameSherkat() + "'" +
            ", saltakhfif=" + getSaltakhfif() +
            ", tips=" + getTipsId() +
            "}";
    }
}

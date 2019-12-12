package ir.insurance.startup.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the SaalSakht entity.
 */
public class SaalSakhtDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 4, max = 4)
    private String saalShamsi;

    @NotNull
    @Size(min = 4, max = 4)
    private String saalMiladi;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSaalShamsi() {
        return saalShamsi;
    }

    public void setSaalShamsi(String saalShamsi) {
        this.saalShamsi = saalShamsi;
    }

    public String getSaalMiladi() {
        return saalMiladi;
    }

    public void setSaalMiladi(String saalMiladi) {
        this.saalMiladi = saalMiladi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SaalSakhtDTO saalSakhtDTO = (SaalSakhtDTO) o;
        if (saalSakhtDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), saalSakhtDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SaalSakhtDTO{" +
            "id=" + getId() +
            ", saalShamsi='" + getSaalShamsi() + "'" +
            ", saalMiladi='" + getSaalMiladi() + "'" +
            "}";
    }
}

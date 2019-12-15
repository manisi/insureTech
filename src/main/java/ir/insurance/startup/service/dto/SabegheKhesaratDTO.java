package ir.insurance.startup.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the SabegheKhesarat entity.
 */
public class SabegheKhesaratDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String sharh;

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

        SabegheKhesaratDTO sabegheKhesaratDTO = (SabegheKhesaratDTO) o;
        if (sabegheKhesaratDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sabegheKhesaratDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SabegheKhesaratDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sharh='" + getSharh() + "'" +
            ", faal='" + isFaal() + "'" +
            "}";
    }
}

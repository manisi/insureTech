package ir.insurance.startup.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the VaziatBime entity.
 */
public class VaziatBimeDTO implements Serializable {

    private Long id;

    @NotNull
    private String title;

    @NotNull
    private Boolean faal;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

        VaziatBimeDTO vaziatBimeDTO = (VaziatBimeDTO) o;
        if (vaziatBimeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vaziatBimeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VaziatBimeDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", faal='" + isFaal() + "'" +
            "}";
    }
}

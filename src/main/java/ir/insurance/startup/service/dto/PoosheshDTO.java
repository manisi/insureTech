package ir.insurance.startup.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Pooshesh entity.
 */
public class PoosheshDTO implements Serializable {

    private Long id;

    private String name;

    private Boolean faal;

    private Instant aztarikh;


    private Long nerkhId;

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

    public Instant getAztarikh() {
        return aztarikh;
    }

    public void setAztarikh(Instant aztarikh) {
        this.aztarikh = aztarikh;
    }

    public Long getNerkhId() {
        return nerkhId;
    }

    public void setNerkhId(Long nerkhId) {
        this.nerkhId = nerkhId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PoosheshDTO poosheshDTO = (PoosheshDTO) o;
        if (poosheshDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), poosheshDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PoosheshDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", faal='" + isFaal() + "'" +
            ", aztarikh='" + getAztarikh() + "'" +
            ", nerkh=" + getNerkhId() +
            "}";
    }
}

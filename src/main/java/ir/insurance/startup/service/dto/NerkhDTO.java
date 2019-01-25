package ir.insurance.startup.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Nerkh entity.
 */
public class NerkhDTO implements Serializable {

    private Long id;

    private String name;

    private Boolean faal;

    private Float mablagh;

    private Set<SherkatBimeDTO> sherkatBimes = new HashSet<>();

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

    public Float getMablagh() {
        return mablagh;
    }

    public void setMablagh(Float mablagh) {
        this.mablagh = mablagh;
    }

    public Set<SherkatBimeDTO> getSherkatBimes() {
        return sherkatBimes;
    }

    public void setSherkatBimes(Set<SherkatBimeDTO> sherkatBimes) {
        this.sherkatBimes = sherkatBimes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NerkhDTO nerkhDTO = (NerkhDTO) o;
        if (nerkhDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nerkhDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NerkhDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", faal='" + isFaal() + "'" +
            ", mablagh=" + getMablagh() +
            "}";
    }
}

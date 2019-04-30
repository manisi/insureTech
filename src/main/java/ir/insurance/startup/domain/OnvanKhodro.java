package ir.insurance.startup.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A OnvanKhodro.
 */
@Entity
@Table(name = "onvan_khodro")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OnvanKhodro implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sharh")
    private String sharh;

    @NotNull
    @Column(name = "az_tarikh", nullable = false)
    private LocalDate azTarikh;

    @NotNull
    @Column(name = "ta_tarikh", nullable = false)
    private LocalDate taTarikh;

    @NotNull
    @Column(name = "faal", nullable = false)
    private Boolean faal;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public OnvanKhodro name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSharh() {
        return sharh;
    }

    public OnvanKhodro sharh(String sharh) {
        this.sharh = sharh;
        return this;
    }

    public void setSharh(String sharh) {
        this.sharh = sharh;
    }

    public LocalDate getAzTarikh() {
        return azTarikh;
    }

    public OnvanKhodro azTarikh(LocalDate azTarikh) {
        this.azTarikh = azTarikh;
        return this;
    }

    public void setAzTarikh(LocalDate azTarikh) {
        this.azTarikh = azTarikh;
    }

    public LocalDate getTaTarikh() {
        return taTarikh;
    }

    public OnvanKhodro taTarikh(LocalDate taTarikh) {
        this.taTarikh = taTarikh;
        return this;
    }

    public void setTaTarikh(LocalDate taTarikh) {
        this.taTarikh = taTarikh;
    }

    public Boolean isFaal() {
        return faal;
    }

    public OnvanKhodro faal(Boolean faal) {
        this.faal = faal;
        return this;
    }

    public void setFaal(Boolean faal) {
        this.faal = faal;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OnvanKhodro onvanKhodro = (OnvanKhodro) o;
        if (onvanKhodro.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), onvanKhodro.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OnvanKhodro{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sharh='" + getSharh() + "'" +
            ", azTarikh='" + getAzTarikh() + "'" +
            ", taTarikh='" + getTaTarikh() + "'" +
            ", faal='" + isFaal() + "'" +
            "}";
    }
}

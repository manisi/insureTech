package ir.insurance.startup.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Kohnegi.
 */
@Entity
@Table(name = "kohnegi")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Kohnegi implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "darsad_har_sal", nullable = false)
    private Float darsadHarSal;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "max_darsad", nullable = false)
    private Float maxDarsad;

    @Lob
    @Column(name = "sharh")
    private String sharh;

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

    public Float getDarsadHarSal() {
        return darsadHarSal;
    }

    public Kohnegi darsadHarSal(Float darsadHarSal) {
        this.darsadHarSal = darsadHarSal;
        return this;
    }

    public void setDarsadHarSal(Float darsadHarSal) {
        this.darsadHarSal = darsadHarSal;
    }

    public Float getMaxDarsad() {
        return maxDarsad;
    }

    public Kohnegi maxDarsad(Float maxDarsad) {
        this.maxDarsad = maxDarsad;
        return this;
    }

    public void setMaxDarsad(Float maxDarsad) {
        this.maxDarsad = maxDarsad;
    }

    public String getSharh() {
        return sharh;
    }

    public Kohnegi sharh(String sharh) {
        this.sharh = sharh;
        return this;
    }

    public void setSharh(String sharh) {
        this.sharh = sharh;
    }

    public Boolean isFaal() {
        return faal;
    }

    public Kohnegi faal(Boolean faal) {
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
        Kohnegi kohnegi = (Kohnegi) o;
        if (kohnegi.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), kohnegi.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Kohnegi{" +
            "id=" + getId() +
            ", darsadHarSal=" + getDarsadHarSal() +
            ", maxDarsad=" + getMaxDarsad() +
            ", sharh='" + getSharh() + "'" +
            ", faal='" + isFaal() + "'" +
            "}";
    }
}

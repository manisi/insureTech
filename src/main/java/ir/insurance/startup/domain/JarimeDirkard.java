package ir.insurance.startup.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A JarimeDirkard.
 */
@Entity
@Table(name = "jarime_dirkard")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class JarimeDirkard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "roozane", nullable = false)
    private Float roozane;

    @NotNull
    @Column(name = "faal", nullable = false)
    private Boolean faal;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    private GrouhKhodro grouhKhodro;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getRoozane() {
        return roozane;
    }

    public JarimeDirkard roozane(Float roozane) {
        this.roozane = roozane;
        return this;
    }

    public void setRoozane(Float roozane) {
        this.roozane = roozane;
    }

    public Boolean isFaal() {
        return faal;
    }

    public JarimeDirkard faal(Boolean faal) {
        this.faal = faal;
        return this;
    }

    public void setFaal(Boolean faal) {
        this.faal = faal;
    }

    public GrouhKhodro getGrouhKhodro() {
        return grouhKhodro;
    }

    public JarimeDirkard grouhKhodro(GrouhKhodro grouhKhodro) {
        this.grouhKhodro = grouhKhodro;
        return this;
    }

    public void setGrouhKhodro(GrouhKhodro grouhKhodro) {
        this.grouhKhodro = grouhKhodro;
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
        JarimeDirkard jarimeDirkard = (JarimeDirkard) o;
        if (jarimeDirkard.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jarimeDirkard.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JarimeDirkard{" +
            "id=" + getId() +
            ", roozane=" + getRoozane() +
            ", faal='" + isFaal() + "'" +
            "}";
    }
}

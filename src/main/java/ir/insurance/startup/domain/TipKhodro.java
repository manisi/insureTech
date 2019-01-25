package ir.insurance.startup.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import ir.insurance.startup.domain.enumeration.NoeKhodro;

/**
 * A TipKhodro.
 */
@Entity
@Table(name = "tip_khodro")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TipKhodro implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "model")
    private String model;

    @Column(name = "faal")
    private Boolean faal;

    @Enumerated(EnumType.STRING)
    @Column(name = "noe")
    private NoeKhodro noe;

    @ManyToOne
    @JsonIgnoreProperties("tips")
    private Khodro khodro;

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

    public TipKhodro name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public TipKhodro model(String model) {
        this.model = model;
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean isFaal() {
        return faal;
    }

    public TipKhodro faal(Boolean faal) {
        this.faal = faal;
        return this;
    }

    public void setFaal(Boolean faal) {
        this.faal = faal;
    }

    public NoeKhodro getNoe() {
        return noe;
    }

    public TipKhodro noe(NoeKhodro noe) {
        this.noe = noe;
        return this;
    }

    public void setNoe(NoeKhodro noe) {
        this.noe = noe;
    }

    public Khodro getKhodro() {
        return khodro;
    }

    public TipKhodro khodro(Khodro khodro) {
        this.khodro = khodro;
        return this;
    }

    public void setKhodro(Khodro khodro) {
        this.khodro = khodro;
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
        TipKhodro tipKhodro = (TipKhodro) o;
        if (tipKhodro.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tipKhodro.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TipKhodro{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", model='" + getModel() + "'" +
            ", faal='" + isFaal() + "'" +
            ", noe='" + getNoe() + "'" +
            "}";
    }
}

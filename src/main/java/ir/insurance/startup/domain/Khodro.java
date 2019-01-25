package ir.insurance.startup.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import ir.insurance.startup.domain.enumeration.NoeKhodro;

/**
 * A Khodro.
 */
@Entity
@Table(name = "khodro")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Khodro implements Serializable {

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

    @OneToMany(mappedBy = "khodro")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TipKhodro> tips = new HashSet<>();
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

    public Khodro name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public Khodro model(String model) {
        this.model = model;
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean isFaal() {
        return faal;
    }

    public Khodro faal(Boolean faal) {
        this.faal = faal;
        return this;
    }

    public void setFaal(Boolean faal) {
        this.faal = faal;
    }

    public NoeKhodro getNoe() {
        return noe;
    }

    public Khodro noe(NoeKhodro noe) {
        this.noe = noe;
        return this;
    }

    public void setNoe(NoeKhodro noe) {
        this.noe = noe;
    }

    public Set<TipKhodro> getTips() {
        return tips;
    }

    public Khodro tips(Set<TipKhodro> tipKhodros) {
        this.tips = tipKhodros;
        return this;
    }

    public Khodro addTips(TipKhodro tipKhodro) {
        this.tips.add(tipKhodro);
        tipKhodro.setKhodro(this);
        return this;
    }

    public Khodro removeTips(TipKhodro tipKhodro) {
        this.tips.remove(tipKhodro);
        tipKhodro.setKhodro(null);
        return this;
    }

    public void setTips(Set<TipKhodro> tipKhodros) {
        this.tips = tipKhodros;
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
        Khodro khodro = (Khodro) o;
        if (khodro.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), khodro.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Khodro{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", model='" + getModel() + "'" +
            ", faal='" + isFaal() + "'" +
            ", noe='" + getNoe() + "'" +
            "}";
    }
}

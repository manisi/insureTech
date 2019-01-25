package ir.insurance.startup.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A SherkatBime.
 */
@Entity
@Table(name = "sherkat_bime")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SherkatBime implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "faal")
    private Boolean faal;

    @ManyToMany(mappedBy = "sherkatBimes")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Nerkh> names = new HashSet<>();

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

    public SherkatBime name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isFaal() {
        return faal;
    }

    public SherkatBime faal(Boolean faal) {
        this.faal = faal;
        return this;
    }

    public void setFaal(Boolean faal) {
        this.faal = faal;
    }

    public Set<Nerkh> getNames() {
        return names;
    }

    public SherkatBime names(Set<Nerkh> nerkhs) {
        this.names = nerkhs;
        return this;
    }

    public SherkatBime addName(Nerkh nerkh) {
        this.names.add(nerkh);
        nerkh.getSherkatBimes().add(this);
        return this;
    }

    public SherkatBime removeName(Nerkh nerkh) {
        this.names.remove(nerkh);
        nerkh.getSherkatBimes().remove(this);
        return this;
    }

    public void setNames(Set<Nerkh> nerkhs) {
        this.names = nerkhs;
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
        SherkatBime sherkatBime = (SherkatBime) o;
        if (sherkatBime.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sherkatBime.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SherkatBime{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", faal='" + isFaal() + "'" +
            "}";
    }
}

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
 * A Nerkh.
 */
@Entity
@Table(name = "nerkh")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Nerkh implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "faal")
    private Boolean faal;

    @Column(name = "mablagh")
    private Float mablagh;

    @OneToMany(mappedBy = "nerkh")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Pooshesh> nerkhs = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "nerkh_sherkat_bime",
               joinColumns = @JoinColumn(name = "nerkh_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "sherkat_bime_id", referencedColumnName = "id"))
    private Set<SherkatBime> sherkatBimes = new HashSet<>();

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

    public Nerkh name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isFaal() {
        return faal;
    }

    public Nerkh faal(Boolean faal) {
        this.faal = faal;
        return this;
    }

    public void setFaal(Boolean faal) {
        this.faal = faal;
    }

    public Float getMablagh() {
        return mablagh;
    }

    public Nerkh mablagh(Float mablagh) {
        this.mablagh = mablagh;
        return this;
    }

    public void setMablagh(Float mablagh) {
        this.mablagh = mablagh;
    }

    public Set<Pooshesh> getNerkhs() {
        return nerkhs;
    }

    public Nerkh nerkhs(Set<Pooshesh> poosheshes) {
        this.nerkhs = poosheshes;
        return this;
    }

    public Nerkh addNerkh(Pooshesh pooshesh) {
        this.nerkhs.add(pooshesh);
        pooshesh.setNerkh(this);
        return this;
    }

    public Nerkh removeNerkh(Pooshesh pooshesh) {
        this.nerkhs.remove(pooshesh);
        pooshesh.setNerkh(null);
        return this;
    }

    public void setNerkhs(Set<Pooshesh> poosheshes) {
        this.nerkhs = poosheshes;
    }

    public Set<SherkatBime> getSherkatBimes() {
        return sherkatBimes;
    }

    public Nerkh sherkatBimes(Set<SherkatBime> sherkatBimes) {
        this.sherkatBimes = sherkatBimes;
        return this;
    }

    public Nerkh addSherkatBime(SherkatBime sherkatBime) {
        this.sherkatBimes.add(sherkatBime);
        sherkatBime.getNames().add(this);
        return this;
    }

    public Nerkh removeSherkatBime(SherkatBime sherkatBime) {
        this.sherkatBimes.remove(sherkatBime);
        sherkatBime.getNames().remove(this);
        return this;
    }

    public void setSherkatBimes(Set<SherkatBime> sherkatBimes) {
        this.sherkatBimes = sherkatBimes;
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
        Nerkh nerkh = (Nerkh) o;
        if (nerkh.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nerkh.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Nerkh{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", faal='" + isFaal() + "'" +
            ", mablagh=" + getMablagh() +
            "}";
    }
}

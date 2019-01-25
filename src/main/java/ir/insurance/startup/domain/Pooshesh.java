package ir.insurance.startup.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Pooshesh.
 */
@Entity
@Table(name = "pooshesh")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pooshesh implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "faal")
    private Boolean faal;

    @Column(name = "aztarikh")
    private Instant aztarikh;

    @ManyToOne
    @JsonIgnoreProperties("nerkhs")
    private Nerkh nerkh;

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

    public Pooshesh name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isFaal() {
        return faal;
    }

    public Pooshesh faal(Boolean faal) {
        this.faal = faal;
        return this;
    }

    public void setFaal(Boolean faal) {
        this.faal = faal;
    }

    public Instant getAztarikh() {
        return aztarikh;
    }

    public Pooshesh aztarikh(Instant aztarikh) {
        this.aztarikh = aztarikh;
        return this;
    }

    public void setAztarikh(Instant aztarikh) {
        this.aztarikh = aztarikh;
    }

    public Nerkh getNerkh() {
        return nerkh;
    }

    public Pooshesh nerkh(Nerkh nerkh) {
        this.nerkh = nerkh;
        return this;
    }

    public void setNerkh(Nerkh nerkh) {
        this.nerkh = nerkh;
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
        Pooshesh pooshesh = (Pooshesh) o;
        if (pooshesh.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pooshesh.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Pooshesh{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", faal='" + isFaal() + "'" +
            ", aztarikh='" + getAztarikh() + "'" +
            "}";
    }
}

package ir.insurance.startup.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Sabeghe.
 */
@Entity
@Table(name = "sabeghe")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Sabeghe implements Serializable {

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
    @Column(name = "faal", nullable = false)
    private Boolean faal;

    @Column(name = "tarikh")
    private LocalDate tarikh;

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

    public Sabeghe name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSharh() {
        return sharh;
    }

    public Sabeghe sharh(String sharh) {
        this.sharh = sharh;
        return this;
    }

    public void setSharh(String sharh) {
        this.sharh = sharh;
    }

    public Boolean isFaal() {
        return faal;
    }

    public Sabeghe faal(Boolean faal) {
        this.faal = faal;
        return this;
    }

    public void setFaal(Boolean faal) {
        this.faal = faal;
    }

    public LocalDate getTarikh() {
        return tarikh;
    }

    public Sabeghe tarikh(LocalDate tarikh) {
        this.tarikh = tarikh;
        return this;
    }

    public void setTarikh(LocalDate tarikh) {
        this.tarikh = tarikh;
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
        Sabeghe sabeghe = (Sabeghe) o;
        if (sabeghe.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sabeghe.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Sabeghe{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sharh='" + getSharh() + "'" +
            ", faal='" + isFaal() + "'" +
            ", tarikh='" + getTarikh() + "'" +
            "}";
    }
}

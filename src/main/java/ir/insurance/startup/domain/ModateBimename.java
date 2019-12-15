package ir.insurance.startup.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ModateBimename.
 */
@Entity
@Table(name = "modate_bimename")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ModateBimename implements Serializable {

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

    public ModateBimename name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSharh() {
        return sharh;
    }

    public ModateBimename sharh(String sharh) {
        this.sharh = sharh;
        return this;
    }

    public void setSharh(String sharh) {
        this.sharh = sharh;
    }

    public Boolean isFaal() {
        return faal;
    }

    public ModateBimename faal(Boolean faal) {
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
        ModateBimename modateBimename = (ModateBimename) o;
        if (modateBimename.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), modateBimename.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ModateBimename{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sharh='" + getSharh() + "'" +
            ", faal='" + isFaal() + "'" +
            "}";
    }
}

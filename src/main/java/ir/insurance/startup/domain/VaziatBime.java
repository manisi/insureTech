package ir.insurance.startup.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A VaziatBime.
 */
@Entity
@Table(name = "vaziat_bime")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class VaziatBime implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

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

    public String getTitle() {
        return title;
    }

    public VaziatBime title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean isFaal() {
        return faal;
    }

    public VaziatBime faal(Boolean faal) {
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
        VaziatBime vaziatBime = (VaziatBime) o;
        if (vaziatBime.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vaziatBime.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VaziatBime{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", faal='" + isFaal() + "'" +
            "}";
    }
}

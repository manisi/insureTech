package ir.insurance.startup.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A AdamKhesaratBadane.
 */
@Entity
@Table(name = "adam_khesarat_badane")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AdamKhesaratBadane implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "badane", nullable = false)
    private Float badane;

    @NotNull
    @Column(name = "faal", nullable = false)
    private Boolean faal;

    @ManyToOne(optional = false)
    @NotNull
    private NoeSabeghe noeSabeghe;

    @ManyToOne(optional = false)
    @NotNull
    private Sabeghe sabeghe;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getBadane() {
        return badane;
    }

    public AdamKhesaratBadane badane(Float badane) {
        this.badane = badane;
        return this;
    }

    public void setBadane(Float badane) {
        this.badane = badane;
    }

    public Boolean isFaal() {
        return faal;
    }

    public AdamKhesaratBadane faal(Boolean faal) {
        this.faal = faal;
        return this;
    }

    public void setFaal(Boolean faal) {
        this.faal = faal;
    }

    public NoeSabeghe getNoeSabeghe() {
        return noeSabeghe;
    }

    public AdamKhesaratBadane noeSabeghe(NoeSabeghe noeSabeghe) {
        this.noeSabeghe = noeSabeghe;
        return this;
    }

    public void setNoeSabeghe(NoeSabeghe noeSabeghe) {
        this.noeSabeghe = noeSabeghe;
    }

    public Sabeghe getSabeghe() {
        return sabeghe;
    }

    public AdamKhesaratBadane sabeghe(Sabeghe sabeghe) {
        this.sabeghe = sabeghe;
        return this;
    }

    public void setSabeghe(Sabeghe sabeghe) {
        this.sabeghe = sabeghe;
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
        AdamKhesaratBadane adamKhesaratBadane = (AdamKhesaratBadane) o;
        if (adamKhesaratBadane.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adamKhesaratBadane.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdamKhesaratBadane{" +
            "id=" + getId() +
            ", badane=" + getBadane() +
            ", faal='" + isFaal() + "'" +
            "}";
    }
}

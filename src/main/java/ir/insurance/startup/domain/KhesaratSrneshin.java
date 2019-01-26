package ir.insurance.startup.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A KhesaratSrneshin.
 */
@Entity
@Table(name = "khesarat_srneshin")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class KhesaratSrneshin implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "nerkh_khesarat", nullable = false)
    private Float nerkhKhesarat;

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

    public Float getNerkhKhesarat() {
        return nerkhKhesarat;
    }

    public KhesaratSrneshin nerkhKhesarat(Float nerkhKhesarat) {
        this.nerkhKhesarat = nerkhKhesarat;
        return this;
    }

    public void setNerkhKhesarat(Float nerkhKhesarat) {
        this.nerkhKhesarat = nerkhKhesarat;
    }

    public Boolean isFaal() {
        return faal;
    }

    public KhesaratSrneshin faal(Boolean faal) {
        this.faal = faal;
        return this;
    }

    public void setFaal(Boolean faal) {
        this.faal = faal;
    }

    public NoeSabeghe getNoeSabeghe() {
        return noeSabeghe;
    }

    public KhesaratSrneshin noeSabeghe(NoeSabeghe noeSabeghe) {
        this.noeSabeghe = noeSabeghe;
        return this;
    }

    public void setNoeSabeghe(NoeSabeghe noeSabeghe) {
        this.noeSabeghe = noeSabeghe;
    }

    public Sabeghe getSabeghe() {
        return sabeghe;
    }

    public KhesaratSrneshin sabeghe(Sabeghe sabeghe) {
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
        KhesaratSrneshin khesaratSrneshin = (KhesaratSrneshin) o;
        if (khesaratSrneshin.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), khesaratSrneshin.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "KhesaratSrneshin{" +
            "id=" + getId() +
            ", nerkhKhesarat=" + getNerkhKhesarat() +
            ", faal='" + isFaal() + "'" +
            "}";
    }
}

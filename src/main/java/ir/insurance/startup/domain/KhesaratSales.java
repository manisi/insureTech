package ir.insurance.startup.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import ir.insurance.startup.domain.enumeration.SalesSarneshinEnum;

/**
 * A KhesaratSales.
 */
@Entity
@Table(name = "khesarat_sales")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class KhesaratSales implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "noe", nullable = false)
    private SalesSarneshinEnum noe;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "nerkh_khesarat", nullable = false)
    private Float nerkhKhesarat;

    @NotNull
    @Column(name = "faal", nullable = false)
    private Boolean faal;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("khesaratSales")
    private Sabeghe sabeghe;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("khesaratSales")
    private NoeSabeghe noeSabeghe;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SalesSarneshinEnum getNoe() {
        return noe;
    }

    public KhesaratSales noe(SalesSarneshinEnum noe) {
        this.noe = noe;
        return this;
    }

    public void setNoe(SalesSarneshinEnum noe) {
        this.noe = noe;
    }

    public Float getNerkhKhesarat() {
        return nerkhKhesarat;
    }

    public KhesaratSales nerkhKhesarat(Float nerkhKhesarat) {
        this.nerkhKhesarat = nerkhKhesarat;
        return this;
    }

    public void setNerkhKhesarat(Float nerkhKhesarat) {
        this.nerkhKhesarat = nerkhKhesarat;
    }

    public Boolean isFaal() {
        return faal;
    }

    public KhesaratSales faal(Boolean faal) {
        this.faal = faal;
        return this;
    }

    public void setFaal(Boolean faal) {
        this.faal = faal;
    }

    public Sabeghe getSabeghe() {
        return sabeghe;
    }

    public KhesaratSales sabeghe(Sabeghe sabeghe) {
        this.sabeghe = sabeghe;
        return this;
    }

    public void setSabeghe(Sabeghe sabeghe) {
        this.sabeghe = sabeghe;
    }

    public NoeSabeghe getNoeSabeghe() {
        return noeSabeghe;
    }

    public KhesaratSales noeSabeghe(NoeSabeghe noeSabeghe) {
        this.noeSabeghe = noeSabeghe;
        return this;
    }

    public void setNoeSabeghe(NoeSabeghe noeSabeghe) {
        this.noeSabeghe = noeSabeghe;
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
        KhesaratSales khesaratSales = (KhesaratSales) o;
        if (khesaratSales.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), khesaratSales.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "KhesaratSales{" +
            "id=" + getId() +
            ", noe='" + getNoe() + "'" +
            ", nerkhKhesarat=" + getNerkhKhesarat() +
            ", faal='" + isFaal() + "'" +
            "}";
    }
}

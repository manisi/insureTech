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
 * A KhesaratSalesMali.
 */
@Entity
@Table(name = "khesarat_sales_mali")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class KhesaratSalesMali implements Serializable {

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
    @DecimalMax(value = "100")
    @Column(name = "nerkh_khesarat_mali", nullable = false)
    private Float nerkhKhesaratMali;

    @NotNull
    @Column(name = "faal", nullable = false)
    private Boolean faal;

    @ManyToOne(optional = false)
    @NotNull
    private Sabeghe sabeghe;

    @ManyToOne(optional = false)
    @NotNull
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

    public KhesaratSalesMali noe(SalesSarneshinEnum noe) {
        this.noe = noe;
        return this;
    }

    public void setNoe(SalesSarneshinEnum noe) {
        this.noe = noe;
    }

    public Float getNerkhKhesaratMali() {
        return nerkhKhesaratMali;
    }

    public KhesaratSalesMali nerkhKhesaratMali(Float nerkhKhesaratMali) {
        this.nerkhKhesaratMali = nerkhKhesaratMali;
        return this;
    }

    public void setNerkhKhesaratMali(Float nerkhKhesaratMali) {
        this.nerkhKhesaratMali = nerkhKhesaratMali;
    }

    public Boolean isFaal() {
        return faal;
    }

    public KhesaratSalesMali faal(Boolean faal) {
        this.faal = faal;
        return this;
    }

    public void setFaal(Boolean faal) {
        this.faal = faal;
    }

    public Sabeghe getSabeghe() {
        return sabeghe;
    }

    public KhesaratSalesMali sabeghe(Sabeghe sabeghe) {
        this.sabeghe = sabeghe;
        return this;
    }

    public void setSabeghe(Sabeghe sabeghe) {
        this.sabeghe = sabeghe;
    }

    public NoeSabeghe getNoeSabeghe() {
        return noeSabeghe;
    }

    public KhesaratSalesMali noeSabeghe(NoeSabeghe noeSabeghe) {
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
        KhesaratSalesMali khesaratSalesMali = (KhesaratSalesMali) o;
        if (khesaratSalesMali.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), khesaratSalesMali.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "KhesaratSalesMali{" +
            "id=" + getId() +
            ", noe='" + getNoe() + "'" +
            ", nerkhKhesaratMali=" + getNerkhKhesaratMali() +
            ", faal='" + isFaal() + "'" +
            "}";
    }
}

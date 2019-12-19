package ir.insurance.startup.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A AdamKhesaratSalesMali.
 */
@Entity
@Table(name = "adam_khesarat_sales_mali")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AdamKhesaratSalesMali implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "sales_mali", nullable = false)
    private Float salesMali;

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

    public Float getSalesMali() {
        return salesMali;
    }

    public AdamKhesaratSalesMali salesMali(Float salesMali) {
        this.salesMali = salesMali;
        return this;
    }

    public void setSalesMali(Float salesMali) {
        this.salesMali = salesMali;
    }

    public Boolean isFaal() {
        return faal;
    }

    public AdamKhesaratSalesMali faal(Boolean faal) {
        this.faal = faal;
        return this;
    }

    public void setFaal(Boolean faal) {
        this.faal = faal;
    }

    public Sabeghe getSabeghe() {
        return sabeghe;
    }

    public AdamKhesaratSalesMali sabeghe(Sabeghe sabeghe) {
        this.sabeghe = sabeghe;
        return this;
    }

    public void setSabeghe(Sabeghe sabeghe) {
        this.sabeghe = sabeghe;
    }

    public NoeSabeghe getNoeSabeghe() {
        return noeSabeghe;
    }

    public AdamKhesaratSalesMali noeSabeghe(NoeSabeghe noeSabeghe) {
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
        AdamKhesaratSalesMali adamKhesaratSalesMali = (AdamKhesaratSalesMali) o;
        if (adamKhesaratSalesMali.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adamKhesaratSalesMali.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdamKhesaratSalesMali{" +
            "id=" + getId() +
            ", salesMali=" + getSalesMali() +
            ", faal='" + isFaal() + "'" +
            "}";
    }
}

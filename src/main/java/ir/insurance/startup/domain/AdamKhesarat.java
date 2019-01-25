package ir.insurance.startup.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A AdamKhesarat.
 */
@Entity
@Table(name = "adam_khesarat")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AdamKhesarat implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "sales", nullable = false)
    private Float sales;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "mazad", nullable = false)
    private Float mazad;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "sarneshin", nullable = false)
    private Float sarneshin;

    @NotNull
    @Column(name = "faal", nullable = false)
    private Boolean faal;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("adamKhesarats")
    private Sabeghe sabeghe;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("adamKhesarats")
    private NoeSabeghe noeSabeghe;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getSales() {
        return sales;
    }

    public AdamKhesarat sales(Float sales) {
        this.sales = sales;
        return this;
    }

    public void setSales(Float sales) {
        this.sales = sales;
    }

    public Float getMazad() {
        return mazad;
    }

    public AdamKhesarat mazad(Float mazad) {
        this.mazad = mazad;
        return this;
    }

    public void setMazad(Float mazad) {
        this.mazad = mazad;
    }

    public Float getSarneshin() {
        return sarneshin;
    }

    public AdamKhesarat sarneshin(Float sarneshin) {
        this.sarneshin = sarneshin;
        return this;
    }

    public void setSarneshin(Float sarneshin) {
        this.sarneshin = sarneshin;
    }

    public Boolean isFaal() {
        return faal;
    }

    public AdamKhesarat faal(Boolean faal) {
        this.faal = faal;
        return this;
    }

    public void setFaal(Boolean faal) {
        this.faal = faal;
    }

    public Sabeghe getSabeghe() {
        return sabeghe;
    }

    public AdamKhesarat sabeghe(Sabeghe sabeghe) {
        this.sabeghe = sabeghe;
        return this;
    }

    public void setSabeghe(Sabeghe sabeghe) {
        this.sabeghe = sabeghe;
    }

    public NoeSabeghe getNoeSabeghe() {
        return noeSabeghe;
    }

    public AdamKhesarat noeSabeghe(NoeSabeghe noeSabeghe) {
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
        AdamKhesarat adamKhesarat = (AdamKhesarat) o;
        if (adamKhesarat.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adamKhesarat.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdamKhesarat{" +
            "id=" + getId() +
            ", sales=" + getSales() +
            ", mazad=" + getMazad() +
            ", sarneshin=" + getSarneshin() +
            ", faal='" + isFaal() + "'" +
            "}";
    }
}

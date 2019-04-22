package ir.insurance.startup.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A SalesSarneshinCalc.
 */
@Entity
@Table(name = "sales_sarneshin_calc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SalesSarneshinCalc implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "mablagh_sales_mazad", nullable = false)
    private Float mablaghSalesMazad;

    @NotNull
    @Min(value = 0)
    @Column(name = "tedad_rooz", nullable = false)
    private Integer tedadRooz;

    @NotNull
    @Column(name = "mablagh_hagh_bime", nullable = false)
    private Float mablaghHaghBime;

    @NotNull
    @Column(name = "tarikh_shoroo", nullable = false)
    private LocalDate tarikhShoroo;

    @Column(name = "tarikh_payan")
    private LocalDate tarikhPayan;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("salesSarneshinCalcs")
    private SherkatBime namesherkat;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("salesSarneshinCalcs")
    private GrouhKhodro grouhKhodro;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getMablaghSalesMazad() {
        return mablaghSalesMazad;
    }

    public SalesSarneshinCalc mablaghSalesMazad(Float mablaghSalesMazad) {
        this.mablaghSalesMazad = mablaghSalesMazad;
        return this;
    }

    public void setMablaghSalesMazad(Float mablaghSalesMazad) {
        this.mablaghSalesMazad = mablaghSalesMazad;
    }

    public Integer getTedadRooz() {
        return tedadRooz;
    }

    public SalesSarneshinCalc tedadRooz(Integer tedadRooz) {
        this.tedadRooz = tedadRooz;
        return this;
    }

    public void setTedadRooz(Integer tedadRooz) {
        this.tedadRooz = tedadRooz;
    }

    public Float getMablaghHaghBime() {
        return mablaghHaghBime;
    }

    public SalesSarneshinCalc mablaghHaghBime(Float mablaghHaghBime) {
        this.mablaghHaghBime = mablaghHaghBime;
        return this;
    }

    public void setMablaghHaghBime(Float mablaghHaghBime) {
        this.mablaghHaghBime = mablaghHaghBime;
    }

    public LocalDate getTarikhShoroo() {
        return tarikhShoroo;
    }

    public SalesSarneshinCalc tarikhShoroo(LocalDate tarikhShoroo) {
        this.tarikhShoroo = tarikhShoroo;
        return this;
    }

    public void setTarikhShoroo(LocalDate tarikhShoroo) {
        this.tarikhShoroo = tarikhShoroo;
    }

    public LocalDate getTarikhPayan() {
        return tarikhPayan;
    }

    public SalesSarneshinCalc tarikhPayan(LocalDate tarikhPayan) {
        this.tarikhPayan = tarikhPayan;
        return this;
    }

    public void setTarikhPayan(LocalDate tarikhPayan) {
        this.tarikhPayan = tarikhPayan;
    }

    public SherkatBime getNamesherkat() {
        return namesherkat;
    }

    public SalesSarneshinCalc namesherkat(SherkatBime sherkatBime) {
        this.namesherkat = sherkatBime;
        return this;
    }

    public void setNamesherkat(SherkatBime sherkatBime) {
        this.namesherkat = sherkatBime;
    }

    public GrouhKhodro getGrouhKhodro() {
        return grouhKhodro;
    }

    public SalesSarneshinCalc grouhKhodro(GrouhKhodro grouhKhodro) {
        this.grouhKhodro = grouhKhodro;
        return this;
    }

    public void setGrouhKhodro(GrouhKhodro grouhKhodro) {
        this.grouhKhodro = grouhKhodro;
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
        SalesSarneshinCalc salesSarneshinCalc = (SalesSarneshinCalc) o;
        if (salesSarneshinCalc.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), salesSarneshinCalc.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SalesSarneshinCalc{" +
            "id=" + getId() +
            ", mablaghSalesMazad=" + getMablaghSalesMazad() +
            ", tedadRooz=" + getTedadRooz() +
            ", mablaghHaghBime=" + getMablaghHaghBime() +
            ", tarikhShoroo='" + getTarikhShoroo() + "'" +
            ", tarikhPayan='" + getTarikhPayan() + "'" +
            "}";
    }
}

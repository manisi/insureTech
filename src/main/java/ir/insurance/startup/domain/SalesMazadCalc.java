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
 * A SalesMazadCalc.
 */
@Entity
@Table(name = "sales_mazad_calc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SalesMazadCalc implements Serializable {

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
    @DecimalMin(value = "0")
    @Column(name = "hagh_bime", nullable = false)
    private Float haghBime;

    @NotNull
    @Column(name = "tarikh_shoroo", nullable = false)
    private LocalDate tarikhShoroo;

    @NotNull
    @Column(name = "tarikh_payan", nullable = false)
    private LocalDate tarikhPayan;

    @ManyToOne(optional = false)
    @NotNull
    private SherkatBime namesherkat;

    @ManyToOne(optional = false)
    @NotNull
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

    public SalesMazadCalc mablaghSalesMazad(Float mablaghSalesMazad) {
        this.mablaghSalesMazad = mablaghSalesMazad;
        return this;
    }

    public void setMablaghSalesMazad(Float mablaghSalesMazad) {
        this.mablaghSalesMazad = mablaghSalesMazad;
    }

    public Integer getTedadRooz() {
        return tedadRooz;
    }

    public SalesMazadCalc tedadRooz(Integer tedadRooz) {
        this.tedadRooz = tedadRooz;
        return this;
    }

    public void setTedadRooz(Integer tedadRooz) {
        this.tedadRooz = tedadRooz;
    }

    public Float getHaghBime() {
        return haghBime;
    }

    public SalesMazadCalc haghBime(Float haghBime) {
        this.haghBime = haghBime;
        return this;
    }

    public void setHaghBime(Float haghBime) {
        this.haghBime = haghBime;
    }

    public LocalDate getTarikhShoroo() {
        return tarikhShoroo;
    }

    public SalesMazadCalc tarikhShoroo(LocalDate tarikhShoroo) {
        this.tarikhShoroo = tarikhShoroo;
        return this;
    }

    public void setTarikhShoroo(LocalDate tarikhShoroo) {
        this.tarikhShoroo = tarikhShoroo;
    }

    public LocalDate getTarikhPayan() {
        return tarikhPayan;
    }

    public SalesMazadCalc tarikhPayan(LocalDate tarikhPayan) {
        this.tarikhPayan = tarikhPayan;
        return this;
    }

    public void setTarikhPayan(LocalDate tarikhPayan) {
        this.tarikhPayan = tarikhPayan;
    }

    public SherkatBime getNamesherkat() {
        return namesherkat;
    }

    public SalesMazadCalc namesherkat(SherkatBime sherkatBime) {
        this.namesherkat = sherkatBime;
        return this;
    }

    public void setNamesherkat(SherkatBime sherkatBime) {
        this.namesherkat = sherkatBime;
    }

    public GrouhKhodro getGrouhKhodro() {
        return grouhKhodro;
    }

    public SalesMazadCalc grouhKhodro(GrouhKhodro grouhKhodro) {
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
        SalesMazadCalc salesMazadCalc = (SalesMazadCalc) o;
        if (salesMazadCalc.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), salesMazadCalc.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SalesMazadCalc{" +
            "id=" + getId() +
            ", mablaghSalesMazad=" + getMablaghSalesMazad() +
            ", tedadRooz=" + getTedadRooz() +
            ", haghBime=" + getHaghBime() +
            ", tarikhShoroo='" + getTarikhShoroo() + "'" +
            ", tarikhPayan='" + getTarikhPayan() + "'" +
            "}";
    }
}

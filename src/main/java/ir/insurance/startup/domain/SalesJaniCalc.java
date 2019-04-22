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
 * A SalesJaniCalc.
 */
@Entity
@Table(name = "sales_jani_calc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SalesJaniCalc implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "mablagh_jani", nullable = false)
    private Float mablaghJani;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "mablagh_mali_ejbari", nullable = false)
    private Float mablaghMaliEjbari;

    @NotNull
    @Column(name = "tedad_rooz", nullable = false)
    private Integer tedadRooz;

    @NotNull
    @Column(name = "tarikh_shoro_faaliat", nullable = false)
    private LocalDate tarikhShoroFaaliat;

    @NotNull
    @Column(name = "tarikh_payan_faaliat", nullable = false)
    private LocalDate tarikhPayanFaaliat;

    @Column(name = "naam_sherkat")
    private String naamSherkat;

    @NotNull
    @Column(name = "haghbime", nullable = false)
    private Float haghbime;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("salesJaniCalcs")
    private SherkatBime bimename;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("salesJaniCalcs")
    private GrouhKhodro grouhKhodro;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getMablaghJani() {
        return mablaghJani;
    }

    public SalesJaniCalc mablaghJani(Float mablaghJani) {
        this.mablaghJani = mablaghJani;
        return this;
    }

    public void setMablaghJani(Float mablaghJani) {
        this.mablaghJani = mablaghJani;
    }

    public Float getMablaghMaliEjbari() {
        return mablaghMaliEjbari;
    }

    public SalesJaniCalc mablaghMaliEjbari(Float mablaghMaliEjbari) {
        this.mablaghMaliEjbari = mablaghMaliEjbari;
        return this;
    }

    public void setMablaghMaliEjbari(Float mablaghMaliEjbari) {
        this.mablaghMaliEjbari = mablaghMaliEjbari;
    }

    public Integer getTedadRooz() {
        return tedadRooz;
    }

    public SalesJaniCalc tedadRooz(Integer tedadRooz) {
        this.tedadRooz = tedadRooz;
        return this;
    }

    public void setTedadRooz(Integer tedadRooz) {
        this.tedadRooz = tedadRooz;
    }

    public LocalDate getTarikhShoroFaaliat() {
        return tarikhShoroFaaliat;
    }

    public SalesJaniCalc tarikhShoroFaaliat(LocalDate tarikhShoroFaaliat) {
        this.tarikhShoroFaaliat = tarikhShoroFaaliat;
        return this;
    }

    public void setTarikhShoroFaaliat(LocalDate tarikhShoroFaaliat) {
        this.tarikhShoroFaaliat = tarikhShoroFaaliat;
    }

    public LocalDate getTarikhPayanFaaliat() {
        return tarikhPayanFaaliat;
    }

    public SalesJaniCalc tarikhPayanFaaliat(LocalDate tarikhPayanFaaliat) {
        this.tarikhPayanFaaliat = tarikhPayanFaaliat;
        return this;
    }

    public void setTarikhPayanFaaliat(LocalDate tarikhPayanFaaliat) {
        this.tarikhPayanFaaliat = tarikhPayanFaaliat;
    }

    public String getNaamSherkat() {
        return naamSherkat;
    }

    public SalesJaniCalc naamSherkat(String naamSherkat) {
        this.naamSherkat = naamSherkat;
        return this;
    }

    public void setNaamSherkat(String naamSherkat) {
        this.naamSherkat = naamSherkat;
    }

    public Float getHaghbime() {
        return haghbime;
    }

    public SalesJaniCalc haghbime(Float haghbime) {
        this.haghbime = haghbime;
        return this;
    }

    public void setHaghbime(Float haghbime) {
        this.haghbime = haghbime;
    }

    public SherkatBime getBimename() {
        return bimename;
    }

    public SalesJaniCalc bimename(SherkatBime sherkatBime) {
        this.bimename = sherkatBime;
        return this;
    }

    public void setBimename(SherkatBime sherkatBime) {
        this.bimename = sherkatBime;
    }

    public GrouhKhodro getGrouhKhodro() {
        return grouhKhodro;
    }

    public SalesJaniCalc grouhKhodro(GrouhKhodro grouhKhodro) {
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
        SalesJaniCalc salesJaniCalc = (SalesJaniCalc) o;
        if (salesJaniCalc.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), salesJaniCalc.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SalesJaniCalc{" +
            "id=" + getId() +
            ", mablaghJani=" + getMablaghJani() +
            ", mablaghMaliEjbari=" + getMablaghMaliEjbari() +
            ", tedadRooz=" + getTedadRooz() +
            ", tarikhShoroFaaliat='" + getTarikhShoroFaaliat() + "'" +
            ", tarikhPayanFaaliat='" + getTarikhPayanFaaliat() + "'" +
            ", naamSherkat='" + getNaamSherkat() + "'" +
            ", haghbime=" + getHaghbime() +
            "}";
    }
}

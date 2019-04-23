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
 * A MoredEstefadeSales.
 */
@Entity
@Table(name = "mored_estefade_sales")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MoredEstefadeSales implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "darsad_ezafe_nerkh", nullable = false)
    private Float darsadEzafeNerkh;

    @NotNull
    @Column(name = "az_tarikh", nullable = false)
    private LocalDate azTarikh;

    @NotNull
    @Column(name = "ta_tarikh", nullable = false)
    private LocalDate taTarikh;

    @NotNull
    @Column(name = "faal", nullable = false)
    private Boolean faal;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("moredEstefadeSales")
    private GrouhKhodro grouhKhodro;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("moredEstefadeSales")
    private SherkatBime sherkatBime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getDarsadEzafeNerkh() {
        return darsadEzafeNerkh;
    }

    public MoredEstefadeSales darsadEzafeNerkh(Float darsadEzafeNerkh) {
        this.darsadEzafeNerkh = darsadEzafeNerkh;
        return this;
    }

    public void setDarsadEzafeNerkh(Float darsadEzafeNerkh) {
        this.darsadEzafeNerkh = darsadEzafeNerkh;
    }

    public LocalDate getAzTarikh() {
        return azTarikh;
    }

    public MoredEstefadeSales azTarikh(LocalDate azTarikh) {
        this.azTarikh = azTarikh;
        return this;
    }

    public void setAzTarikh(LocalDate azTarikh) {
        this.azTarikh = azTarikh;
    }

    public LocalDate getTaTarikh() {
        return taTarikh;
    }

    public MoredEstefadeSales taTarikh(LocalDate taTarikh) {
        this.taTarikh = taTarikh;
        return this;
    }

    public void setTaTarikh(LocalDate taTarikh) {
        this.taTarikh = taTarikh;
    }

    public Boolean isFaal() {
        return faal;
    }

    public MoredEstefadeSales faal(Boolean faal) {
        this.faal = faal;
        return this;
    }

    public void setFaal(Boolean faal) {
        this.faal = faal;
    }

    public GrouhKhodro getGrouhKhodro() {
        return grouhKhodro;
    }

    public MoredEstefadeSales grouhKhodro(GrouhKhodro grouhKhodro) {
        this.grouhKhodro = grouhKhodro;
        return this;
    }

    public void setGrouhKhodro(GrouhKhodro grouhKhodro) {
        this.grouhKhodro = grouhKhodro;
    }

    public SherkatBime getSherkatBime() {
        return sherkatBime;
    }

    public MoredEstefadeSales sherkatBime(SherkatBime sherkatBime) {
        this.sherkatBime = sherkatBime;
        return this;
    }

    public void setSherkatBime(SherkatBime sherkatBime) {
        this.sherkatBime = sherkatBime;
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
        MoredEstefadeSales moredEstefadeSales = (MoredEstefadeSales) o;
        if (moredEstefadeSales.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), moredEstefadeSales.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MoredEstefadeSales{" +
            "id=" + getId() +
            ", darsadEzafeNerkh=" + getDarsadEzafeNerkh() +
            ", azTarikh='" + getAzTarikh() + "'" +
            ", taTarikh='" + getTaTarikh() + "'" +
            ", faal='" + isFaal() + "'" +
            "}";
    }
}

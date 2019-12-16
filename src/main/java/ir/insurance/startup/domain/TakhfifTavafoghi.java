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
 * A TakhfifTavafoghi.
 */
@Entity
@Table(name = "takhfif_tavafoghi")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TakhfifTavafoghi implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "darsad_takhfif", nullable = false)
    private Float darsadTakhfif;

    @NotNull
    @Column(name = "az_tarikh", nullable = false)
    private LocalDate azTarikh;

    @NotNull
    @Column(name = "faal", nullable = false)
    private Boolean faal;

    @ManyToOne(optional = false)
    @NotNull
    private SherkatBime sherkatBime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public TakhfifTavafoghi name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDarsadTakhfif() {
        return darsadTakhfif;
    }

    public TakhfifTavafoghi darsadTakhfif(Float darsadTakhfif) {
        this.darsadTakhfif = darsadTakhfif;
        return this;
    }

    public void setDarsadTakhfif(Float darsadTakhfif) {
        this.darsadTakhfif = darsadTakhfif;
    }

    public LocalDate getAzTarikh() {
        return azTarikh;
    }

    public TakhfifTavafoghi azTarikh(LocalDate azTarikh) {
        this.azTarikh = azTarikh;
        return this;
    }

    public void setAzTarikh(LocalDate azTarikh) {
        this.azTarikh = azTarikh;
    }

    public Boolean isFaal() {
        return faal;
    }

    public TakhfifTavafoghi faal(Boolean faal) {
        this.faal = faal;
        return this;
    }

    public void setFaal(Boolean faal) {
        this.faal = faal;
    }

    public SherkatBime getSherkatBime() {
        return sherkatBime;
    }

    public TakhfifTavafoghi sherkatBime(SherkatBime sherkatBime) {
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
        TakhfifTavafoghi takhfifTavafoghi = (TakhfifTavafoghi) o;
        if (takhfifTavafoghi.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), takhfifTavafoghi.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TakhfifTavafoghi{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", darsadTakhfif=" + getDarsadTakhfif() +
            ", azTarikh='" + getAzTarikh() + "'" +
            ", faal='" + isFaal() + "'" +
            "}";
    }
}

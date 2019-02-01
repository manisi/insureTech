package ir.insurance.startup.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A AnvaeKhodro.
 */
@Entity
@Table(name = "anvae_khodro")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AnvaeKhodro implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "grouh_vasile", nullable = false)
    private String grouhVasile;

    @NotNull
    @Column(name = "system_vasile", nullable = false)
    private String systemVasile;

    @NotNull
    @Column(name = "onvan", nullable = false)
    private String onvan;

    @Column(name = "tonazh")
    private String tonazh;

    @NotNull
    @Column(name = "tedad_sarneshin", nullable = false)
    private String tedadSarneshin;

    @NotNull
    @Column(name = "tedad_silandre", nullable = false)
    private String tedadSilandre;

    @Column(name = "daste_bandi")
    private String dasteBandi;

    @NotNull
    @Column(name = "savari_type", nullable = false)
    private String savariType;

    @ManyToOne
    @JsonIgnoreProperties("anvaeKhodros")
    private GrouhKhodro grouhKhodro;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrouhVasile() {
        return grouhVasile;
    }

    public AnvaeKhodro grouhVasile(String grouhVasile) {
        this.grouhVasile = grouhVasile;
        return this;
    }

    public void setGrouhVasile(String grouhVasile) {
        this.grouhVasile = grouhVasile;
    }

    public String getSystemVasile() {
        return systemVasile;
    }

    public AnvaeKhodro systemVasile(String systemVasile) {
        this.systemVasile = systemVasile;
        return this;
    }

    public void setSystemVasile(String systemVasile) {
        this.systemVasile = systemVasile;
    }

    public String getOnvan() {
        return onvan;
    }

    public AnvaeKhodro onvan(String onvan) {
        this.onvan = onvan;
        return this;
    }

    public void setOnvan(String onvan) {
        this.onvan = onvan;
    }

    public String getTonazh() {
        return tonazh;
    }

    public AnvaeKhodro tonazh(String tonazh) {
        this.tonazh = tonazh;
        return this;
    }

    public void setTonazh(String tonazh) {
        this.tonazh = tonazh;
    }

    public String getTedadSarneshin() {
        return tedadSarneshin;
    }

    public AnvaeKhodro tedadSarneshin(String tedadSarneshin) {
        this.tedadSarneshin = tedadSarneshin;
        return this;
    }

    public void setTedadSarneshin(String tedadSarneshin) {
        this.tedadSarneshin = tedadSarneshin;
    }

    public String getTedadSilandre() {
        return tedadSilandre;
    }

    public AnvaeKhodro tedadSilandre(String tedadSilandre) {
        this.tedadSilandre = tedadSilandre;
        return this;
    }

    public void setTedadSilandre(String tedadSilandre) {
        this.tedadSilandre = tedadSilandre;
    }

    public String getDasteBandi() {
        return dasteBandi;
    }

    public AnvaeKhodro dasteBandi(String dasteBandi) {
        this.dasteBandi = dasteBandi;
        return this;
    }

    public void setDasteBandi(String dasteBandi) {
        this.dasteBandi = dasteBandi;
    }

    public String getSavariType() {
        return savariType;
    }

    public AnvaeKhodro savariType(String savariType) {
        this.savariType = savariType;
        return this;
    }

    public void setSavariType(String savariType) {
        this.savariType = savariType;
    }

    public GrouhKhodro getGrouhKhodro() {
        return grouhKhodro;
    }

    public AnvaeKhodro grouhKhodro(GrouhKhodro grouhKhodro) {
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
        AnvaeKhodro anvaeKhodro = (AnvaeKhodro) o;
        if (anvaeKhodro.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), anvaeKhodro.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AnvaeKhodro{" +
            "id=" + getId() +
            ", grouhVasile='" + getGrouhVasile() + "'" +
            ", systemVasile='" + getSystemVasile() + "'" +
            ", onvan='" + getOnvan() + "'" +
            ", tonazh='" + getTonazh() + "'" +
            ", tedadSarneshin='" + getTedadSarneshin() + "'" +
            ", tedadSilandre='" + getTedadSilandre() + "'" +
            ", dasteBandi='" + getDasteBandi() + "'" +
            ", savariType='" + getSavariType() + "'" +
            "}";
    }
}

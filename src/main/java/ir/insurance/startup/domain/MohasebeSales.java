package ir.insurance.startup.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MohasebeSales.
 */
@Entity
@Table(name = "mohasebe_sales")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MohasebeSales implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name_sherkat")
    private String nameSherkat;

    @Column(name = "saltakhfif")
    private Integer saltakhfif;

    @ManyToOne
    @JsonIgnoreProperties("mohasebeSales")
    private TipKhodro tips;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSherkat() {
        return nameSherkat;
    }

    public MohasebeSales nameSherkat(String nameSherkat) {
        this.nameSherkat = nameSherkat;
        return this;
    }

    public void setNameSherkat(String nameSherkat) {
        this.nameSherkat = nameSherkat;
    }

    public Integer getSaltakhfif() {
        return saltakhfif;
    }

    public MohasebeSales saltakhfif(Integer saltakhfif) {
        this.saltakhfif = saltakhfif;
        return this;
    }

    public void setSaltakhfif(Integer saltakhfif) {
        this.saltakhfif = saltakhfif;
    }

    public TipKhodro getTips() {
        return tips;
    }

    public MohasebeSales tips(TipKhodro tipKhodro) {
        this.tips = tipKhodro;
        return this;
    }

    public void setTips(TipKhodro tipKhodro) {
        this.tips = tipKhodro;
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
        MohasebeSales mohasebeSales = (MohasebeSales) o;
        if (mohasebeSales.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mohasebeSales.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MohasebeSales{" +
            "id=" + getId() +
            ", nameSherkat='" + getNameSherkat() + "'" +
            ", saltakhfif=" + getSaltakhfif() +
            "}";
    }
}

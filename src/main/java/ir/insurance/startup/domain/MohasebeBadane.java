package ir.insurance.startup.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MohasebeBadane.
 */
@Entity
@Table(name = "mohasebe_badane")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MohasebeBadane implements Serializable {

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
    @JsonIgnoreProperties("")
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

    public MohasebeBadane nameSherkat(String nameSherkat) {
        this.nameSherkat = nameSherkat;
        return this;
    }

    public void setNameSherkat(String nameSherkat) {
        this.nameSherkat = nameSherkat;
    }

    public Integer getSaltakhfif() {
        return saltakhfif;
    }

    public MohasebeBadane saltakhfif(Integer saltakhfif) {
        this.saltakhfif = saltakhfif;
        return this;
    }

    public void setSaltakhfif(Integer saltakhfif) {
        this.saltakhfif = saltakhfif;
    }

    public TipKhodro getTips() {
        return tips;
    }

    public MohasebeBadane tips(TipKhodro tipKhodro) {
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
        MohasebeBadane mohasebeBadane = (MohasebeBadane) o;
        if (mohasebeBadane.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mohasebeBadane.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MohasebeBadane{" +
            "id=" + getId() +
            ", nameSherkat='" + getNameSherkat() + "'" +
            ", saltakhfif=" + getSaltakhfif() +
            "}";
    }
}

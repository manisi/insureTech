package ir.insurance.startup.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A SaalSakht.
 */
@Entity
@Table(name = "saal_sakht")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SaalSakht implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 4, max = 4)
    @Column(name = "saal_shamsi", length = 4, nullable = false, unique = true)
    private String saalShamsi;

    @NotNull
    @Size(min = 4, max = 4)
    @Column(name = "saal_miladi", length = 4, nullable = false, unique = true)
    private String saalMiladi;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSaalShamsi() {
        return saalShamsi;
    }

    public SaalSakht saalShamsi(String saalShamsi) {
        this.saalShamsi = saalShamsi;
        return this;
    }

    public void setSaalShamsi(String saalShamsi) {
        this.saalShamsi = saalShamsi;
    }

    public String getSaalMiladi() {
        return saalMiladi;
    }

    public SaalSakht saalMiladi(String saalMiladi) {
        this.saalMiladi = saalMiladi;
        return this;
    }

    public void setSaalMiladi(String saalMiladi) {
        this.saalMiladi = saalMiladi;
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
        SaalSakht saalSakht = (SaalSakht) o;
        if (saalSakht.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), saalSakht.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SaalSakht{" +
            "id=" + getId() +
            ", saalShamsi='" + getSaalShamsi() + "'" +
            ", saalMiladi='" + getSaalMiladi() + "'" +
            "}";
    }
}

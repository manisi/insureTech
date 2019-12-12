package ir.insurance.startup.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A EstelaamSalesNerkh.
 */
@Entity
@Table(name = "estelaam_sales_nerkh")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EstelaamSalesNerkh implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("estelaamSalesNerkhs")
    private AnvaeKhodro anvaeKhodro;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("estelaamSalesNerkhs")
    private SaalSakht saalSakht;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("estelaamSalesNerkhs")
    private OnvanKhodro onvanKhodro;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("estelaamSalesNerkhs")
    private AdamKhesarat adamKhesarat;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("estelaamSalesNerkhs")
    private AdamKhesaratSarneshin adamKhesaratSarneshin;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("estelaamSalesNerkhs")
    private KhesaratSrneshin khesaratSrneshin;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("estelaamSalesNerkhs")
    private KhesaratSales khesaratSales;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnvaeKhodro getAnvaeKhodro() {
        return anvaeKhodro;
    }

    public EstelaamSalesNerkh anvaeKhodro(AnvaeKhodro anvaeKhodro) {
        this.anvaeKhodro = anvaeKhodro;
        return this;
    }

    public void setAnvaeKhodro(AnvaeKhodro anvaeKhodro) {
        this.anvaeKhodro = anvaeKhodro;
    }

    public SaalSakht getSaalSakht() {
        return saalSakht;
    }

    public EstelaamSalesNerkh saalSakht(SaalSakht saalSakht) {
        this.saalSakht = saalSakht;
        return this;
    }

    public void setSaalSakht(SaalSakht saalSakht) {
        this.saalSakht = saalSakht;
    }

    public OnvanKhodro getOnvanKhodro() {
        return onvanKhodro;
    }

    public EstelaamSalesNerkh onvanKhodro(OnvanKhodro onvanKhodro) {
        this.onvanKhodro = onvanKhodro;
        return this;
    }

    public void setOnvanKhodro(OnvanKhodro onvanKhodro) {
        this.onvanKhodro = onvanKhodro;
    }

    public AdamKhesarat getAdamKhesarat() {
        return adamKhesarat;
    }

    public EstelaamSalesNerkh adamKhesarat(AdamKhesarat adamKhesarat) {
        this.adamKhesarat = adamKhesarat;
        return this;
    }

    public void setAdamKhesarat(AdamKhesarat adamKhesarat) {
        this.adamKhesarat = adamKhesarat;
    }

    public AdamKhesaratSarneshin getAdamKhesaratSarneshin() {
        return adamKhesaratSarneshin;
    }

    public EstelaamSalesNerkh adamKhesaratSarneshin(AdamKhesaratSarneshin adamKhesaratSarneshin) {
        this.adamKhesaratSarneshin = adamKhesaratSarneshin;
        return this;
    }

    public void setAdamKhesaratSarneshin(AdamKhesaratSarneshin adamKhesaratSarneshin) {
        this.adamKhesaratSarneshin = adamKhesaratSarneshin;
    }

    public KhesaratSrneshin getKhesaratSrneshin() {
        return khesaratSrneshin;
    }

    public EstelaamSalesNerkh khesaratSrneshin(KhesaratSrneshin khesaratSrneshin) {
        this.khesaratSrneshin = khesaratSrneshin;
        return this;
    }

    public void setKhesaratSrneshin(KhesaratSrneshin khesaratSrneshin) {
        this.khesaratSrneshin = khesaratSrneshin;
    }

    public KhesaratSales getKhesaratSales() {
        return khesaratSales;
    }

    public EstelaamSalesNerkh khesaratSales(KhesaratSales khesaratSales) {
        this.khesaratSales = khesaratSales;
        return this;
    }

    public void setKhesaratSales(KhesaratSales khesaratSales) {
        this.khesaratSales = khesaratSales;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EstelaamSalesNerkh)) {
            return false;
        }
        return id != null && id.equals(((EstelaamSalesNerkh) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EstelaamSalesNerkh{" +
            "id=" + getId() +
            "}";
    }
}

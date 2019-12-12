package ir.insurance.startup.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ir.insurance.startup.domain.EstelaamSalesNerkh} entity.
 */
public class EstelaamSalesNerkhDTO implements Serializable {

    private Long id;


    private Long anvaeKhodroId;

    private String anvaeKhodroOnvan;

    private Long saalSakhtId;

    private String saalSakhtSaalShamsi;

    private Long onvanKhodroId;

    private String onvanKhodroName;

    private Long adamKhesaratId;

    private String adamKhesaratSales;

    private Long adamKhesaratSarneshinId;

    private String adamKhesaratSarneshinSarneshin;

    private Long khesaratSrneshinId;

    private String khesaratSrneshinNerkhKhesarat;

    private Long khesaratSalesId;

    private String khesaratSalesNerkhKhesarat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAnvaeKhodroId() {
        return anvaeKhodroId;
    }

    public void setAnvaeKhodroId(Long anvaeKhodroId) {
        this.anvaeKhodroId = anvaeKhodroId;
    }

    public String getAnvaeKhodroOnvan() {
        return anvaeKhodroOnvan;
    }

    public void setAnvaeKhodroOnvan(String anvaeKhodroOnvan) {
        this.anvaeKhodroOnvan = anvaeKhodroOnvan;
    }

    public Long getSaalSakhtId() {
        return saalSakhtId;
    }

    public void setSaalSakhtId(Long saalSakhtId) {
        this.saalSakhtId = saalSakhtId;
    }

    public String getSaalSakhtSaalShamsi() {
        return saalSakhtSaalShamsi;
    }

    public void setSaalSakhtSaalShamsi(String saalSakhtSaalShamsi) {
        this.saalSakhtSaalShamsi = saalSakhtSaalShamsi;
    }

    public Long getOnvanKhodroId() {
        return onvanKhodroId;
    }

    public void setOnvanKhodroId(Long onvanKhodroId) {
        this.onvanKhodroId = onvanKhodroId;
    }

    public String getOnvanKhodroName() {
        return onvanKhodroName;
    }

    public void setOnvanKhodroName(String onvanKhodroName) {
        this.onvanKhodroName = onvanKhodroName;
    }

    public Long getAdamKhesaratId() {
        return adamKhesaratId;
    }

    public void setAdamKhesaratId(Long adamKhesaratId) {
        this.adamKhesaratId = adamKhesaratId;
    }

    public String getAdamKhesaratSales() {
        return adamKhesaratSales;
    }

    public void setAdamKhesaratSales(String adamKhesaratSales) {
        this.adamKhesaratSales = adamKhesaratSales;
    }

    public Long getAdamKhesaratSarneshinId() {
        return adamKhesaratSarneshinId;
    }

    public void setAdamKhesaratSarneshinId(Long adamKhesaratSarneshinId) {
        this.adamKhesaratSarneshinId = adamKhesaratSarneshinId;
    }

    public String getAdamKhesaratSarneshinSarneshin() {
        return adamKhesaratSarneshinSarneshin;
    }

    public void setAdamKhesaratSarneshinSarneshin(String adamKhesaratSarneshinSarneshin) {
        this.adamKhesaratSarneshinSarneshin = adamKhesaratSarneshinSarneshin;
    }

    public Long getKhesaratSrneshinId() {
        return khesaratSrneshinId;
    }

    public void setKhesaratSrneshinId(Long khesaratSrneshinId) {
        this.khesaratSrneshinId = khesaratSrneshinId;
    }

    public String getKhesaratSrneshinNerkhKhesarat() {
        return khesaratSrneshinNerkhKhesarat;
    }

    public void setKhesaratSrneshinNerkhKhesarat(String khesaratSrneshinNerkhKhesarat) {
        this.khesaratSrneshinNerkhKhesarat = khesaratSrneshinNerkhKhesarat;
    }

    public Long getKhesaratSalesId() {
        return khesaratSalesId;
    }

    public void setKhesaratSalesId(Long khesaratSalesId) {
        this.khesaratSalesId = khesaratSalesId;
    }

    public String getKhesaratSalesNerkhKhesarat() {
        return khesaratSalesNerkhKhesarat;
    }

    public void setKhesaratSalesNerkhKhesarat(String khesaratSalesNerkhKhesarat) {
        this.khesaratSalesNerkhKhesarat = khesaratSalesNerkhKhesarat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EstelaamSalesNerkhDTO estelaamSalesNerkhDTO = (EstelaamSalesNerkhDTO) o;
        if (estelaamSalesNerkhDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), estelaamSalesNerkhDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EstelaamSalesNerkhDTO{" +
            "id=" + getId() +
            ", anvaeKhodro=" + getAnvaeKhodroId() +
            ", anvaeKhodro='" + getAnvaeKhodroOnvan() + "'" +
            ", saalSakht=" + getSaalSakhtId() +
            ", saalSakht='" + getSaalSakhtSaalShamsi() + "'" +
            ", onvanKhodro=" + getOnvanKhodroId() +
            ", onvanKhodro='" + getOnvanKhodroName() + "'" +
            ", adamKhesarat=" + getAdamKhesaratId() +
            ", adamKhesarat='" + getAdamKhesaratSales() + "'" +
            ", adamKhesaratSarneshin=" + getAdamKhesaratSarneshinId() +
            ", adamKhesaratSarneshin='" + getAdamKhesaratSarneshinSarneshin() + "'" +
            ", khesaratSrneshin=" + getKhesaratSrneshinId() +
            ", khesaratSrneshin='" + getKhesaratSrneshinNerkhKhesarat() + "'" +
            ", khesaratSales=" + getKhesaratSalesId() +
            ", khesaratSales='" + getKhesaratSalesNerkhKhesarat() + "'" +
            "}";
    }
}

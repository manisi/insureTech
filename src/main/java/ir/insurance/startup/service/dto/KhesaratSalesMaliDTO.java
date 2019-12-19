package ir.insurance.startup.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import ir.insurance.startup.domain.enumeration.SalesSarneshinEnum;

/**
 * A DTO for the KhesaratSalesMali entity.
 */
public class KhesaratSalesMaliDTO implements Serializable {

    private Long id;

    @NotNull
    private SalesSarneshinEnum noe;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Float nerkhKhesaratMali;

    @NotNull
    private Boolean faal;


    private Long sabegheId;

    private String sabegheName;

    private Long noeSabegheId;

    private String noeSabegheName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SalesSarneshinEnum getNoe() {
        return noe;
    }

    public void setNoe(SalesSarneshinEnum noe) {
        this.noe = noe;
    }

    public Float getNerkhKhesaratMali() {
        return nerkhKhesaratMali;
    }

    public void setNerkhKhesaratMali(Float nerkhKhesaratMali) {
        this.nerkhKhesaratMali = nerkhKhesaratMali;
    }

    public Boolean isFaal() {
        return faal;
    }

    public void setFaal(Boolean faal) {
        this.faal = faal;
    }

    public Long getSabegheId() {
        return sabegheId;
    }

    public void setSabegheId(Long sabegheId) {
        this.sabegheId = sabegheId;
    }

    public String getSabegheName() {
        return sabegheName;
    }

    public void setSabegheName(String sabegheName) {
        this.sabegheName = sabegheName;
    }

    public Long getNoeSabegheId() {
        return noeSabegheId;
    }

    public void setNoeSabegheId(Long noeSabegheId) {
        this.noeSabegheId = noeSabegheId;
    }

    public String getNoeSabegheName() {
        return noeSabegheName;
    }

    public void setNoeSabegheName(String noeSabegheName) {
        this.noeSabegheName = noeSabegheName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        KhesaratSalesMaliDTO khesaratSalesMaliDTO = (KhesaratSalesMaliDTO) o;
        if (khesaratSalesMaliDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), khesaratSalesMaliDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "KhesaratSalesMaliDTO{" +
            "id=" + getId() +
            ", noe='" + getNoe() + "'" +
            ", nerkhKhesaratMali=" + getNerkhKhesaratMali() +
            ", faal='" + isFaal() + "'" +
            ", sabeghe=" + getSabegheId() +
            ", sabeghe='" + getSabegheName() + "'" +
            ", noeSabeghe=" + getNoeSabegheId() +
            ", noeSabeghe='" + getNoeSabegheName() + "'" +
            "}";
    }
}

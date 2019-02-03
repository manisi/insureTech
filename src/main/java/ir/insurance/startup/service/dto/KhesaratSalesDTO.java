package ir.insurance.startup.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import ir.insurance.startup.domain.enumeration.SalesSarneshinEnum;

/**
 * A DTO for the KhesaratSales entity.
 */
public class KhesaratSalesDTO implements Serializable {

    private Long id;

    @NotNull
    private SalesSarneshinEnum noe;

    @NotNull
    @DecimalMin(value = "0")
    private Float nerkhKhesarat;

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

    public Float getNerkhKhesarat() {
        return nerkhKhesarat;
    }

    public void setNerkhKhesarat(Float nerkhKhesarat) {
        this.nerkhKhesarat = nerkhKhesarat;
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

        KhesaratSalesDTO khesaratSalesDTO = (KhesaratSalesDTO) o;
        if (khesaratSalesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), khesaratSalesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "KhesaratSalesDTO{" +
            "id=" + getId() +
            ", noe='" + getNoe() + "'" +
            ", nerkhKhesarat=" + getNerkhKhesarat() +
            ", faal='" + isFaal() + "'" +
            ", sabeghe=" + getSabegheId() +
            ", sabeghe='" + getSabegheName() + "'" +
            ", noeSabeghe=" + getNoeSabegheId() +
            ", noeSabeghe='" + getNoeSabegheName() + "'" +
            "}";
    }
}

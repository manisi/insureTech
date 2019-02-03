package ir.insurance.startup.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AdamKhesarat entity.
 */
public class AdamKhesaratDTO implements Serializable {

    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    private Float sales;

    @NotNull
    @DecimalMin(value = "0")
    private Float mazad;

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

    public Float getSales() {
        return sales;
    }

    public void setSales(Float sales) {
        this.sales = sales;
    }

    public Float getMazad() {
        return mazad;
    }

    public void setMazad(Float mazad) {
        this.mazad = mazad;
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

        AdamKhesaratDTO adamKhesaratDTO = (AdamKhesaratDTO) o;
        if (adamKhesaratDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adamKhesaratDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdamKhesaratDTO{" +
            "id=" + getId() +
            ", sales=" + getSales() +
            ", mazad=" + getMazad() +
            ", faal='" + isFaal() + "'" +
            ", sabeghe=" + getSabegheId() +
            ", sabeghe='" + getSabegheName() + "'" +
            ", noeSabeghe=" + getNoeSabegheId() +
            ", noeSabeghe='" + getNoeSabegheName() + "'" +
            "}";
    }
}

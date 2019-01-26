package ir.insurance.startup.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AdamKhesaratSarneshin entity.
 */
public class AdamKhesaratSarneshinDTO implements Serializable {

    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    private Float sarneshin;

    @NotNull
    private Boolean faal;

    private Long noeSabegheId;

    private String noeSabegheName;

    private Long sabegheId;

    private String sabegheName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getSarneshin() {
        return sarneshin;
    }

    public void setSarneshin(Float sarneshin) {
        this.sarneshin = sarneshin;
    }

    public Boolean isFaal() {
        return faal;
    }

    public void setFaal(Boolean faal) {
        this.faal = faal;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdamKhesaratSarneshinDTO adamKhesaratSarneshinDTO = (AdamKhesaratSarneshinDTO) o;
        if (adamKhesaratSarneshinDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adamKhesaratSarneshinDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdamKhesaratSarneshinDTO{" +
            "id=" + getId() +
            ", sarneshin=" + getSarneshin() +
            ", faal='" + isFaal() + "'" +
            ", noeSabeghe=" + getNoeSabegheId() +
            ", noeSabeghe='" + getNoeSabegheName() + "'" +
            ", sabeghe=" + getSabegheId() +
            ", sabeghe='" + getSabegheName() + "'" +
            "}";
    }
}

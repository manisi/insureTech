package ir.insurance.startup.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the KhesaratSrneshin entity.
 */
public class KhesaratSrneshinDTO implements Serializable {

    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    private Float nerkhKhesarat;

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

        KhesaratSrneshinDTO khesaratSrneshinDTO = (KhesaratSrneshinDTO) o;
        if (khesaratSrneshinDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), khesaratSrneshinDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "KhesaratSrneshinDTO{" +
            "id=" + getId() +
            ", nerkhKhesarat=" + getNerkhKhesarat() +
            ", faal='" + isFaal() + "'" +
            ", noeSabeghe=" + getNoeSabegheId() +
            ", noeSabeghe='" + getNoeSabegheName() + "'" +
            ", sabeghe=" + getSabegheId() +
            ", sabeghe='" + getSabegheName() + "'" +
            "}";
    }
}

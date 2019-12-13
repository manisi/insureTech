package ir.insurance.startup.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EstelaamSalesNerkh entity.
 */
public class EstelaamSalesNerkhDTO implements Serializable {

    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
            "}";
    }
}

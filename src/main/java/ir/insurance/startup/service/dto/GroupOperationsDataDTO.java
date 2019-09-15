package ir.insurance.startup.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import ir.insurance.startup.domain.enumeration.EntityEnumName;
import ir.insurance.startup.domain.enumeration.EstateFileItem;

/**
 * A DTO for the GroupOperationsData entity.
 */
public class GroupOperationsDataDTO implements Serializable {

    private Long id;

    @NotNull
    private EntityEnumName entityName;

    @NotNull
    private Boolean acting;

    @NotNull
    private EstateFileItem estate;

    
    @Lob
    private byte[] uploadfile;

    private String uploadfileContentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EntityEnumName getEntityName() {
        return entityName;
    }

    public void setEntityName(EntityEnumName entityName) {
        this.entityName = entityName;
    }

    public Boolean isActing() {
        return acting;
    }

    public void setActing(Boolean acting) {
        this.acting = acting;
    }

    public EstateFileItem getEstate() {
        return estate;
    }

    public void setEstate(EstateFileItem estate) {
        this.estate = estate;
    }

    public byte[] getUploadfile() {
        return uploadfile;
    }

    public void setUploadfile(byte[] uploadfile) {
        this.uploadfile = uploadfile;
    }

    public String getUploadfileContentType() {
        return uploadfileContentType;
    }

    public void setUploadfileContentType(String uploadfileContentType) {
        this.uploadfileContentType = uploadfileContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GroupOperationsDataDTO groupOperationsDataDTO = (GroupOperationsDataDTO) o;
        if (groupOperationsDataDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), groupOperationsDataDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GroupOperationsDataDTO{" +
            "id=" + getId() +
            ", entityName='" + getEntityName() + "'" +
            ", acting='" + isActing() + "'" +
            ", estate='" + getEstate() + "'" +
            ", uploadfile='" + getUploadfile() + "'" +
            "}";
    }
}

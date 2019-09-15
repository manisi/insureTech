package ir.insurance.startup.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import ir.insurance.startup.domain.enumeration.EntityEnumName;

import ir.insurance.startup.domain.enumeration.EstateFileItem;

/**
 * A GroupOperationsData.
 */
@Entity
@Table(name = "group_operations_data")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GroupOperationsData implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "entity_name", nullable = false)
    private EntityEnumName entityName;

    @NotNull
    @Column(name = "acting", nullable = false)
    private Boolean acting;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estate", nullable = false)
    private EstateFileItem estate;

    
    @Lob
    @Column(name = "uploadfile", nullable = false)
    private byte[] uploadfile;

    @Column(name = "uploadfile_content_type", nullable = false)
    private String uploadfileContentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EntityEnumName getEntityName() {
        return entityName;
    }

    public GroupOperationsData entityName(EntityEnumName entityName) {
        this.entityName = entityName;
        return this;
    }

    public void setEntityName(EntityEnumName entityName) {
        this.entityName = entityName;
    }

    public Boolean isActing() {
        return acting;
    }

    public GroupOperationsData acting(Boolean acting) {
        this.acting = acting;
        return this;
    }

    public void setActing(Boolean acting) {
        this.acting = acting;
    }

    public EstateFileItem getEstate() {
        return estate;
    }

    public GroupOperationsData estate(EstateFileItem estate) {
        this.estate = estate;
        return this;
    }

    public void setEstate(EstateFileItem estate) {
        this.estate = estate;
    }

    public byte[] getUploadfile() {
        return uploadfile;
    }

    public GroupOperationsData uploadfile(byte[] uploadfile) {
        this.uploadfile = uploadfile;
        return this;
    }

    public void setUploadfile(byte[] uploadfile) {
        this.uploadfile = uploadfile;
    }

    public String getUploadfileContentType() {
        return uploadfileContentType;
    }

    public GroupOperationsData uploadfileContentType(String uploadfileContentType) {
        this.uploadfileContentType = uploadfileContentType;
        return this;
    }

    public void setUploadfileContentType(String uploadfileContentType) {
        this.uploadfileContentType = uploadfileContentType;
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
        GroupOperationsData groupOperationsData = (GroupOperationsData) o;
        if (groupOperationsData.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), groupOperationsData.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GroupOperationsData{" +
            "id=" + getId() +
            ", entityName='" + getEntityName() + "'" +
            ", acting='" + isActing() + "'" +
            ", estate='" + getEstate() + "'" +
            ", uploadfile='" + getUploadfile() + "'" +
            ", uploadfileContentType='" + getUploadfileContentType() + "'" +
            "}";
    }
}

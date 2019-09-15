package ir.insurance.startup.service.dto;

import java.io.Serializable;
import java.util.Objects;
import ir.insurance.startup.domain.enumeration.EntityEnumName;
import ir.insurance.startup.domain.enumeration.EstateFileItem;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the GroupOperationsData entity. This class is used in GroupOperationsDataResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /group-operations-data?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class GroupOperationsDataCriteria implements Serializable {
    /**
     * Class for filtering EntityEnumName
     */
    public static class EntityEnumNameFilter extends Filter<EntityEnumName> {
    }
    /**
     * Class for filtering EstateFileItem
     */
    public static class EstateFileItemFilter extends Filter<EstateFileItem> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private EntityEnumNameFilter entityName;

    private BooleanFilter acting;

    private EstateFileItemFilter estate;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public EntityEnumNameFilter getEntityName() {
        return entityName;
    }

    public void setEntityName(EntityEnumNameFilter entityName) {
        this.entityName = entityName;
    }

    public BooleanFilter getActing() {
        return acting;
    }

    public void setActing(BooleanFilter acting) {
        this.acting = acting;
    }

    public EstateFileItemFilter getEstate() {
        return estate;
    }

    public void setEstate(EstateFileItemFilter estate) {
        this.estate = estate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final GroupOperationsDataCriteria that = (GroupOperationsDataCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(entityName, that.entityName) &&
            Objects.equals(acting, that.acting) &&
            Objects.equals(estate, that.estate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        entityName,
        acting,
        estate
        );
    }

    @Override
    public String toString() {
        return "GroupOperationsDataCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (entityName != null ? "entityName=" + entityName + ", " : "") +
                (acting != null ? "acting=" + acting + ", " : "") +
                (estate != null ? "estate=" + estate + ", " : "") +
            "}";
    }

}

package ir.insurance.startup.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the AdamKhesaratSarneshin entity. This class is used in AdamKhesaratSarneshinResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /adam-khesarat-sarneshins?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AdamKhesaratSarneshinCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private FloatFilter sarneshin;

    private BooleanFilter faal;

    private LongFilter noeSabegheId;

    private LongFilter sabegheId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public FloatFilter getSarneshin() {
        return sarneshin;
    }

    public void setSarneshin(FloatFilter sarneshin) {
        this.sarneshin = sarneshin;
    }

    public BooleanFilter getFaal() {
        return faal;
    }

    public void setFaal(BooleanFilter faal) {
        this.faal = faal;
    }

    public LongFilter getNoeSabegheId() {
        return noeSabegheId;
    }

    public void setNoeSabegheId(LongFilter noeSabegheId) {
        this.noeSabegheId = noeSabegheId;
    }

    public LongFilter getSabegheId() {
        return sabegheId;
    }

    public void setSabegheId(LongFilter sabegheId) {
        this.sabegheId = sabegheId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AdamKhesaratSarneshinCriteria that = (AdamKhesaratSarneshinCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(sarneshin, that.sarneshin) &&
            Objects.equals(faal, that.faal) &&
            Objects.equals(noeSabegheId, that.noeSabegheId) &&
            Objects.equals(sabegheId, that.sabegheId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        sarneshin,
        faal,
        noeSabegheId,
        sabegheId
        );
    }

    @Override
    public String toString() {
        return "AdamKhesaratSarneshinCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (sarneshin != null ? "sarneshin=" + sarneshin + ", " : "") +
                (faal != null ? "faal=" + faal + ", " : "") +
                (noeSabegheId != null ? "noeSabegheId=" + noeSabegheId + ", " : "") +
                (sabegheId != null ? "sabegheId=" + sabegheId + ", " : "") +
            "}";
    }

}

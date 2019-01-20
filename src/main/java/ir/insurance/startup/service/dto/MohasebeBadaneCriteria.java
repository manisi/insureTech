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
 * Criteria class for the MohasebeBadane entity. This class is used in MohasebeBadaneResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /mohasebe-badanes?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MohasebeBadaneCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nameSherkat;

    private IntegerFilter saltakhfif;

    private LongFilter tipsId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNameSherkat() {
        return nameSherkat;
    }

    public void setNameSherkat(StringFilter nameSherkat) {
        this.nameSherkat = nameSherkat;
    }

    public IntegerFilter getSaltakhfif() {
        return saltakhfif;
    }

    public void setSaltakhfif(IntegerFilter saltakhfif) {
        this.saltakhfif = saltakhfif;
    }

    public LongFilter getTipsId() {
        return tipsId;
    }

    public void setTipsId(LongFilter tipsId) {
        this.tipsId = tipsId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MohasebeBadaneCriteria that = (MohasebeBadaneCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nameSherkat, that.nameSherkat) &&
            Objects.equals(saltakhfif, that.saltakhfif) &&
            Objects.equals(tipsId, that.tipsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nameSherkat,
        saltakhfif,
        tipsId
        );
    }

    @Override
    public String toString() {
        return "MohasebeBadaneCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nameSherkat != null ? "nameSherkat=" + nameSherkat + ", " : "") +
                (saltakhfif != null ? "saltakhfif=" + saltakhfif + ", " : "") +
                (tipsId != null ? "tipsId=" + tipsId + ", " : "") +
            "}";
    }

}

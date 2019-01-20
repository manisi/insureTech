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
 * Criteria class for the Nerkh entity. This class is used in NerkhResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /nerkhs?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NerkhCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private BooleanFilter faal;

    private FloatFilter mablagh;

    private LongFilter nerkhId;

    private LongFilter sherkatBimeId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public BooleanFilter getFaal() {
        return faal;
    }

    public void setFaal(BooleanFilter faal) {
        this.faal = faal;
    }

    public FloatFilter getMablagh() {
        return mablagh;
    }

    public void setMablagh(FloatFilter mablagh) {
        this.mablagh = mablagh;
    }

    public LongFilter getNerkhId() {
        return nerkhId;
    }

    public void setNerkhId(LongFilter nerkhId) {
        this.nerkhId = nerkhId;
    }

    public LongFilter getSherkatBimeId() {
        return sherkatBimeId;
    }

    public void setSherkatBimeId(LongFilter sherkatBimeId) {
        this.sherkatBimeId = sherkatBimeId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final NerkhCriteria that = (NerkhCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(faal, that.faal) &&
            Objects.equals(mablagh, that.mablagh) &&
            Objects.equals(nerkhId, that.nerkhId) &&
            Objects.equals(sherkatBimeId, that.sherkatBimeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        faal,
        mablagh,
        nerkhId,
        sherkatBimeId
        );
    }

    @Override
    public String toString() {
        return "NerkhCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (faal != null ? "faal=" + faal + ", " : "") +
                (mablagh != null ? "mablagh=" + mablagh + ", " : "") +
                (nerkhId != null ? "nerkhId=" + nerkhId + ", " : "") +
                (sherkatBimeId != null ? "sherkatBimeId=" + sherkatBimeId + ", " : "") +
            "}";
    }

}

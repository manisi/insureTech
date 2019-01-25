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
 * Criteria class for the JarimeDirkard entity. This class is used in JarimeDirkardResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /jarime-dirkards?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class JarimeDirkardCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private FloatFilter roozane;

    private BooleanFilter faal;

    private LongFilter grouhKhodroId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public FloatFilter getRoozane() {
        return roozane;
    }

    public void setRoozane(FloatFilter roozane) {
        this.roozane = roozane;
    }

    public BooleanFilter getFaal() {
        return faal;
    }

    public void setFaal(BooleanFilter faal) {
        this.faal = faal;
    }

    public LongFilter getGrouhKhodroId() {
        return grouhKhodroId;
    }

    public void setGrouhKhodroId(LongFilter grouhKhodroId) {
        this.grouhKhodroId = grouhKhodroId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final JarimeDirkardCriteria that = (JarimeDirkardCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(roozane, that.roozane) &&
            Objects.equals(faal, that.faal) &&
            Objects.equals(grouhKhodroId, that.grouhKhodroId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        roozane,
        faal,
        grouhKhodroId
        );
    }

    @Override
    public String toString() {
        return "JarimeDirkardCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (roozane != null ? "roozane=" + roozane + ", " : "") +
                (faal != null ? "faal=" + faal + ", " : "") +
                (grouhKhodroId != null ? "grouhKhodroId=" + grouhKhodroId + ", " : "") +
            "}";
    }

}

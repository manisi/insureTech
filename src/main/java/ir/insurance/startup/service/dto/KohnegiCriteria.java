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
 * Criteria class for the Kohnegi entity. This class is used in KohnegiResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /kohnegis?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class KohnegiCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private FloatFilter darsadHarSal;

    private FloatFilter maxDarsad;

    private BooleanFilter faal;

    private LongFilter grouhKhodroId;

    private LongFilter sherkatBimeId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public FloatFilter getDarsadHarSal() {
        return darsadHarSal;
    }

    public void setDarsadHarSal(FloatFilter darsadHarSal) {
        this.darsadHarSal = darsadHarSal;
    }

    public FloatFilter getMaxDarsad() {
        return maxDarsad;
    }

    public void setMaxDarsad(FloatFilter maxDarsad) {
        this.maxDarsad = maxDarsad;
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
        final KohnegiCriteria that = (KohnegiCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(darsadHarSal, that.darsadHarSal) &&
            Objects.equals(maxDarsad, that.maxDarsad) &&
            Objects.equals(faal, that.faal) &&
            Objects.equals(grouhKhodroId, that.grouhKhodroId) &&
            Objects.equals(sherkatBimeId, that.sherkatBimeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        darsadHarSal,
        maxDarsad,
        faal,
        grouhKhodroId,
        sherkatBimeId
        );
    }

    @Override
    public String toString() {
        return "KohnegiCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (darsadHarSal != null ? "darsadHarSal=" + darsadHarSal + ", " : "") +
                (maxDarsad != null ? "maxDarsad=" + maxDarsad + ", " : "") +
                (faal != null ? "faal=" + faal + ", " : "") +
                (grouhKhodroId != null ? "grouhKhodroId=" + grouhKhodroId + ", " : "") +
                (sherkatBimeId != null ? "sherkatBimeId=" + sherkatBimeId + ", " : "") +
            "}";
    }

}

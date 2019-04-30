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
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the MoredEstefadeSales entity. This class is used in MoredEstefadeSalesResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /mored-estefade-sales?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MoredEstefadeSalesCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private FloatFilter darsadEzafeNerkh;

    private LocalDateFilter azTarikh;

    private LocalDateFilter taTarikh;

    private BooleanFilter faal;

    private LongFilter grouhKhodroId;

    private LongFilter sherkatBimeId;

    private LongFilter onvanKhodroId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public FloatFilter getDarsadEzafeNerkh() {
        return darsadEzafeNerkh;
    }

    public void setDarsadEzafeNerkh(FloatFilter darsadEzafeNerkh) {
        this.darsadEzafeNerkh = darsadEzafeNerkh;
    }

    public LocalDateFilter getAzTarikh() {
        return azTarikh;
    }

    public void setAzTarikh(LocalDateFilter azTarikh) {
        this.azTarikh = azTarikh;
    }

    public LocalDateFilter getTaTarikh() {
        return taTarikh;
    }

    public void setTaTarikh(LocalDateFilter taTarikh) {
        this.taTarikh = taTarikh;
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

    public LongFilter getOnvanKhodroId() {
        return onvanKhodroId;
    }

    public void setOnvanKhodroId(LongFilter onvanKhodroId) {
        this.onvanKhodroId = onvanKhodroId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MoredEstefadeSalesCriteria that = (MoredEstefadeSalesCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(darsadEzafeNerkh, that.darsadEzafeNerkh) &&
            Objects.equals(azTarikh, that.azTarikh) &&
            Objects.equals(taTarikh, that.taTarikh) &&
            Objects.equals(faal, that.faal) &&
            Objects.equals(grouhKhodroId, that.grouhKhodroId) &&
            Objects.equals(sherkatBimeId, that.sherkatBimeId) &&
            Objects.equals(onvanKhodroId, that.onvanKhodroId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        darsadEzafeNerkh,
        azTarikh,
        taTarikh,
        faal,
        grouhKhodroId,
        sherkatBimeId,
        onvanKhodroId
        );
    }

    @Override
    public String toString() {
        return "MoredEstefadeSalesCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (darsadEzafeNerkh != null ? "darsadEzafeNerkh=" + darsadEzafeNerkh + ", " : "") +
                (azTarikh != null ? "azTarikh=" + azTarikh + ", " : "") +
                (taTarikh != null ? "taTarikh=" + taTarikh + ", " : "") +
                (faal != null ? "faal=" + faal + ", " : "") +
                (grouhKhodroId != null ? "grouhKhodroId=" + grouhKhodroId + ", " : "") +
                (sherkatBimeId != null ? "sherkatBimeId=" + sherkatBimeId + ", " : "") +
                (onvanKhodroId != null ? "onvanKhodroId=" + onvanKhodroId + ", " : "") +
            "}";
    }

}

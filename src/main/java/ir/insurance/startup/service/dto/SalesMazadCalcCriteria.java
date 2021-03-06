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
 * Criteria class for the SalesMazadCalc entity. This class is used in SalesMazadCalcResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /sales-mazad-calcs?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SalesMazadCalcCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private FloatFilter mablaghSalesMazad;

    private IntegerFilter tedadRooz;

    private FloatFilter haghBime;

    private LocalDateFilter tarikhShoroo;

    private LocalDateFilter tarikhPayan;

    private LongFilter namesherkatId;

    private LongFilter grouhKhodroId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public FloatFilter getMablaghSalesMazad() {
        return mablaghSalesMazad;
    }

    public void setMablaghSalesMazad(FloatFilter mablaghSalesMazad) {
        this.mablaghSalesMazad = mablaghSalesMazad;
    }

    public IntegerFilter getTedadRooz() {
        return tedadRooz;
    }

    public void setTedadRooz(IntegerFilter tedadRooz) {
        this.tedadRooz = tedadRooz;
    }

    public FloatFilter getHaghBime() {
        return haghBime;
    }

    public void setHaghBime(FloatFilter haghBime) {
        this.haghBime = haghBime;
    }

    public LocalDateFilter getTarikhShoroo() {
        return tarikhShoroo;
    }

    public void setTarikhShoroo(LocalDateFilter tarikhShoroo) {
        this.tarikhShoroo = tarikhShoroo;
    }

    public LocalDateFilter getTarikhPayan() {
        return tarikhPayan;
    }

    public void setTarikhPayan(LocalDateFilter tarikhPayan) {
        this.tarikhPayan = tarikhPayan;
    }

    public LongFilter getNamesherkatId() {
        return namesherkatId;
    }

    public void setNamesherkatId(LongFilter namesherkatId) {
        this.namesherkatId = namesherkatId;
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
        final SalesMazadCalcCriteria that = (SalesMazadCalcCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(mablaghSalesMazad, that.mablaghSalesMazad) &&
            Objects.equals(tedadRooz, that.tedadRooz) &&
            Objects.equals(haghBime, that.haghBime) &&
            Objects.equals(tarikhShoroo, that.tarikhShoroo) &&
            Objects.equals(tarikhPayan, that.tarikhPayan) &&
            Objects.equals(namesherkatId, that.namesherkatId) &&
            Objects.equals(grouhKhodroId, that.grouhKhodroId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        mablaghSalesMazad,
        tedadRooz,
        haghBime,
        tarikhShoroo,
        tarikhPayan,
        namesherkatId,
        grouhKhodroId
        );
    }

    @Override
    public String toString() {
        return "SalesMazadCalcCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (mablaghSalesMazad != null ? "mablaghSalesMazad=" + mablaghSalesMazad + ", " : "") +
                (tedadRooz != null ? "tedadRooz=" + tedadRooz + ", " : "") +
                (haghBime != null ? "haghBime=" + haghBime + ", " : "") +
                (tarikhShoroo != null ? "tarikhShoroo=" + tarikhShoroo + ", " : "") +
                (tarikhPayan != null ? "tarikhPayan=" + tarikhPayan + ", " : "") +
                (namesherkatId != null ? "namesherkatId=" + namesherkatId + ", " : "") +
                (grouhKhodroId != null ? "grouhKhodroId=" + grouhKhodroId + ", " : "") +
            "}";
    }

}

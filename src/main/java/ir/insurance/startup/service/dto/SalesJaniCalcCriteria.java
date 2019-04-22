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
 * Criteria class for the SalesJaniCalc entity. This class is used in SalesJaniCalcResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /sales-jani-calcs?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SalesJaniCalcCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private FloatFilter mablaghJani;

    private FloatFilter mablaghMaliEjbari;

    private IntegerFilter tedadRooz;

    private LocalDateFilter tarikhShoroFaaliat;

    private LocalDateFilter tarikhPayanFaaliat;

    private StringFilter naamSherkat;

    private FloatFilter haghbime;

    private LongFilter bimenameId;

    private LongFilter grouhKhodroId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public FloatFilter getMablaghJani() {
        return mablaghJani;
    }

    public void setMablaghJani(FloatFilter mablaghJani) {
        this.mablaghJani = mablaghJani;
    }

    public FloatFilter getMablaghMaliEjbari() {
        return mablaghMaliEjbari;
    }

    public void setMablaghMaliEjbari(FloatFilter mablaghMaliEjbari) {
        this.mablaghMaliEjbari = mablaghMaliEjbari;
    }

    public IntegerFilter getTedadRooz() {
        return tedadRooz;
    }

    public void setTedadRooz(IntegerFilter tedadRooz) {
        this.tedadRooz = tedadRooz;
    }

    public LocalDateFilter getTarikhShoroFaaliat() {
        return tarikhShoroFaaliat;
    }

    public void setTarikhShoroFaaliat(LocalDateFilter tarikhShoroFaaliat) {
        this.tarikhShoroFaaliat = tarikhShoroFaaliat;
    }

    public LocalDateFilter getTarikhPayanFaaliat() {
        return tarikhPayanFaaliat;
    }

    public void setTarikhPayanFaaliat(LocalDateFilter tarikhPayanFaaliat) {
        this.tarikhPayanFaaliat = tarikhPayanFaaliat;
    }

    public StringFilter getNaamSherkat() {
        return naamSherkat;
    }

    public void setNaamSherkat(StringFilter naamSherkat) {
        this.naamSherkat = naamSherkat;
    }

    public FloatFilter getHaghbime() {
        return haghbime;
    }

    public void setHaghbime(FloatFilter haghbime) {
        this.haghbime = haghbime;
    }

    public LongFilter getBimenameId() {
        return bimenameId;
    }

    public void setBimenameId(LongFilter bimenameId) {
        this.bimenameId = bimenameId;
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
        final SalesJaniCalcCriteria that = (SalesJaniCalcCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(mablaghJani, that.mablaghJani) &&
            Objects.equals(mablaghMaliEjbari, that.mablaghMaliEjbari) &&
            Objects.equals(tedadRooz, that.tedadRooz) &&
            Objects.equals(tarikhShoroFaaliat, that.tarikhShoroFaaliat) &&
            Objects.equals(tarikhPayanFaaliat, that.tarikhPayanFaaliat) &&
            Objects.equals(naamSherkat, that.naamSherkat) &&
            Objects.equals(haghbime, that.haghbime) &&
            Objects.equals(bimenameId, that.bimenameId) &&
            Objects.equals(grouhKhodroId, that.grouhKhodroId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        mablaghJani,
        mablaghMaliEjbari,
        tedadRooz,
        tarikhShoroFaaliat,
        tarikhPayanFaaliat,
        naamSherkat,
        haghbime,
        bimenameId,
        grouhKhodroId
        );
    }

    @Override
    public String toString() {
        return "SalesJaniCalcCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (mablaghJani != null ? "mablaghJani=" + mablaghJani + ", " : "") +
                (mablaghMaliEjbari != null ? "mablaghMaliEjbari=" + mablaghMaliEjbari + ", " : "") +
                (tedadRooz != null ? "tedadRooz=" + tedadRooz + ", " : "") +
                (tarikhShoroFaaliat != null ? "tarikhShoroFaaliat=" + tarikhShoroFaaliat + ", " : "") +
                (tarikhPayanFaaliat != null ? "tarikhPayanFaaliat=" + tarikhPayanFaaliat + ", " : "") +
                (naamSherkat != null ? "naamSherkat=" + naamSherkat + ", " : "") +
                (haghbime != null ? "haghbime=" + haghbime + ", " : "") +
                (bimenameId != null ? "bimenameId=" + bimenameId + ", " : "") +
                (grouhKhodroId != null ? "grouhKhodroId=" + grouhKhodroId + ", " : "") +
            "}";
    }

}

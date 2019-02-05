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
 * Criteria class for the AnvaeKhodro entity. This class is used in AnvaeKhodroResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /anvae-khodros?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AnvaeKhodroCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter grouhVasile;

    private StringFilter systemVasile;

    private StringFilter onvan;

    private StringFilter tonazh;

    private StringFilter tedadSarneshin;

    private StringFilter tedadSilandre;

    private StringFilter dasteBandi;

    private StringFilter savariType;

    private BooleanFilter faal;

    private LongFilter grouhKhodroId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getGrouhVasile() {
        return grouhVasile;
    }

    public void setGrouhVasile(StringFilter grouhVasile) {
        this.grouhVasile = grouhVasile;
    }

    public StringFilter getSystemVasile() {
        return systemVasile;
    }

    public void setSystemVasile(StringFilter systemVasile) {
        this.systemVasile = systemVasile;
    }

    public StringFilter getOnvan() {
        return onvan;
    }

    public void setOnvan(StringFilter onvan) {
        this.onvan = onvan;
    }

    public StringFilter getTonazh() {
        return tonazh;
    }

    public void setTonazh(StringFilter tonazh) {
        this.tonazh = tonazh;
    }

    public StringFilter getTedadSarneshin() {
        return tedadSarneshin;
    }

    public void setTedadSarneshin(StringFilter tedadSarneshin) {
        this.tedadSarneshin = tedadSarneshin;
    }

    public StringFilter getTedadSilandre() {
        return tedadSilandre;
    }

    public void setTedadSilandre(StringFilter tedadSilandre) {
        this.tedadSilandre = tedadSilandre;
    }

    public StringFilter getDasteBandi() {
        return dasteBandi;
    }

    public void setDasteBandi(StringFilter dasteBandi) {
        this.dasteBandi = dasteBandi;
    }

    public StringFilter getSavariType() {
        return savariType;
    }

    public void setSavariType(StringFilter savariType) {
        this.savariType = savariType;
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
        final AnvaeKhodroCriteria that = (AnvaeKhodroCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(grouhVasile, that.grouhVasile) &&
            Objects.equals(systemVasile, that.systemVasile) &&
            Objects.equals(onvan, that.onvan) &&
            Objects.equals(tonazh, that.tonazh) &&
            Objects.equals(tedadSarneshin, that.tedadSarneshin) &&
            Objects.equals(tedadSilandre, that.tedadSilandre) &&
            Objects.equals(dasteBandi, that.dasteBandi) &&
            Objects.equals(savariType, that.savariType) &&
            Objects.equals(faal, that.faal) &&
            Objects.equals(grouhKhodroId, that.grouhKhodroId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        grouhVasile,
        systemVasile,
        onvan,
        tonazh,
        tedadSarneshin,
        tedadSilandre,
        dasteBandi,
        savariType,
        faal,
        grouhKhodroId
        );
    }

    @Override
    public String toString() {
        return "AnvaeKhodroCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (grouhVasile != null ? "grouhVasile=" + grouhVasile + ", " : "") +
                (systemVasile != null ? "systemVasile=" + systemVasile + ", " : "") +
                (onvan != null ? "onvan=" + onvan + ", " : "") +
                (tonazh != null ? "tonazh=" + tonazh + ", " : "") +
                (tedadSarneshin != null ? "tedadSarneshin=" + tedadSarneshin + ", " : "") +
                (tedadSilandre != null ? "tedadSilandre=" + tedadSilandre + ", " : "") +
                (dasteBandi != null ? "dasteBandi=" + dasteBandi + ", " : "") +
                (savariType != null ? "savariType=" + savariType + ", " : "") +
                (faal != null ? "faal=" + faal + ", " : "") +
                (grouhKhodroId != null ? "grouhKhodroId=" + grouhKhodroId + ", " : "") +
            "}";
    }

}

package ir.insurance.startup.service.dto;

import java.io.Serializable;
import java.util.Objects;
import ir.insurance.startup.domain.enumeration.NoeKhodro;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the Khodro entity. This class is used in KhodroResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /khodros?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class KhodroCriteria implements Serializable {
    /**
     * Class for filtering NoeKhodro
     */
    public static class NoeKhodroFilter extends Filter<NoeKhodro> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter model;

    private BooleanFilter faal;

    private NoeKhodroFilter noe;

    private LongFilter tipsId;

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

    public StringFilter getModel() {
        return model;
    }

    public void setModel(StringFilter model) {
        this.model = model;
    }

    public BooleanFilter getFaal() {
        return faal;
    }

    public void setFaal(BooleanFilter faal) {
        this.faal = faal;
    }

    public NoeKhodroFilter getNoe() {
        return noe;
    }

    public void setNoe(NoeKhodroFilter noe) {
        this.noe = noe;
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
        final KhodroCriteria that = (KhodroCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(model, that.model) &&
            Objects.equals(faal, that.faal) &&
            Objects.equals(noe, that.noe) &&
            Objects.equals(tipsId, that.tipsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        model,
        faal,
        noe,
        tipsId
        );
    }

    @Override
    public String toString() {
        return "KhodroCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (model != null ? "model=" + model + ", " : "") +
                (faal != null ? "faal=" + faal + ", " : "") +
                (noe != null ? "noe=" + noe + ", " : "") +
                (tipsId != null ? "tipsId=" + tipsId + ", " : "") +
            "}";
    }

}

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
 * Criteria class for the OnvanKhodro entity. This class is used in OnvanKhodroResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /onvan-khodros?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class OnvanKhodroCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter sharh;

    private LocalDateFilter azTarikh;

    private LocalDateFilter taTarikh;

    private BooleanFilter faal;

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

    public StringFilter getSharh() {
        return sharh;
    }

    public void setSharh(StringFilter sharh) {
        this.sharh = sharh;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final OnvanKhodroCriteria that = (OnvanKhodroCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(sharh, that.sharh) &&
            Objects.equals(azTarikh, that.azTarikh) &&
            Objects.equals(taTarikh, that.taTarikh) &&
            Objects.equals(faal, that.faal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        sharh,
        azTarikh,
        taTarikh,
        faal
        );
    }

    @Override
    public String toString() {
        return "OnvanKhodroCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (sharh != null ? "sharh=" + sharh + ", " : "") +
                (azTarikh != null ? "azTarikh=" + azTarikh + ", " : "") +
                (taTarikh != null ? "taTarikh=" + taTarikh + ", " : "") +
                (faal != null ? "faal=" + faal + ", " : "") +
            "}";
    }

}

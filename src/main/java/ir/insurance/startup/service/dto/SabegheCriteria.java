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
 * Criteria class for the Sabeghe entity. This class is used in SabegheResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /sabeghes?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SabegheCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter sharh;

    private BooleanFilter faal;

    private LocalDateFilter tarikh;

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

    public BooleanFilter getFaal() {
        return faal;
    }

    public void setFaal(BooleanFilter faal) {
        this.faal = faal;
    }

    public LocalDateFilter getTarikh() {
        return tarikh;
    }

    public void setTarikh(LocalDateFilter tarikh) {
        this.tarikh = tarikh;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SabegheCriteria that = (SabegheCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(sharh, that.sharh) &&
            Objects.equals(faal, that.faal) &&
            Objects.equals(tarikh, that.tarikh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        sharh,
        faal,
        tarikh
        );
    }

    @Override
    public String toString() {
        return "SabegheCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (sharh != null ? "sharh=" + sharh + ", " : "") +
                (faal != null ? "faal=" + faal + ", " : "") +
                (tarikh != null ? "tarikh=" + tarikh + ", " : "") +
            "}";
    }

}

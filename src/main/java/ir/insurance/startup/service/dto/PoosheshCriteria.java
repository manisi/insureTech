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
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the Pooshesh entity. This class is used in PoosheshResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /poosheshes?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PoosheshCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private BooleanFilter faal;

    private InstantFilter aztarikh;

    private LongFilter nerkhId;

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

    public InstantFilter getAztarikh() {
        return aztarikh;
    }

    public void setAztarikh(InstantFilter aztarikh) {
        this.aztarikh = aztarikh;
    }

    public LongFilter getNerkhId() {
        return nerkhId;
    }

    public void setNerkhId(LongFilter nerkhId) {
        this.nerkhId = nerkhId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PoosheshCriteria that = (PoosheshCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(faal, that.faal) &&
            Objects.equals(aztarikh, that.aztarikh) &&
            Objects.equals(nerkhId, that.nerkhId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        faal,
        aztarikh,
        nerkhId
        );
    }

    @Override
    public String toString() {
        return "PoosheshCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (faal != null ? "faal=" + faal + ", " : "") +
                (aztarikh != null ? "aztarikh=" + aztarikh + ", " : "") +
                (nerkhId != null ? "nerkhId=" + nerkhId + ", " : "") +
            "}";
    }

}

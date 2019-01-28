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
 * Criteria class for the AdamKhesarat entity. This class is used in AdamKhesaratResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /adam-khesarats?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AdamKhesaratCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private FloatFilter sales;

    private FloatFilter mazad;

    private BooleanFilter faal;

    private LongFilter sabegheId;

    private LongFilter noeSabegheId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public FloatFilter getSales() {
        return sales;
    }

    public void setSales(FloatFilter sales) {
        this.sales = sales;
    }

    public FloatFilter getMazad() {
        return mazad;
    }

    public void setMazad(FloatFilter mazad) {
        this.mazad = mazad;
    }

    public BooleanFilter getFaal() {
        return faal;
    }

    public void setFaal(BooleanFilter faal) {
        this.faal = faal;
    }

    public LongFilter getSabegheId() {
        return sabegheId;
    }

    public void setSabegheId(LongFilter sabegheId) {
        this.sabegheId = sabegheId;
    }

    public LongFilter getNoeSabegheId() {
        return noeSabegheId;
    }

    public void setNoeSabegheId(LongFilter noeSabegheId) {
        this.noeSabegheId = noeSabegheId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AdamKhesaratCriteria that = (AdamKhesaratCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(sales, that.sales) &&
            Objects.equals(mazad, that.mazad) &&
            Objects.equals(faal, that.faal) &&
            Objects.equals(sabegheId, that.sabegheId) &&
            Objects.equals(noeSabegheId, that.noeSabegheId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        sales,
        mazad,
        faal,
        sabegheId,
        noeSabegheId
        );
    }

    @Override
    public String toString() {
        return "AdamKhesaratCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (sales != null ? "sales=" + sales + ", " : "") +
                (mazad != null ? "mazad=" + mazad + ", " : "") +
                (faal != null ? "faal=" + faal + ", " : "") +
                (sabegheId != null ? "sabegheId=" + sabegheId + ", " : "") +
                (noeSabegheId != null ? "noeSabegheId=" + noeSabegheId + ", " : "") +
            "}";
    }

}

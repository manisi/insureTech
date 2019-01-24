package ir.insurance.startup.service.dto;

import java.io.Serializable;
import java.util.Objects;
import ir.insurance.startup.domain.enumeration.SalesSarneshinEnum;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the KhesaratSales entity. This class is used in KhesaratSalesResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /khesarat-sales?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class KhesaratSalesCriteria implements Serializable {
    /**
     * Class for filtering SalesSarneshinEnum
     */
    public static class SalesSarneshinEnumFilter extends Filter<SalesSarneshinEnum> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private SalesSarneshinEnumFilter noe;

    private FloatFilter nerkhKhesarat;

    private BooleanFilter faal;

    private LongFilter sabegheId;

    private LongFilter noeSabegheId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public SalesSarneshinEnumFilter getNoe() {
        return noe;
    }

    public void setNoe(SalesSarneshinEnumFilter noe) {
        this.noe = noe;
    }

    public FloatFilter getNerkhKhesarat() {
        return nerkhKhesarat;
    }

    public void setNerkhKhesarat(FloatFilter nerkhKhesarat) {
        this.nerkhKhesarat = nerkhKhesarat;
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
        final KhesaratSalesCriteria that = (KhesaratSalesCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(noe, that.noe) &&
            Objects.equals(nerkhKhesarat, that.nerkhKhesarat) &&
            Objects.equals(faal, that.faal) &&
            Objects.equals(sabegheId, that.sabegheId) &&
            Objects.equals(noeSabegheId, that.noeSabegheId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        noe,
        nerkhKhesarat,
        faal,
        sabegheId,
        noeSabegheId
        );
    }

    @Override
    public String toString() {
        return "KhesaratSalesCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (noe != null ? "noe=" + noe + ", " : "") +
                (nerkhKhesarat != null ? "nerkhKhesarat=" + nerkhKhesarat + ", " : "") +
                (faal != null ? "faal=" + faal + ", " : "") +
                (sabegheId != null ? "sabegheId=" + sabegheId + ", " : "") +
                (noeSabegheId != null ? "noeSabegheId=" + noeSabegheId + ", " : "") +
            "}";
    }

}

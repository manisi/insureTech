package ir.insurance.startup.web.rest.vm;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.Instant;

/**
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EstelamSalesNerkhVM implements Serializable {

    private final Instant timestamp;
    private final String anvaeKhodro;
    private final String saalSakht;
    private final String vaziatBime;
    private final String onvanKhodro;
    private final String adamKhesarat;
    private final String adamKhesaratSarneshin;
    private final String khesaratSrneshin;
    private final String khesaratSales;
    private final String khesaratSalesmali;
    private final String sherkatBime;
    private final String tarikhEtebar;
    private final String codeyekta;
    private final String modateBimename;
    private final String sabegheKhesarat;
    private final String haghbime;
    private final String takhfif;


    public EstelamSalesNerkhVM(Instant timestamp, String anvaeKhodro, String saalSakht, String vaziatBime, String onvanKhodro, String adamKhesarat, String adamKhesaratSarneshin,
                               String khesaratSrneshin, String khesaratSales, String khesaratSalesmali, String sherkatBime, String tarikhEtebar,
                               String codeyekta, String modateBimename, String sabegheKhesarat, String haghbime, String takhfif) {
        this.timestamp = timestamp;
        this.anvaeKhodro = anvaeKhodro;
        this.saalSakht = saalSakht;
        this.vaziatBime = vaziatBime;
        this.onvanKhodro = onvanKhodro;
        this.adamKhesarat = adamKhesarat;
        this.adamKhesaratSarneshin = adamKhesaratSarneshin;
        this.khesaratSrneshin = khesaratSrneshin;
        this.khesaratSales = khesaratSales;
        this.khesaratSalesmali = khesaratSalesmali;
        this.sherkatBime = sherkatBime;
        this.tarikhEtebar = tarikhEtebar;
        this.codeyekta = codeyekta;
        this.modateBimename = modateBimename;
        this.sabegheKhesarat = sabegheKhesarat;
        this.haghbime = haghbime;
        this.takhfif = takhfif;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getAnvaeKhodro() {
        return anvaeKhodro;
    }

    public String getSaalSakht() {
        return saalSakht;
    }

    public String getVaziatBime() {
        return vaziatBime;
    }

    public String getOnvanKhodro() {
        return onvanKhodro;
    }

    public String getAdamKhesarat() {
        return adamKhesarat;
    }

    public String getAdamKhesaratSarneshin() {
        return adamKhesaratSarneshin;
    }

    public String getKhesaratSrneshin() {
        return khesaratSrneshin;
    }

    public String getKhesaratSales() {
        return khesaratSales;
    }

    public String getKhesaratSalesmali() {
        return khesaratSalesmali;
    }

    public String getSherkatBime() {
        return sherkatBime;
    }

    public String getTarikhEtebar() {
        return tarikhEtebar;
    }

    public String getCodeyekta() {
        return codeyekta;
    }

    public String getModateBimename() {
        return modateBimename;
    }

    public String getSabegheKhesarat() {
        return sabegheKhesarat;
    }

    public String getHaghbime() {
        return haghbime;
    }

    public String getTakhfif() {
        return takhfif;
    }
}

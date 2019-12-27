package ir.insurance.startup.web.rest.vm;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.Instant;

/**
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EstelamSalesNerkhVM implements Serializable {


   public EstelamSalesNerkhVM(){

    }


    private  Instant timestamp;
    private  String anvaeKhodro;
    private  String saalSakht;
    private  String vaziatBime;
    private  String onvanKhodro;
    private  String adamKhesarat;
    private  String adamKhesaratSarneshin;
    private  String khesaratSrneshin;
    private  String khesaratSales;
    private  String khesaratSalesmali;
    private  String sherkatBime;
    private  String tarikhEtebar;
    private  String codeyekta;
    private  String modateBimename;
    private  String sabegheKhesarat;
    private  String haghbimeSalesJani;
    private  String haghbimeSalesSarneshin;
    private  String haghbimeMazad;
    private  String naamSherkat;
    private  String tedadRooz;
    private  String mablaghJani;
    private  String mablaghMali;
    private  String mablagSarneshin;
    private  String takhfif;

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getAnvaeKhodro() {
        return anvaeKhodro;
    }

    public void setAnvaeKhodro(String anvaeKhodro) {
        this.anvaeKhodro = anvaeKhodro;
    }

    public String getSaalSakht() {
        return saalSakht;
    }

    public void setSaalSakht(String saalSakht) {
        this.saalSakht = saalSakht;
    }

    public String getVaziatBime() {
        return vaziatBime;
    }

    public void setVaziatBime(String vaziatBime) {
        this.vaziatBime = vaziatBime;
    }

    public String getOnvanKhodro() {
        return onvanKhodro;
    }

    public void setOnvanKhodro(String onvanKhodro) {
        this.onvanKhodro = onvanKhodro;
    }

    public String getAdamKhesarat() {
        return adamKhesarat;
    }

    public void setAdamKhesarat(String adamKhesarat) {
        this.adamKhesarat = adamKhesarat;
    }

    public String getAdamKhesaratSarneshin() {
        return adamKhesaratSarneshin;
    }

    public void setAdamKhesaratSarneshin(String adamKhesaratSarneshin) {
        this.adamKhesaratSarneshin = adamKhesaratSarneshin;
    }

    public String getKhesaratSrneshin() {
        return khesaratSrneshin;
    }

    public void setKhesaratSrneshin(String khesaratSrneshin) {
        this.khesaratSrneshin = khesaratSrneshin;
    }

    public String getKhesaratSales() {
        return khesaratSales;
    }

    public void setKhesaratSales(String khesaratSales) {
        this.khesaratSales = khesaratSales;
    }

    public String getKhesaratSalesmali() {
        return khesaratSalesmali;
    }

    public void setKhesaratSalesmali(String khesaratSalesmali) {
        this.khesaratSalesmali = khesaratSalesmali;
    }

    public String getSherkatBime() {
        return sherkatBime;
    }

    public void setSherkatBime(String sherkatBime) {
        this.sherkatBime = sherkatBime;
    }

    public String getTarikhEtebar() {
        return tarikhEtebar;
    }

    public void setTarikhEtebar(String tarikhEtebar) {
        this.tarikhEtebar = tarikhEtebar;
    }

    public String getCodeyekta() {
        return codeyekta;
    }

    public void setCodeyekta(String codeyekta) {
        this.codeyekta = codeyekta;
    }

    public String getModateBimename() {
        return modateBimename;
    }

    public void setModateBimename(String modateBimename) {
        this.modateBimename = modateBimename;
    }

    public String getSabegheKhesarat() {
        return sabegheKhesarat;
    }

    public void setSabegheKhesarat(String sabegheKhesarat) {
        this.sabegheKhesarat = sabegheKhesarat;
    }

    public String getTakhfif() {
        return takhfif;
    }

    public void setTakhfif(String takhfif) {
        this.takhfif = takhfif;
    }

    public String getHaghbimeSalesJani() {
        return haghbimeSalesJani;
    }

    public void setHaghbimeSalesJani(String haghbimeSalesJani) {
        this.haghbimeSalesJani = haghbimeSalesJani;
    }

    public String getHaghbimeSalesSarneshin() {
        return haghbimeSalesSarneshin;
    }

    public void setHaghbimeSalesSarneshin(String haghbimeSalesSarneshin) {
        this.haghbimeSalesSarneshin = haghbimeSalesSarneshin;
    }

    public String getHaghbimeMazad() {
        return haghbimeMazad;
    }

    public void setHaghbimeMazad(String haghbimeMazad) {
        this.haghbimeMazad = haghbimeMazad;
    }

    public String getTedadRooz() {
        return tedadRooz;
    }

    public void setTedadRooz(String tedadRooz) {
        this.tedadRooz = tedadRooz;
    }

    public String getNaamSherkat() {
        return naamSherkat;
    }

    public void setNaamSherkat(String naamSherkat) {
        this.naamSherkat = naamSherkat;
    }

    public String getMablaghJani() {
        return mablaghJani;
    }

    public void setMablaghJani(String mablaghJani) {
        this.mablaghJani = mablaghJani;
    }

    public String getMablaghMali() {
        return mablaghMali;
    }

    public void setMablaghMali(String mablaghMali) {
        this.mablaghMali = mablaghMali;
    }

    public String getMablagSarneshin() {
        return mablagSarneshin;
    }

    public void setMablagSarneshin(String mablagSarneshin) {
        this.mablagSarneshin = mablagSarneshin;
    }
    //    public EstelamSalesNerkhVM(Instant timestamp, String anvaeKhodro, String saalSakht, String vaziatBime, String onvanKhodro, String adamKhesarat, String adamKhesaratSarneshin,
//                               String khesaratSrneshin, String khesaratSales, String khesaratSalesmali, String sherkatBime, String tarikhEtebar,
//                               String codeyekta, String modateBimename, String sabegheKhesarat, String haghbime, String takhfif) {
//        this.timestamp = timestamp;
//        this.anvaeKhodro = anvaeKhodro;
//        this.saalSakht = saalSakht;
//        this.vaziatBime = vaziatBime;
//        this.onvanKhodro = onvanKhodro;
//        this.adamKhesarat = adamKhesarat;
//        this.adamKhesaratSarneshin = adamKhesaratSarneshin;
//        this.khesaratSrneshin = khesaratSrneshin;
//        this.khesaratSales = khesaratSales;
//        this.khesaratSalesmali = khesaratSalesmali;
//        this.sherkatBime = sherkatBime;
//        this.tarikhEtebar = tarikhEtebar;
//        this.codeyekta = codeyekta;
//        this.modateBimename = modateBimename;
//        this.sabegheKhesarat = sabegheKhesarat;
//        this.haghbime = haghbime;
//        this.takhfif = takhfif;
//    }
//
//    public Instant getTimestamp() {
//        return timestamp;
//    }
//
//    public String getAnvaeKhodro() {
//        return anvaeKhodro;
//    }
//
//    public String getSaalSakht() {
//        return saalSakht;
//    }
//
//    public String getVaziatBime() {
//        return vaziatBime;
//    }
//
//    public String getOnvanKhodro() {
//        return onvanKhodro;
//    }
//
//    public String getAdamKhesarat() {
//        return adamKhesarat;
//    }
//
//    public String getAdamKhesaratSarneshin() {
//        return adamKhesaratSarneshin;
//    }
//
//    public String getKhesaratSrneshin() {
//        return khesaratSrneshin;
//    }
//
//    public String getKhesaratSales() {
//        return khesaratSales;
//    }
//
//    public String getKhesaratSalesmali() {
//        return khesaratSalesmali;
//    }
//
//    public String getSherkatBime() {
//        return sherkatBime;
//    }
//
//    public String getTarikhEtebar() {
//        return tarikhEtebar;
//    }
//
//    public String getCodeyekta() {
//        return codeyekta;
//    }
//
//    public String getModateBimename() {
//        return modateBimename;
//    }
//
//    public String getSabegheKhesarat() {
//        return sabegheKhesarat;
//    }
//
//    public String getHaghbime() {
//        return haghbime;
//    }
//
//    public String getTakhfif() {
//        return takhfif;
//    }
}

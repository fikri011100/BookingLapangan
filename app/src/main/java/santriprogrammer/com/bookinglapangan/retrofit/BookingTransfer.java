
package santriprogrammer.com.bookinglapangan.retrofit;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class BookingTransfer {

    @SerializedName("id_booking")
    private String mIdBooking;
    @SerializedName("id_konfirmasi_booking")
    private String mIdKonfirmasiBooking;
    @SerializedName("nama")
    private String mNama;
    @SerializedName("nama_bank")
    private String mNamaBank;
    @SerializedName("no_rek")
    private String mNoRek;
    @SerializedName("nominal")
    private String mNominal;
    @SerializedName("photo_struk")
    private String mPhotoStruk;

    public String getIdBooking() {
        return mIdBooking;
    }

    public void setIdBooking(String idBooking) {
        mIdBooking = idBooking;
    }

    public String getIdKonfirmasiBooking() {
        return mIdKonfirmasiBooking;
    }

    public void setIdKonfirmasiBooking(String idKonfirmasiBooking) {
        mIdKonfirmasiBooking = idKonfirmasiBooking;
    }

    public String getNama() {
        return mNama;
    }

    public void setNama(String nama) {
        mNama = nama;
    }

    public String getNamaBank() {
        return mNamaBank;
    }

    public void setNamaBank(String namaBank) {
        mNamaBank = namaBank;
    }

    public String getNoRek() {
        return mNoRek;
    }

    public void setNoRek(String noRek) {
        mNoRek = noRek;
    }

    public String getNominal() {
        return mNominal;
    }

    public void setNominal(String nominal) {
        mNominal = nominal;
    }

    public String getPhotoStruk() {
        return mPhotoStruk;
    }

    public void setPhotoStruk(String photoStruk) {
        mPhotoStruk = photoStruk;
    }

}

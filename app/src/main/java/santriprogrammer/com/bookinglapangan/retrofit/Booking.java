
package santriprogrammer.com.bookinglapangan.retrofit;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Booking {

    @SerializedName("booking_category")
    private String mBookingCategory;
    @SerializedName("booking_dates")
    private String mBookingDates;
    @SerializedName("booking_hour")
    private String mBookingHour;
    @SerializedName("booking_hour_until")
    private String mBookingHourUntil;
    @SerializedName("booking_id")
    private String mBookingId;
    @SerializedName("booking_place")
    private String mBookingPlace;
    @SerializedName("booking_price")
    private String mBookingPrice;
    @SerializedName("booking_status")
    private String mBookingStatus;
    @SerializedName("booking_user_email")
    private String mBookingUserEmail;

    public String getBookingCategory() {
        return mBookingCategory;
    }

    public void setBookingCategory(String bookingCategory) {
        mBookingCategory = bookingCategory;
    }

    public String getBookingDates() {
        return mBookingDates;
    }

    public void setBookingDates(String bookingDates) {
        mBookingDates = bookingDates;
    }

    public String getBookingHour() {
        return mBookingHour;
    }

    public void setBookingHour(String bookingHour) {
        mBookingHour = bookingHour;
    }

    public String getBookingHourUntil() {
        return mBookingHourUntil;
    }

    public void setBookingHourUntil(String bookingHourUntil) {
        mBookingHourUntil = bookingHourUntil;
    }

    public String getBookingId() {
        return mBookingId;
    }

    public void setBookingId(String bookingId) {
        mBookingId = bookingId;
    }

    public String getBookingPlace() {
        return mBookingPlace;
    }

    public void setBookingPlace(String bookingPlace) {
        mBookingPlace = bookingPlace;
    }

    public String getBookingPrice() {
        return mBookingPrice;
    }

    public void setBookingPrice(String bookingPrice) {
        mBookingPrice = bookingPrice;
    }

    public String getBookingStatus() {
        return mBookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        mBookingStatus = bookingStatus;
    }

    public String getBookingUserEmail() {
        return mBookingUserEmail;
    }

    public void setBookingUserEmail(String bookingUserEmail) {
        mBookingUserEmail = bookingUserEmail;
    }

}


package santriprogrammer.com.bookinglapangan.retrofit;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PojoBookingAdmin {

    @SerializedName("booking")
    private List<Booking> mBooking;
    @SerializedName("status")
    private Boolean mStatus;

    public List<Booking> getBooking() {
        return mBooking;
    }

    public void setBooking(List<Booking> booking) {
        mBooking = booking;
    }

    public Boolean getStatus() {
        return mStatus;
    }

    public void setStatus(Boolean status) {
        mStatus = status;
    }

}

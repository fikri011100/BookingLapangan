
package santriprogrammer.com.bookinglapangan.retrofit;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PojoTransferBooking {

    @SerializedName("bookingTransfer")
    private List<BookingTransfer> mBookingTransfer;
    @SerializedName("status")
    private Boolean mStatus;

    public List<BookingTransfer> getBookingTransfer() {
        return mBookingTransfer;
    }

    public void setBookingTransfer(List<BookingTransfer> bookingTransfer) {
        mBookingTransfer = bookingTransfer;
    }

    public Boolean getStatus() {
        return mStatus;
    }

    public void setStatus(Boolean status) {
        mStatus = status;
    }

}

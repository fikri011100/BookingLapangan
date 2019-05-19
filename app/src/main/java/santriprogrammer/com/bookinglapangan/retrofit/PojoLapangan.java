
package santriprogrammer.com.bookinglapangan.retrofit;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PojoLapangan {

    @SerializedName("lapangan")
    private List<Lapangan> mLapangan;
    @SerializedName("status")
    private Boolean mStatus;

    public List<Lapangan> getLapangan() {
        return mLapangan;
    }

    public void setLapangan(List<Lapangan> lapangan) {
        mLapangan = lapangan;
    }

    public Boolean getStatus() {
        return mStatus;
    }

    public void setStatus(Boolean status) {
        mStatus = status;
    }

}

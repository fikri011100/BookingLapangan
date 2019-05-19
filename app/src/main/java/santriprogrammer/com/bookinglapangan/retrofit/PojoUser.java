
package santriprogrammer.com.bookinglapangan.retrofit;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PojoUser {

    @SerializedName("status")
    private Boolean mStatus;
    @SerializedName("user")
    private List<User> mUser;

    public Boolean getStatus() {
        return mStatus;
    }

    public void setStatus(Boolean status) {
        mStatus = status;
    }

    public List<User> getUser() {
        return mUser;
    }

    public void setUser(List<User> user) {
        mUser = user;
    }

}

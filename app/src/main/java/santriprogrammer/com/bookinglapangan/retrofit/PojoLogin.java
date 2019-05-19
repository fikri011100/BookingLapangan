
package santriprogrammer.com.bookinglapangan.retrofit;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PojoLogin {

    @SerializedName("msg")
    private String mMsg;
    @SerializedName("result")
    private Boolean mResult;
    @SerializedName("user")
    private List<User> mUser;

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
        mMsg = msg;
    }

    public Boolean getResult() {
        return mResult;
    }

    public void setResult(Boolean result) {
        mResult = result;
    }

    public List<User> getUser() {
        return mUser;
    }

    public void setUser(List<User> user) {
        mUser = user;
    }

}

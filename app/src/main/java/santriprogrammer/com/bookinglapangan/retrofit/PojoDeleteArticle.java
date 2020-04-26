
package santriprogrammer.com.bookinglapangan.retrofit;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PojoDeleteArticle {

    @SerializedName("hasil")
    private String mHasil;
    @SerializedName("msg")
    private String mMsg;

    public String getHasil() {
        return mHasil;
    }

    public void setHasil(String hasil) {
        mHasil = hasil;
    }

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
        mMsg = msg;
    }

}

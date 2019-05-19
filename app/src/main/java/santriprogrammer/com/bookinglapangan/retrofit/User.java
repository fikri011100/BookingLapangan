
package santriprogrammer.com.bookinglapangan.retrofit;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class User {

    @SerializedName("email")
    private String mEmail;
    @SerializedName("no_telp")
    private String mNoTelp;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("user_id")
    private String mUserId;
    @SerializedName("username")
    private String mUsername;

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getNoTelp() {
        return mNoTelp;
    }

    public void setNoTelp(String noTelp) {
        mNoTelp = noTelp;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

}

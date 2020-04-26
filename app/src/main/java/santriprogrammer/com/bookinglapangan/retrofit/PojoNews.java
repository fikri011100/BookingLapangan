
package santriprogrammer.com.bookinglapangan.retrofit;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PojoNews {

    @SerializedName("news")
    private List<News> mNews;
    @SerializedName("status")
    private Boolean mStatus;

    public List<News> getNews() {
        return mNews;
    }

    public void setNews(List<News> news) {
        mNews = news;
    }

    public Boolean getStatus() {
        return mStatus;
    }

    public void setStatus(Boolean status) {
        mStatus = status;
    }

}

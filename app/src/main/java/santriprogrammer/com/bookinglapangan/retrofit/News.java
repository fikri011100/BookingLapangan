
package santriprogrammer.com.bookinglapangan.retrofit;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class News {

    @SerializedName("id_news")
    private String mIdNews;
    @SerializedName("news_desc")
    private String mNewsDesc;
    @SerializedName("news_image")
    private String mNewsImage;
    @SerializedName("news_title")
    private String mNewsTitle;

    public String getIdNews() {
        return mIdNews;
    }

    public void setIdNews(String idNews) {
        mIdNews = idNews;
    }

    public String getNewsDesc() {
        return mNewsDesc;
    }

    public void setNewsDesc(String newsDesc) {
        mNewsDesc = newsDesc;
    }

    public String getNewsImage() {
        return mNewsImage;
    }

    public void setNewsImage(String newsImage) {
        mNewsImage = newsImage;
    }

    public String getNewsTitle() {
        return mNewsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        mNewsTitle = newsTitle;
    }

}

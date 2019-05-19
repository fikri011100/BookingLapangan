
package santriprogrammer.com.bookinglapangan.retrofit;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Lapangan {

    @SerializedName("field_id")
    private String mFieldId;
    @SerializedName("field_image")
    private String mFieldImage;
    @SerializedName("field_name")
    private String mFieldName;

    public String getFieldId() {
        return mFieldId;
    }

    public void setFieldId(String fieldId) {
        mFieldId = fieldId;
    }

    public String getFieldImage() {
        return mFieldImage;
    }

    public void setFieldImage(String fieldImage) {
        mFieldImage = fieldImage;
    }

    public String getFieldName() {
        return mFieldName;
    }

    public void setFieldName(String fieldName) {
        mFieldName = fieldName;
    }

}

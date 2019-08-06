package santriprogrammer.com.bookinglapangan;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StringHelper {
    public static String pattern2 = "###,###";

    public static String convertFormatPrice(long priceValue, String pattern) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat(pattern);
            decimalFormat.applyPattern(pattern);
            return "Rp " + decimalFormat.format(priceValue);
        } catch (Exception e) {
            return "Rp 0";
        }
    }

    public static String convertFormatPrice(String priceValue) {
        return convertFormatPrice(Long.parseLong(priceValue), pattern2);
    }

    public static String formatDate(String value) {
        return formatingDateFromString("dd-MM-yyyy", " EEEE, dd MMM yyyy", value);
    }

    public static String formatingDateFromString(String fromFormat, String toFormat,
                                                 String stringDate) {
        //yyyy-MM-dd'T'HH:mm:ss.SSSZ
        SimpleDateFormat format = new SimpleDateFormat(fromFormat, Locale.getDefault());
        try {
            Date newDate = format.parse(stringDate);
            format = new SimpleDateFormat(toFormat, Locale.getDefault());
            return format.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return stringDate;
    }

}

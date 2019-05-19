package santriprogrammer.com.bookinglapangan;

import android.app.Activity;

import com.tapadoo.alerter.Alerter;

public class Utils {

    public static void failAlert(Activity act, String message){
        Alerter.create(act)
                .setTitle("Gagal")
                .setText(message)
                .setDuration(2000)
                .setBackgroundColorRes(R.color.red_bg)
                .enableSwipeToDismiss()
                .show();
    }

}

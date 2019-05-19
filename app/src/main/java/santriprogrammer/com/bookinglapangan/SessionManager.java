package santriprogrammer.com.bookinglapangan;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class SessionManager {
    private static String TAG = SessionManager.class.getSimpleName();

    SharedPreferences pref, adminPref, usernamePref, userIdPref;

    Editor editor, editorAdmin, editorUsername, editorUserId;
    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "BookingLapanganLogin";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_IS_ADMIN = "isAdmin";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_USER_ID = "user_id";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        adminPref = _context.getSharedPreferences(KEY_IS_ADMIN, PRIVATE_MODE);
        usernamePref = _context.getSharedPreferences(KEY_USERNAME, PRIVATE_MODE);
        userIdPref = _context.getSharedPreferences(KEY_USER_ID, PRIVATE_MODE);
        editor = pref.edit();
        editorAdmin = adminPref.edit();
        editorUsername = usernamePref.edit();
        editorUserId = userIdPref.edit();

    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.commit();
        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public void setAdmin(boolean isAdmin) {
        editorAdmin.putBoolean(KEY_IS_ADMIN, isAdmin);
        editorAdmin.commit();
        Log.d(TAG, "admin session modified!");
    }

    public boolean isAdmin() {
        return adminPref.getBoolean(KEY_IS_ADMIN, false);
    }

    public void setUsername(String username) {
        editorUsername.putString(KEY_USERNAME, username);
        editorUsername.commit();
    }

    public String getUsername() {
        return usernamePref.getString(KEY_USERNAME, "");
    }

    public void setUserID(String userID) {
        editorUserId.putString(KEY_USER_ID, userID);
        editorUserId.commit();
    }

    public String getUserID() {
        return userIdPref.getString(KEY_USER_ID, "");
    }
}

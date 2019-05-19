package santriprogrammer.com.bookinglapangan;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import santriprogrammer.com.bookinglapangan.home.HomeFragment;
import santriprogrammer.com.bookinglapangan.order.MenuOrderFragment;
import santriprogrammer.com.bookinglapangan.ticket.TicketFragment;
import santriprogrammer.com.bookinglapangan.user.UserFragment;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    Fragment fragment;
    FragmentManager fragmentManager;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(getApplicationContext());
        fragmentManager = getSupportFragmentManager();
        fragment = new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.framelayout, fragment).commit();

        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    fragmentManager.beginTransaction().replace(R.id.framelayout, fragment).commit();
                    return true;
                case R.id.navigation_dashboard:
                    fragment = new MenuOrderFragment();
                    fragmentManager.beginTransaction().replace(R.id.framelayout, fragment).commit();
                    return true;
                case R.id.navigation_notifications:
                    fragment = new TicketFragment();
                    fragmentManager.beginTransaction().replace(R.id.framelayout, fragment).commit();
                    return true;
                case R.id.navigation_users:
                    fragment = new UserFragment();
                    fragmentManager.beginTransaction().replace(R.id.framelayout, fragment).commit();
                    return true;
            }
            return false;
        };

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}

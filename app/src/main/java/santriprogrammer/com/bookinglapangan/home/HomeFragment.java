package santriprogrammer.com.bookinglapangan.home;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import santriprogrammer.com.bookinglapangan.ConfirmationActivity;
import santriprogrammer.com.bookinglapangan.R;
import santriprogrammer.com.bookinglapangan.SessionManager;
import santriprogrammer.com.bookinglapangan.announcement.AnnouncementActivity;
import santriprogrammer.com.bookinglapangan.news.NewsActivity;
import santriprogrammer.com.bookinglapangan.order.MenuOrderFragment;
import santriprogrammer.com.bookinglapangan.retrofit.APIClient;
import santriprogrammer.com.bookinglapangan.retrofit.APIInterface;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    @BindView(R.id.imagecarabook)
    ConstraintLayout imagecarabook;
    @BindView(R.id.imagecarapembayaran)
    ConstraintLayout imagecarapembayaran;
    @BindView(R.id.imagecarapembatalan)
    ConstraintLayout imagecarapembatalan;
    @BindView(R.id.imagebiayabooking)
    ConstraintLayout imagebiayabooking;
    Unbinder unbinder;
    FrameLayout framelayout;
    Fragment fragment;
    APIInterface apiInterface;
    FragmentManager fragmentManager;
    HomeAdapter adapter;
    @BindView(R.id.cons1)
    ConstraintLayout cons1;
    @BindView(R.id.image_booking)
    ImageView imageBooking;
    @BindView(R.id.text_booking)
    TextView textBooking;
    String username;

    public HomeFragment() {
        // Required empty public constructor
    }

    SessionManager sessionManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(getActivity());
        fragmentManager = getActivity().getSupportFragmentManager();
        getActivity().setTitle("Home");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        username = sessionManager.getUsername();
        if (sessionManager.getUserID().equals("0")) {
//            cons1.setVisibility(View.GONE);
            textBooking.setText("Confirmation");
            imageBooking.setImageResource(R.drawable.ic_verified_user_black_24dp);
        } else {
            textBooking.setText("Booking");
            imageBooking.setImageResource(R.drawable.ic_local_grocery_store_black_24dp);
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.imagecarabook)
    public void onImagecarabookClicked() {
        if (sessionManager.getUserID().equals("0")) {
            startActivity(new Intent(getActivity(), ConfirmationActivity.class));
        } else {
            fragment = new MenuOrderFragment();
            fragmentManager.beginTransaction().replace(R.id.framelayout, fragment).commit();
        }
    }

    @OnClick(R.id.imagecarapembayaran)
    public void onImagecarapembayaranClicked() {
        startActivity(new Intent(getActivity(), NewsActivity.class));
    }

    @OnClick(R.id.imagecarapembatalan)
    public void onImagecarapembatalanClicked() {
        startActivity(new Intent(getActivity(), AnnouncementActivity.class));
    }

    @OnClick(R.id.imagebiayabooking)
    public void onImagebiayabookingClicked() {
        AlertDialog.Builder alertdialogBuilder = new AlertDialog.Builder(getActivity());
        alertdialogBuilder.setTitle("Biaya Booking");
        alertdialogBuilder.setMessage(R.string.biayabookingcaption);
        alertdialogBuilder.setPositiveButton("OK, Saya Mengerti", (dialog, which) -> dialog.dismiss());
        alertdialogBuilder.show();
    }
}

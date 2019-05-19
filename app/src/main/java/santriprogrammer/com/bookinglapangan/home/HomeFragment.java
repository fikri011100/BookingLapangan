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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import santriprogrammer.com.bookinglapangan.R;
import santriprogrammer.com.bookinglapangan.SessionManager;
import santriprogrammer.com.bookinglapangan.UploadLapanganActivity;
import santriprogrammer.com.bookinglapangan.order.MenuOrderFragment;
import santriprogrammer.com.bookinglapangan.retrofit.APIClient;
import santriprogrammer.com.bookinglapangan.retrofit.APIInterface;
import santriprogrammer.com.bookinglapangan.retrofit.Booking;
import santriprogrammer.com.bookinglapangan.retrofit.PojoBookingAdmin;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    @BindView(R.id.textviewtop)
    TextView textviewtop;
    @BindView(R.id.imagecarabook)
    ConstraintLayout imagecarabook;
    @BindView(R.id.imagecarapembayaran)
    ConstraintLayout imagecarapembayaran;
    @BindView(R.id.imagecarapembatalan)
    ConstraintLayout imagecarapembatalan;
    @BindView(R.id.imagebiayabooking)
    ConstraintLayout imagebiayabooking;
    @BindView(R.id.buttonOrder)
    ConstraintLayout buttonOrder;
    Unbinder unbinder;
    FrameLayout framelayout;
    Fragment fragment;
    APIInterface apiInterface;
    FragmentManager fragmentManager;
    @BindView(R.id.recyclerview_list_booking)
    RecyclerView recyclerviewListBooking;
    @BindView(R.id.fab)
    ImageView fab;
    HomeAdapter adapter;
    @BindView(R.id.cons1)
    ConstraintLayout cons1;

    public HomeFragment() {
        // Required empty public constructor
    }

    SessionManager sessionManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(getActivity());
        fragmentManager = getActivity().getSupportFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        textviewtop.setText("Selamat Datang " + sessionManager.getUsername());
        if (sessionManager.getUserID().equals("0")) {
            recyclerviewListBooking.setVisibility(View.VISIBLE);
            fab.setVisibility(View.VISIBLE);
            cons1.setVisibility(View.GONE);
            buttonOrder.setVisibility(View.GONE);
            apiInterface = APIClient.getRetrofit().create(APIInterface.class);
            recyclerviewListBooking.setLayoutManager(new LinearLayoutManager(getActivity()));
            getData();
        } else {
            recyclerviewListBooking.setVisibility(View.GONE);
            fab.setVisibility(View.GONE);
            cons1.setVisibility(View.VISIBLE);
            buttonOrder.setVisibility(View.VISIBLE);
        }
        fab.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), UploadLapanganActivity.class));
        });
        return view;
    }

    private void getData() {
        Call<PojoBookingAdmin> call = apiInterface.getBookingAdmin(sessionManager.getUsername());
        call.enqueue(new Callback<PojoBookingAdmin>() {
            @Override
            public void onResponse(Call<PojoBookingAdmin> call, Response<PojoBookingAdmin> response) {
                final List<Booking> bookings = response.body().getBooking();
                adapter = new HomeAdapter(bookings, getContext());
                recyclerviewListBooking.setAdapter(adapter);
                Log.i("response", response.body().toString());
            }

            @Override
            public void onFailure(Call<PojoBookingAdmin> call, Throwable t) {
                Toast.makeText(getActivity(), "Maaf, koneksi anda tidak stabil", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.imagecarabook)
    public void onImagecarabookClicked() {
        AlertDialog.Builder alertdialogBuilder = new AlertDialog.Builder(getActivity());
        alertdialogBuilder.setTitle("Cara Booking");
        alertdialogBuilder.setMessage(R.string.carabookingcaption);
        alertdialogBuilder.setPositiveButton("OK, Saya Mengerti", (dialog, which) -> dialog.dismiss());
        alertdialogBuilder.show();
    }

    @OnClick(R.id.imagecarapembayaran)
    public void onImagecarapembayaranClicked() {
        AlertDialog.Builder alertdialogBuilder = new AlertDialog.Builder(getActivity());
        alertdialogBuilder.setTitle("Cara Pembayaran");
        alertdialogBuilder.setMessage(R.string.carapembayarancaption);
        alertdialogBuilder.setPositiveButton("OK, Saya Mengerti", (dialog, which) -> dialog.dismiss());
        alertdialogBuilder.show();
    }

    @OnClick(R.id.imagecarapembatalan)
    public void onImagecarapembatalanClicked() {
        AlertDialog.Builder alertdialogBuilder = new AlertDialog.Builder(getActivity());
        alertdialogBuilder.setTitle("Cara Pembatalan & Pergantian Jadwal");
        alertdialogBuilder.setMessage(R.string.carapembatalancaption);
        alertdialogBuilder.setPositiveButton("OK, Saya Mengerti", (dialog, which) -> dialog.dismiss());
        alertdialogBuilder.show();
    }

    @OnClick(R.id.imagebiayabooking)
    public void onImagebiayabookingClicked() {
        AlertDialog.Builder alertdialogBuilder = new AlertDialog.Builder(getActivity());
        alertdialogBuilder.setTitle("Biaya Booking");
        alertdialogBuilder.setMessage(R.string.biayabookingcaption);
        alertdialogBuilder.setPositiveButton("OK, Saya Mengerti", (dialog, which) -> dialog.dismiss());
        alertdialogBuilder.show();
    }

    @OnClick(R.id.buttonOrder)
    public void onButtonOrderClicked() {
        fragment = new MenuOrderFragment();
        fragmentManager.beginTransaction().replace(R.id.framelayout, fragment).commit();
    }
}

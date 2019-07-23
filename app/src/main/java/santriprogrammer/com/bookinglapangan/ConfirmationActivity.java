package santriprogrammer.com.bookinglapangan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import santriprogrammer.com.bookinglapangan.home.HomeAdapter;
import santriprogrammer.com.bookinglapangan.retrofit.APIClient;
import santriprogrammer.com.bookinglapangan.retrofit.APIInterface;
import santriprogrammer.com.bookinglapangan.retrofit.Booking;
import santriprogrammer.com.bookinglapangan.retrofit.PojoBookingAdmin;

public class ConfirmationActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview_confirmation)
    RecyclerView recyclerviewConfirmation;
    APIInterface apiInterface;
    HomeAdapter adapter;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Konfirmasi");
        sessionManager = new SessionManager(getApplicationContext());
        recyclerviewConfirmation.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        apiInterface = APIClient.getRetrofit().create(APIInterface.class);
        getData();
    }

    private void getData() {
        Call<PojoBookingAdmin> call = apiInterface.getBookingAdmin(sessionManager.getUsername());
        call.enqueue(new Callback<PojoBookingAdmin>() {
            @Override
            public void onResponse(Call<PojoBookingAdmin> call, Response<PojoBookingAdmin> response) {
                final List<Booking> bookings = response.body().getBooking();
                adapter = new HomeAdapter(bookings, getApplicationContext());
                recyclerviewConfirmation.setAdapter(adapter);
                if (adapter.getItemCount() == 0) {
                    Toast.makeText(getApplicationContext(), "Tidak ada Booking", Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
                Log.i("response", response.body().toString());
            }

            @Override
            public void onFailure(Call<PojoBookingAdmin> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Maaf, Koneksi anda tidak stabil", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

package santriprogrammer.com.bookinglapangan.announcement;

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
import santriprogrammer.com.bookinglapangan.R;
import santriprogrammer.com.bookinglapangan.retrofit.APIClient;
import santriprogrammer.com.bookinglapangan.retrofit.APIInterface;
import santriprogrammer.com.bookinglapangan.retrofit.Booking;
import santriprogrammer.com.bookinglapangan.retrofit.PojoAllBooking;

public class AnnouncementActivity extends AppCompatActivity {

    APIInterface apiInterface;
    @BindView(R.id.recycler_announcement)
    RecyclerView recyclerAnnouncement;
    AdapterAnnouncement adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Information");
        apiInterface = APIClient.getRetrofit().create(APIInterface.class);
        recyclerAnnouncement.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getData();
    }

    private void getData() {
        Call<PojoAllBooking> call = apiInterface.getAllBooking();
        call.enqueue(new Callback<PojoAllBooking>() {
            @Override
            public void onResponse(Call<PojoAllBooking> call, Response<PojoAllBooking> response) {
                try {
                    final List<Booking> bookings = response.body().getBooking();
                    adapter = new AdapterAnnouncement(bookings, getApplicationContext());
                    recyclerAnnouncement.setAdapter(adapter);
                    if (adapter.getItemCount() == 0) {
                        Toast.makeText(getApplicationContext(), "Tidak ada Berita", Toast.LENGTH_SHORT).show();
                    }
                    adapter.notifyDataSetChanged();
                    Log.i("response", response.body().toString());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Tidak ada Berita", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PojoAllBooking> call, Throwable t) {
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

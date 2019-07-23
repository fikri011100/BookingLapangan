package santriprogrammer.com.bookinglapangan.books;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import santriprogrammer.com.bookinglapangan.EmptyRecyclerview;
import santriprogrammer.com.bookinglapangan.R;
import santriprogrammer.com.bookinglapangan.SessionManager;
import santriprogrammer.com.bookinglapangan.dialogfragment.TanggalFragment;
import santriprogrammer.com.bookinglapangan.eventbus.TanggalEventBus;
import santriprogrammer.com.bookinglapangan.retrofit.APIClient;
import santriprogrammer.com.bookinglapangan.retrofit.APIInterface;
import santriprogrammer.com.bookinglapangan.retrofit.Lapangan;
import santriprogrammer.com.bookinglapangan.retrofit.PojoLapangan;

public class BookingActivity extends AppCompatActivity {

    @BindView(R.id.textview_tanggal)
    TextView textviewTanggal;
    @BindView(R.id.image_tanggal)
    ImageView imageTanggal;
    @BindView(R.id.textview_total)
    TextView textviewTotal;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    SimpleDateFormat dateFormat;
    String tanggal, jam, kategori;
    int totaljam, totalPrice;
    APIInterface apiInterface;
    BookingAdapter bookingAdapter;
    ProgressDialog pDialog;
    SessionManager sessionManager;
    List<Lapangan> lapangans;
    Calendar newDate;
    @BindView(R.id.recyclerview_booking)
    EmptyRecyclerview recyclerviewBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(this);
        dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        kategori = getIntent().getStringExtra("kategori");
        totaljam = 1;
        sessionManager = new SessionManager(getApplicationContext());
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        recyclerviewBooking.setLayoutManager(new LinearLayoutManager(this));
        apiInterface = APIClient.getRetrofit().create(APIInterface.class);
        totalPrice = 75000 * totaljam;
        textviewTotal.setText("Harga total : " + totalPrice);
        imageTanggal.setOnClickListener(v -> showPicker());
    }

    @Subscribe
    public void getTanggal(TanggalEventBus.EventBus bus) {
        tanggal = bus.getTanggal();
        jam = bus.getJam();
        totaljam = bus.getTotalJam();
        int totalHarga;
        if (jam.equals("06") || jam.equals("07") || jam.equals("08") || jam.equals("09") || jam.equals("10")) {
            totalHarga= 50000 * totaljam;
            getData(tanggal, jam, totaljam, totalHarga);
            textviewTotal.setText("Harga Total : " + totalHarga);
        } else if ( jam.equals("11") || jam.equals("12") || jam.equals("13") || jam.equals("14") || jam.equals("15") || jam.equals("16")) {
            totalHarga= 75000 * totaljam;
            getData(tanggal, jam, totaljam, totalHarga);
            textviewTotal.setText("Harga Total : " + totalHarga);
        } else if (jam.equals("17") || jam.equals("18") || jam.equals("19") || jam.equals("20") || jam.equals("21") || jam.equals("22") || jam.equals("23") || jam.equals("00")){
            totalHarga= 100000 * totaljam;
            getData(tanggal, jam, totaljam, totalHarga);
            textviewTotal.setText("Harga Total : " + totalHarga);
        } else if (jam.equals("01") || jam.equals("02") || jam.equals("03") || jam.equals("04") || jam.equals("05")) {
            totalHarga= 75000 * totaljam;
            getData(tanggal, jam, totaljam, totalHarga);
            textviewTotal.setText("Harga Total : " + totalHarga);
        } else {
            Toast.makeText(this, "Mohon pilih jam terlebih dahulu", Toast.LENGTH_SHORT).show();
        }
        recyclerviewBooking.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void getData(String tanggal, String jam, int totaljam, int totalHarga) {
        Call<PojoLapangan> call = apiInterface.getLapangan();
        call.enqueue(new Callback<PojoLapangan>() {
            @Override
            public void onResponse(Call<PojoLapangan> call, Response<PojoLapangan> response) {
                lapangans = response.body().getLapangan();
                bookingAdapter = new BookingAdapter(lapangans, getApplicationContext(), tanggal, jam + ":00", totaljam, sessionManager.getUsername(), kategori, totalHarga);
                recyclerviewBooking.setAdapter(bookingAdapter);
                Log.i("response", response.body().toString());
            }

            @Override
            public void onFailure(Call<PojoLapangan> call, Throwable t) {
                Toast.makeText(BookingActivity.this, "Maaf, koneksi anda tidak stabil", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDateDialog() {
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            newDate = Calendar.getInstance();
            newDate.set(year, month, dayOfMonth);
            tanggal = dateFormat.format(newDate.getTime());
            bookingAdapter.notifyDataSetChanged();

            textviewTanggal.setText(tanggal);
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void showPicker() {
        FragmentActivity activity = BookingActivity.this;
        FragmentManager fm = activity.getSupportFragmentManager();
        TanggalFragment alert = new TanggalFragment();
        alert.show(fm, "");
    }

    private void showTimeDialog() {
        Calendar calendar = Calendar.getInstance();

        timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
            String hour = hourOfDay + "";
            if (hourOfDay < 10) {
                hour = "0" + hourOfDay;
            }
            String min = minute + "";
            if (minute < 10)
                min = "0" + minute;
            jam = hour + ":" + min;


        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), DateFormat.is24HourFormat(this));
        timePickerDialog.show();
        recyclerviewBooking.setVisibility(View.VISIBLE);
        setJam(jam);
    }

    private void setJam(String jam) {
        this.jam = jam;
    }

    private String getJam() {
        return jam;
    }
}

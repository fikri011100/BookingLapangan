package santriprogrammer.com.bookinglapangan.ticket;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
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
import santriprogrammer.com.bookinglapangan.retrofit.PojoDeleteTicket;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {

    List<Booking> list;
    Context context;

    public TicketAdapter(List<Booking> bookings, Context activity) {
        this.list = bookings;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ticket, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Booking itemList = list.get(i);
        if (itemList.getBookingStatus().equals("1")) {
            viewHolder.buttonBatal.setVisibility(View.GONE);
            viewHolder.textviewCanceled.setVisibility(View.VISIBLE);
        } else if (itemList.getBookingStatus().equals("2")) {
            viewHolder.buttonBatal.setVisibility(View.GONE);
            viewHolder.textviewCanceled.setVisibility(View.VISIBLE);
            viewHolder.textviewCanceled.setText("Pesanan sudah Dikonfirmasi");
        } else if (itemList.getBookingStatus().equals("0")) {
            viewHolder.buttonBatal.setVisibility(View.VISIBLE);
            viewHolder.textviewCanceled.setVisibility(View.GONE);
        }
        viewHolder.textviewHarga.setText(itemList.getBookingPrice());
        viewHolder.textviewTanggal.setText(itemList.getBookingDates());
        viewHolder.textviewLapangan.setText(itemList.getBookingPlace());
        viewHolder.textviewKategori.setText(itemList.getBookingCategory());
        viewHolder.textviewJam.setText(itemList.getBookingHour());
        viewHolder.textviewDurasi.setText(itemList.getBookingHourUntil() + " Jam");
        viewHolder.buttonBatal.setOnClickListener(v -> {
            APIInterface req = APIClient.getRetrofit().create(APIInterface.class);
            final Call<PojoDeleteTicket> call = req.deleteTicket(itemList.getBookingId(), "1");
            call.enqueue(new Callback<PojoDeleteTicket>() {
                @Override
                public void onResponse(Call<PojoDeleteTicket> call, Response<PojoDeleteTicket> response) {
                    Toast.makeText(context, "Pemesanan berhasil dihapus", Toast.LENGTH_SHORT).show();
                    viewHolder.buttonBatal.setVisibility(View.GONE);
                    viewHolder.textviewCanceled.setVisibility(View.VISIBLE);
                    notifyDataSetChanged();
                    Fragment currentFragment = ((AppCompatActivity)context).getSupportFragmentManager().findFragmentById(R.id.framelayout);
                    FragmentTransaction fragmentTransaction = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.detach(currentFragment);
                    fragmentTransaction.attach(currentFragment);
                    fragmentTransaction.commit();
                }

                @Override
                public void onFailure(Call<PojoDeleteTicket> call, Throwable t) {
                    if (t instanceof IOException) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textview_tanggal)
        TextView textviewTanggal;
        @BindView(R.id.textview_lapangan)
        TextView textviewLapangan;
        @BindView(R.id.textview_kategori)
        TextView textviewKategori;
        @BindView(R.id.textview_jam)
        TextView textviewJam;
        @BindView(R.id.textview_harga)
        TextView textviewHarga;
        @BindView(R.id.button_batal)
        Button buttonBatal;
        @BindView(R.id.textview_canceled)
        TextView textviewCanceled;
        @BindView(R.id.textview_durasi)
        TextView textviewDurasi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

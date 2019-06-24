package santriprogrammer.com.bookinglapangan.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    List<Booking> list;
    Context context;

    public HomeAdapter(List<Booking> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_booking_admin, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Booking item = list.get(i);
        viewHolder.textviewLapangan.setText(item.getBookingPlace());
        viewHolder.textviewDurasi.setText(item.getBookingHourUntil() + " Jam");
        viewHolder.textviewJam.setText(item.getBookingHour());
        viewHolder.textviewKategori.setText(item.getBookingCategory());
        viewHolder.textviewHarga.setText("Rp. " + item.getBookingPrice());
        viewHolder.textviewUser.setText(item.getBookingUserEmail());
        viewHolder.textviewLapangan.setSelected(true);
        viewHolder.textviewDurasi.setSelected(true);
        viewHolder.textviewJam.setSelected(true);
        viewHolder.textviewKategori.setSelected(true);
        viewHolder.textviewHarga.setSelected(true);
        viewHolder.textviewUser.setSelected(true);
        viewHolder.buttonKonfirmasi.setVisibility(View.VISIBLE);
        viewHolder.buttonBatal.setVisibility(View.VISIBLE);
        if (list.get(i).getBookingStatus().equals("0")) {
            viewHolder.imageviewBookingAdmin.setImageResource(R.drawable.ic_info_gray);
            viewHolder.textviewStatus.setText("Belum dikonfirmasi");
        } else if (list.get(i).getBookingStatus().equals("1")) {
            viewHolder.imageviewBookingAdmin.setImageResource(R.drawable.ic_cancel);
            viewHolder.textviewStatus.setText("Telah Dibatalkan");
            viewHolder.buttonKonfirmasi.setVisibility(View.GONE);
            viewHolder.buttonBatal.setVisibility(View.GONE);
        } else if (list.get(i).getBookingStatus().equals("2")) {
            viewHolder.imageviewBookingAdmin.setImageResource(R.drawable.ic_check_circle_black_24dp);
            viewHolder.textviewStatus.setText("Sudah Dikonfirmasi");
            viewHolder.buttonKonfirmasi.setVisibility(View.GONE);
            viewHolder.buttonBatal.setVisibility(View.GONE);
        } else if (list.get(i).getBookingStatus().equals("3")) {
            viewHolder.imageviewBookingAdmin.setImageResource(R.drawable.ic_cancel);
            viewHolder.textviewStatus.setText("Telah Ditolak");
            viewHolder.buttonKonfirmasi.setVisibility(View.GONE);
            viewHolder.buttonBatal.setVisibility(View.GONE);
        }
        viewHolder.buttonKonfirmasi.setOnClickListener(v -> {
            APIInterface req = APIClient.getRetrofit().create(APIInterface.class);
            final Call<PojoDeleteTicket> call = req.deleteTicket(item.getBookingId(), "2");
            call.enqueue(new Callback<PojoDeleteTicket>() {
                @Override
                public void onResponse(Call<PojoDeleteTicket> call, Response<PojoDeleteTicket> response) {
                    Toast.makeText(context, "Pemesanan berhasil dikonfirmasi", Toast.LENGTH_SHORT).show();
                    viewHolder.textviewStatus.setText("Sudah Dikonfirmasi");
                    viewHolder.imageviewBookingAdmin.setImageResource(R.drawable.ic_check_circle_black_24dp);
                }

                @Override
                public void onFailure(Call<PojoDeleteTicket> call, Throwable t) {
                    if (t instanceof IOException) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
        viewHolder.buttonBatal.setOnClickListener(v -> {
            APIInterface req = APIClient.getRetrofit().create(APIInterface.class);
            final Call<PojoDeleteTicket> call = req.deleteTicket(item.getBookingId(), "3");
            call.enqueue(new Callback<PojoDeleteTicket>() {
                @Override
                public void onResponse(Call<PojoDeleteTicket> call, Response<PojoDeleteTicket> response) {
                    Toast.makeText(context, "Pemesanan berhasil dibatalkan", Toast.LENGTH_SHORT).show();
                    viewHolder.textviewStatus.setText("Sudah Di Tolak");
                    viewHolder.imageviewBookingAdmin.setImageResource(R.drawable.ic_cancel);
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
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageview_booking_admin)
        ImageView imageviewBookingAdmin;
        @BindView(R.id.textview_status)
        TextView textviewStatus;
        @BindView(R.id.button_konfirmasi)
        Button buttonKonfirmasi;
        @BindView(R.id.button_batal)
        Button buttonBatal;
        @BindView(R.id.textview_lapangan)
        TextView textviewLapangan;
        @BindView(R.id.textview_kategori)
        TextView textviewKategori;
        @BindView(R.id.textview_jam)
        TextView textviewJam;
        @BindView(R.id.textview_durasi)
        TextView textviewDurasi;
        @BindView(R.id.textview_harga)
        TextView textviewHarga;
        @BindView(R.id.textview_user)
        TextView textviewUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

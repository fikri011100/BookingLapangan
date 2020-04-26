package santriprogrammer.com.bookinglapangan.announcement;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import santriprogrammer.com.bookinglapangan.R;
import santriprogrammer.com.bookinglapangan.StringHelper;
import santriprogrammer.com.bookinglapangan.retrofit.Booking;

public class AdapterAnnouncement extends RecyclerView.Adapter<AdapterAnnouncement.ViewHolder> {

    List<Booking> list;
    Context context;
    @BindView(R.id.textview_status_caption)
    TextView textviewStatusCaption;
    @BindView(R.id.textview_status)
    TextView textviewStatus;

    public AdapterAnnouncement(List<Booking> bookings, Context applicationContext) {
        this.list = bookings;
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ticket, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Booking booking = list.get(i);
        viewHolder.textviewTanggal.setText(StringHelper.formatDate(booking.getBookingDates()));
        viewHolder.textviewLapangan.setText(booking.getBookingPlace());
        viewHolder.textviewKategori.setText(booking.getBookingCategory());
        viewHolder.textviewJam.setText(booking.getBookingHour());
        viewHolder.textviewDurasi.setText(booking.getBookingHourUntil());
        if (list.get(i).getBookingStatus().equals("0")) {
            viewHolder.textviewStatus.setText("Belum dikonfirmasi");
        } else if (list.get(i).getBookingStatus().equals("1")) {
            viewHolder.textviewStatus.setText("Telah Dibatalkan");
            viewHolder.buttonBatal.setVisibility(View.GONE);
        } else if (list.get(i).getBookingStatus().equals("2")) {
            viewHolder.textviewStatus.setText("Sudah Dikonfirmasi");
        } else if (list.get(i).getBookingStatus().equals("3")) {
            viewHolder.textviewStatus.setText("Telah Ditolak");
        } else if (list.get(i).getBookingStatus().equals("5")) {
            viewHolder.textviewStatus.setText("Sudah Dibayar");
        }
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
        @BindView(R.id.textview_durasi)
        TextView textviewDurasi;
        @BindView(R.id.button_batal)
        Button buttonBatal;
        @BindView(R.id.textview_canceled)
        TextView textviewCanceled;
        @BindView(R.id.card)
        CardView card;
        @BindView(R.id.textview_status)
        TextView textviewStatus;
        @BindView(R.id.button_bayar)
        Button buttonBayar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            buttonBatal.setVisibility(View.GONE);
            buttonBayar.setVisibility(View.GONE);
            textviewCanceled.setVisibility(View.GONE);
        }
    }
}

package santriprogrammer.com.bookinglapangan.books;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import santriprogrammer.com.bookinglapangan.AppConfig;
import santriprogrammer.com.bookinglapangan.R;
import santriprogrammer.com.bookinglapangan.retrofit.APIClient;
import santriprogrammer.com.bookinglapangan.retrofit.APIInterface;
import santriprogrammer.com.bookinglapangan.retrofit.Lapangan;
import santriprogrammer.com.bookinglapangan.retrofit.PojoUploadBooking;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {
    List<Lapangan> lapangans;
    Context context;
    String tanggal, jam, username, kategori;
    int totaljam, totalPrice;
    private OnItemClicked onItemClicked;

    public BookingAdapter(List<Lapangan> lapangans, Context applicationContext, String tanggal, String jam, int totaljam, String username, String kategori, int totalPrice) {
        this.lapangans = lapangans;
        this.context = applicationContext;
        this.username = username;
        this.kategori = kategori;
        this.totalPrice = totalPrice;
        this.tanggal = tanggal;
        this.jam = jam;
        this.totaljam= totaljam;
    }

    public void setOnItemClicked(OnItemClicked onItemClicked) {
        this.onItemClicked = onItemClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lapangan, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Lapangan lapangan = lapangans.get(i);
        viewHolder.textLapangan.setText(lapangan.getFieldName());
        Glide.with(context)
                .load(AppConfig.BASE_URL_IMG + lapangan.getFieldImage())
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.imageLapangan);
        viewHolder.buttonLapangan.setOnClickListener(v -> {
            APIInterface req = APIClient.getRetrofit().create(APIInterface.class);
            final Call<PojoUploadBooking> call = req.postBooking(username, lapangan.getFieldName(), kategori, jam, String.valueOf(totaljam), tanggal, String.valueOf(totalPrice), "0");
            call.enqueue(new Callback<PojoUploadBooking>() {
                @Override
                public void onResponse(Call<PojoUploadBooking> call, Response<PojoUploadBooking> response) {
                    Toast.makeText(context, "Booking Lapangan berhasil! silahkan cek tiket", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<PojoUploadBooking> call, Throwable t) {
                    if (t instanceof IOException) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }

    @Override
    public int getItemCount() {
        if (lapangans == null) {
            return 0;
        } else {
            return lapangans.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_lapangan)
        TextView textLapangan;
        @BindView(R.id.image_lapangan)
        ImageView imageLapangan;
        @BindView(R.id.button_lapangan)
        Button buttonLapangan;
        @BindView(R.id.cons_click)
        ConstraintLayout consClick;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface OnItemClicked {

        void onClick(String tanggal, String jam, int totalJam);
    }
}

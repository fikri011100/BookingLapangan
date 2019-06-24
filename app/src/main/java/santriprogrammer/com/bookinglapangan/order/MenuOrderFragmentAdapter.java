package santriprogrammer.com.bookinglapangan.order;

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

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import santriprogrammer.com.bookinglapangan.AppConfig;
import santriprogrammer.com.bookinglapangan.R;
import santriprogrammer.com.bookinglapangan.retrofit.Lapangan;

public class MenuOrderFragmentAdapter extends RecyclerView.Adapter<MenuOrderFragmentAdapter.ViewHolder> {
    List<Lapangan> lapangans;
    Context context;

    public MenuOrderFragmentAdapter(List<Lapangan> lapangans, Context context) {
        this.lapangans = lapangans;
        this.context = context;
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
        viewHolder.buttonLapangan.setVisibility(View.GONE);
        viewHolder.textLapangan.setText(lapangan.getFieldName());
        Glide.with(context)
                .load(AppConfig.BASE_URL_IMG + lapangan.getFieldImage())
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.imageLapangan);
    }

    @Override
    public int getItemCount() {
        return lapangans.size();
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
}

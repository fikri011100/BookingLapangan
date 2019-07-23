package santriprogrammer.com.bookinglapangan.news;

import android.content.Context;
import android.content.Intent;
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
import santriprogrammer.com.bookinglapangan.news.detail.DetailNewsActivity;
import santriprogrammer.com.bookinglapangan.retrofit.News;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.ViewHolder> {

    List<News> list;
    Context context;

    public AdapterNews(List<News> bookings, Context applicationContext) {
        this.list = bookings;
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lapangan, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final News news = list.get(i);
        viewHolder.textLapangan.setText(news.getNewsTitle());
        Glide.with(context)
                .load(AppConfig.BASE_URL_IMG + news.getNewsImage())
                .crossFade()
                .placeholder(R.drawable.ic_newsfeed)
                .into(viewHolder.imageLapangan);
        viewHolder.consClick.setOnClickListener(v -> {
            Intent intent = new Intent(context.getApplicationContext(), DetailNewsActivity.class);
            intent.putExtra("title", news.getNewsTitle());
            intent.putExtra("image", news.getNewsImage());
            intent.putExtra("desc", news.getNewsDesc());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
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
            buttonLapangan.setVisibility(View.GONE);
        }
    }
}

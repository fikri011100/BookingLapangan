package santriprogrammer.com.bookinglapangan.news.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import santriprogrammer.com.bookinglapangan.AppConfig;
import santriprogrammer.com.bookinglapangan.R;

public class DetailNewsActivity extends AppCompatActivity {

    @BindView(R.id.image_detail)
    ImageView imageDetail;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.text_desc)
    TextView textDesc;
    String image, title, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        ButterKnife.bind(this);
        getStringExtr();
        textTitle.setText(title);
        textDesc.setText(desc);
        Glide.with(getApplicationContext())
                .load(AppConfig.BASE_URL_IMG + image)
                .crossFade()
                .placeholder(R.drawable.ic_newsfeed)
                .into(imageDetail);
    }

    private void getStringExtr() {
        image = getIntent().getStringExtra("image");
        title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("desc");
    }
}

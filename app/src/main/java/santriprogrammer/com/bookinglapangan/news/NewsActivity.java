package santriprogrammer.com.bookinglapangan.news;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import santriprogrammer.com.bookinglapangan.R;
import santriprogrammer.com.bookinglapangan.SessionManager;
import santriprogrammer.com.bookinglapangan.eventbus.NewsEventBus;
import santriprogrammer.com.bookinglapangan.news.addnews.AddNewsActivity;
import santriprogrammer.com.bookinglapangan.retrofit.APIClient;
import santriprogrammer.com.bookinglapangan.retrofit.APIInterface;
import santriprogrammer.com.bookinglapangan.retrofit.News;
import santriprogrammer.com.bookinglapangan.retrofit.PojoNews;

public class NewsActivity extends AppCompatActivity {

    @BindView(R.id.recycler_news)
    RecyclerView recyclerNews;
    APIInterface apiInterface;
    AdapterNews adapter;
    @BindView(R.id.fbAddNews)
    FloatingActionButton fbAddNews;
    SessionManager sessionManager;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("News");
        apiInterface = APIClient.getRetrofit().create(APIInterface.class);
        sessionManager = new SessionManager(getApplicationContext());
        if (sessionManager.getUserID().equals("0")) {
            fbAddNews.setVisibility(View.VISIBLE);
        } else {
            fbAddNews.setVisibility(View.GONE);
        }
        recyclerNews.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getData();
        fbAddNews.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AddNewsActivity.class));
        });
    }

    @Subscribe
    public void getTrigg(NewsEventBus.EventBus bus) {
        if (bus.getTrigg().equals("1")) {
            getData();
        }
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

    private void getData() {
        Call<PojoNews> call = apiInterface.getNews();
        call.enqueue(new Callback<PojoNews>() {
            @Override
            public void onResponse(Call<PojoNews> call, Response<PojoNews> response) {

                final List<News> bookings = response.body().getNews();
                if (response.body().getNews() != null) {
                    adapter = new AdapterNews(bookings, getApplicationContext(), sessionManager.getUserID());
                    recyclerNews.setAdapter(adapter);
                    if (adapter.getItemCount() == 0) {
                        Toast.makeText(getApplicationContext(), "Tidak ada Berita", Toast.LENGTH_SHORT).show();
                    }
                    adapter.notifyDataSetChanged();
                    Log.i("response", response.body().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Tidak ada Berita", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PojoNews> call, Throwable t) {
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

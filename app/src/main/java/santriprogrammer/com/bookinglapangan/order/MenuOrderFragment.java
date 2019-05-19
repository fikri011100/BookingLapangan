package santriprogrammer.com.bookinglapangan.order;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import santriprogrammer.com.bookinglapangan.R;
import santriprogrammer.com.bookinglapangan.books.BookingActivity;
import santriprogrammer.com.bookinglapangan.home.HomeFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuOrderFragment extends Fragment {


    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.text_title_bottom)
    TextView textTitleBottom;
    @BindView(R.id.text_content_bottom)
    TextView textContentBottom;
    @BindView(R.id.image_opt_umum)
    ImageView imageOptUmum;
    @BindView(R.id.image_cancel_umum)
    ImageView imageCancelUmum;
    @BindView(R.id.image_umum)
    ConstraintLayout imageUmum;
    @BindView(R.id.text_title_sekolah)
    TextView textTitleSekolah;
    @BindView(R.id.text_title_bottom_sekolah)
    TextView textTitleBottomSekolah;
    @BindView(R.id.text_content_bottom_sekolah)
    TextView textContentBottomSekolah;
    @BindView(R.id.image_opt_sekolah)
    ImageView imageOptSekolah;
    @BindView(R.id.image_cancel_sekolah)
    ImageView imageCancelSekolah;
    @BindView(R.id.image_sekolah)
    ConstraintLayout imageSekolah;
    @BindView(R.id.text_title_turney)
    TextView textTitleTurney;
    @BindView(R.id.text_title_bottom_turney)
    TextView textTitleBottomTurney;
    @BindView(R.id.text_content_bottom_turney)
    TextView textContentBottomTurney;
    @BindView(R.id.image_opt_turney)
    ImageView imageOptTurney;
    @BindView(R.id.image_cancel_turney)
    ImageView imageCancelTurney;
    @BindView(R.id.image_turney)
    ConstraintLayout imageTurney;
    @BindView(R.id.text_title_kids)
    TextView textTitleKids;
    @BindView(R.id.text_title_bottom_kids)
    TextView textTitleBottomKids;
    @BindView(R.id.text_content_bottom_kids)
    TextView textContentBottomKids;
    @BindView(R.id.image_opt_kids)
    ImageView imageOptKids;
    @BindView(R.id.image_cancel_kids)
    ImageView imageCancelKids;
    @BindView(R.id.image_kids)
    ConstraintLayout imageKids;
    Unbinder unbinder;
    @BindView(R.id.image_main_umum)
    ImageView imageMainUmum;
    @BindView(R.id.image_main_sekolah)
    ImageView imageMainSekolah;
    @BindView(R.id.image_main_turney)
    ImageView imageMainTurney;
    @BindView(R.id.image_main_kids)
    ImageView imageMainKids;

    public MenuOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        setView();
        return view;
    }

    private void setView() {
        textContentBottom.setText(R.string.ketentuanumum);
        textContentBottomSekolah.setText(R.string.ketentuansekolah);
        textContentBottomTurney.setText(R.string.ketentuanturney);
        textContentBottomKids.setText(R.string.ketentuananak);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.image_opt_umum)
    public void onImageOptUmumClicked() {
        imageMainUmum.setVisibility(View.GONE);
        textTitle.setVisibility(View.GONE);
        imageOptUmum.setVisibility(View.GONE);
        imageCancelUmum.setVisibility(View.VISIBLE);
        textTitleBottom.setVisibility(View.VISIBLE);
        textContentBottom.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.image_cancel_umum)
    public void onImageCancelUmumClicked() {
        imageMainUmum.setVisibility(View.VISIBLE);
        textTitle.setVisibility(View.VISIBLE);
        imageOptUmum.setVisibility(View.VISIBLE);
        imageCancelUmum.setVisibility(View.GONE);
        textTitleBottom.setVisibility(View.GONE);
        textContentBottom.setVisibility(View.GONE);
    }

    @OnClick(R.id.image_umum)
    public void onImageUmumClicked() {
        Intent intent = new Intent(getActivity(), BookingActivity.class);
        intent.putExtra("kategori", "umum");
        startActivity(intent);
    }

    @OnClick(R.id.image_opt_sekolah)
    public void onImageOptSekolahClicked() {
        imageMainSekolah.setVisibility(View.GONE);
        textTitleSekolah.setVisibility(View.GONE);
        imageOptSekolah.setVisibility(View.GONE);
        imageCancelSekolah.setVisibility(View.VISIBLE);
        textTitleBottomSekolah.setVisibility(View.VISIBLE);
        textContentBottomSekolah.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.image_cancel_sekolah)
    public void onImageCancelSekolahClicked() {
        imageMainSekolah.setVisibility(View.VISIBLE);
        textTitleSekolah.setVisibility(View.VISIBLE);
        imageOptSekolah.setVisibility(View.VISIBLE);
        imageCancelSekolah.setVisibility(View.GONE);
        textTitleBottomSekolah.setVisibility(View.GONE);
        textContentBottomSekolah.setVisibility(View.GONE);
    }

    @OnClick(R.id.image_sekolah)
    public void onImageSekolahClicked() {
        Intent intent = new Intent(getActivity(), BookingActivity.class);
        intent.putExtra("kategori", "sekolah");
        startActivity(intent);
    }

    @OnClick(R.id.image_opt_turney)
    public void onImageOptTurneyClicked() {
        imageMainTurney.setVisibility(View.GONE);
        textTitleTurney.setVisibility(View.GONE);
        imageOptTurney.setVisibility(View.GONE);
        imageCancelTurney.setVisibility(View.VISIBLE);
        textTitleBottomTurney.setVisibility(View.VISIBLE);
        textContentBottomTurney.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.image_cancel_turney)
    public void onImageCancelTurneyClicked() {
        imageMainTurney.setVisibility(View.VISIBLE);
        textTitleTurney.setVisibility(View.VISIBLE);
        imageOptTurney.setVisibility(View.VISIBLE);
        imageCancelTurney.setVisibility(View.GONE);
        textTitleBottomTurney.setVisibility(View.GONE);
        textContentBottomTurney.setVisibility(View.GONE);
    }

    @OnClick(R.id.image_turney)
    public void onImageTurneyClicked() {
        Intent intent = new Intent(getActivity(), BookingActivity.class);
        intent.putExtra("kategori", "turnamen");
        startActivity(intent);
    }

    @OnClick(R.id.image_opt_kids)
    public void onImageOptKidsClicked() {
        imageMainKids.setVisibility(View.GONE);
        textTitleKids.setVisibility(View.GONE);
        imageOptKids.setVisibility(View.GONE);
        imageCancelKids.setVisibility(View.VISIBLE);
        textTitleBottomKids.setVisibility(View.VISIBLE);
        textContentBottomKids.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.image_cancel_kids)
    public void onImageCancelKidsClicked() {
        imageMainKids.setVisibility(View.VISIBLE);
        textTitleKids.setVisibility(View.VISIBLE);
        imageOptKids.setVisibility(View.VISIBLE);
        imageCancelKids.setVisibility(View.GONE);
        textTitleBottomKids.setVisibility(View.GONE);
        textContentBottomKids.setVisibility(View.GONE);
    }

    @OnClick(R.id.image_kids)
    public void onImageKidsClicked() {
        Intent intent = new Intent(getActivity(), BookingActivity.class);
        intent.putExtra("kategori", "anak-anak");
        startActivity(intent);
    }
}

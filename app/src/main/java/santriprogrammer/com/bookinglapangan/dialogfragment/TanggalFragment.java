package santriprogrammer.com.bookinglapangan.dialogfragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import santriprogrammer.com.bookinglapangan.R;
import santriprogrammer.com.bookinglapangan.eventbus.TanggalEventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class TanggalFragment extends DialogFragment {


    @BindView(R.id.datepicker)
    DatePicker datepicker;
    @BindView(R.id.button_tanggal)
    Button buttonTanggal;
    Unbinder unbinder;
    @BindView(R.id.button_select_tanggal)
    Button buttonSelectTanggal;
    @BindView(R.id.button_select_jam)
    Button buttonSelectJam;
    @BindView(R.id.textview_decrease)
    TextView textviewDecrease;
    @BindView(R.id.textview_result)
    TextView textviewResult;
    @BindView(R.id.textview_increase)
    TextView textviewIncrease;
    int totalJam;
    @BindView(R.id.text_caption_spinner)
    TextView textCaptionSpinner;
    @BindView(R.id.timepicker)
    TimePicker timepicker;
    private Spinner spinner;

    public TanggalFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tanggal, container, false);
        unbinder = ButterKnife.bind(this, view);
        totalJam = 1;
        textviewIncrease.setOnClickListener(v -> increase());
        textviewDecrease.setOnClickListener(v -> decrease());
        buttonTanggal.setOnClickListener(v -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Calendar calendar = Calendar.getInstance();
            calendar.set(datepicker.getYear(), datepicker.getMonth(), datepicker.getDayOfMonth());
            calendar.get(Calendar.HOUR_OF_DAY);
            calendar.get(Calendar.MINUTE);
            String hour = String.valueOf(timepicker.getHour());
            if (timepicker.getHour() < 10) {
                hour = "0" + timepicker.getHour();
            }
            String min = String.valueOf(timepicker.getMinute());
            if (timepicker.getMinute() < 10)
                min = "0" + timepicker.getMinute();
            String date = dateFormat.format(calendar.getTime());
            String finalhour = hour + ":" + min;
//            Toast.makeText(getContext(), finalhour, Toast.LENGTH_SHORT).show();
            EventBus.getDefault().post(new TanggalEventBus.EventBus(date, finalhour, totalJam, hour));
            dismiss();
        });
        return view;
    }

    private void decrease() {
        if (totalJam == 1) {

        } else {
            totalJam = totalJam - 1;
            textviewResult.setText(String.valueOf(totalJam));
        }
    }

    private void increase() {
        totalJam = totalJam + 1;
        textviewResult.setText(String.valueOf(totalJam));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.button_select_tanggal)
    public void onButtonSelectTanggalClicked() {
        datepicker.setVisibility(View.VISIBLE);
        timepicker.setVisibility(View.INVISIBLE);
        textCaptionSpinner.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.button_select_jam)
    public void onButtonSelectJamClicked() {
        timepicker.setVisibility(View.VISIBLE);
        textCaptionSpinner.setVisibility(View.VISIBLE);
        datepicker.setVisibility(View.INVISIBLE);
    }
}

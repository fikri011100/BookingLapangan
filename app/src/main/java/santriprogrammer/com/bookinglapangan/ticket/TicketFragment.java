package santriprogrammer.com.bookinglapangan.ticket;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import santriprogrammer.com.bookinglapangan.EmptyRecyclerview;
import santriprogrammer.com.bookinglapangan.R;
import santriprogrammer.com.bookinglapangan.SessionManager;
import santriprogrammer.com.bookinglapangan.retrofit.APIClient;
import santriprogrammer.com.bookinglapangan.retrofit.APIInterface;
import santriprogrammer.com.bookinglapangan.retrofit.Booking;
import santriprogrammer.com.bookinglapangan.retrofit.PojoTicket;

/**
 * A simple {@link Fragment} subclass.
 */
public class TicketFragment extends Fragment {


    APIInterface apiInterface;
    SessionManager sessionManager;
    ProgressDialog pDialog;
    Unbinder unbinder;
    TicketAdapter adapter;
    @BindView(R.id.recyclerview_ticket)
    EmptyRecyclerview recyclerviewTicket;

    public TicketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);
        unbinder = ButterKnife.bind(this, view);
        sessionManager = new SessionManager(getActivity());
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        recyclerviewTicket.setLayoutManager(new LinearLayoutManager(getActivity()));
        apiInterface = APIClient.getRetrofit().create(APIInterface.class);
        getData();
        return view;
    }

    private void getData() {
        Call<PojoTicket> call = apiInterface.getTicket(sessionManager.getUsername());
        call.enqueue(new Callback<PojoTicket>() {
            @Override
            public void onResponse(Call<PojoTicket> call, Response<PojoTicket> response) {
                final List<Booking> bookings = response.body().getBooking();
                adapter = new TicketAdapter(bookings, getContext());
                recyclerviewTicket.setAdapter(adapter);
                if (adapter.getItemCount() == 0) {
                    Toast.makeText(getActivity(), "Tidak ada Ticket", Toast.LENGTH_SHORT).show();
                }
                Log.i("response", response.body().toString());
            }

            @Override
            public void onFailure(Call<PojoTicket> call, Throwable t) {
                Toast.makeText(getActivity(), "Maaf, koneksi anda tidak stabil", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

package santriprogrammer.com.bookinglapangan.user;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import santriprogrammer.com.bookinglapangan.LoginActivity;
import santriprogrammer.com.bookinglapangan.R;
import santriprogrammer.com.bookinglapangan.SessionManager;
import santriprogrammer.com.bookinglapangan.retrofit.APIClient;
import santriprogrammer.com.bookinglapangan.retrofit.APIInterface;
import santriprogrammer.com.bookinglapangan.retrofit.PojoUser;
import santriprogrammer.com.bookinglapangan.retrofit.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {


    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_notelp)
    TextView tvNotelp;
    Unbinder unbinder;
    APIInterface apiInterface;
    SessionManager sessionManager;
    ProgressDialog pDialog;
    @BindView(R.id.button_logout)
    Button buttonLogout;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        unbinder = ButterKnife.bind(this, view);
        sessionManager = new SessionManager(getActivity());
        apiInterface = APIClient.getRetrofit().create(APIInterface.class);
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        getData();
        return view;
    }

    private void getData() {
        pDialog.setMessage("Memproses...");
        showDialog();

        Call<PojoUser> call = apiInterface.getUser(sessionManager.getUsername());
        call.enqueue(new Callback<PojoUser>() {
            @Override
            public void onResponse(Call<PojoUser> call, Response<PojoUser> response) {
                hideDialog();
                List<User> users = response.body().getUser();
                Log.i("psst", response.body().getUser().toString());
                for (int i = 0; i < users.size(); i++) {
                    User user = users.get(i);
                    tvEmail.setText(user.getEmail());
                    tvUsername.setText(user.getUsername());
                    tvNotelp.setText(user.getNoTelp());
                }
            }

            @Override
            public void onFailure(Call<PojoUser> call, Throwable t) {

            }
        });

    }

    private void showDialog() {
        if (!pDialog.isShowing()) {
            pDialog.show();
        }
    }

    private void hideDialog() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.button_logout)
    public void onViewClicked() {
        sessionManager.setLogin(false);
        sessionManager.setUserID("");
        sessionManager.setUsername("");
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }
}

package santriprogrammer.com.bookinglapangan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import santriprogrammer.com.bookinglapangan.retrofit.APIClient;
import santriprogrammer.com.bookinglapangan.retrofit.APIInterface;
import santriprogrammer.com.bookinglapangan.retrofit.PojoLogin;
import santriprogrammer.com.bookinglapangan.retrofit.User;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edittext_email_login)
    MaterialEditText edittextEmailLogin;
    @BindView(R.id.edittext_password_login)
    MaterialEditText edittextPasswordLogin;
    @BindView(R.id.button_register)
    Button buttonRegister;
    @BindView(R.id.textview_goregister)
    TextView textviewGoregister;
    SessionManager sessionManager;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(getApplicationContext());
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        setview();
    }

    private void checkLogin(String email, String password) {
        pDialog.setMessage("Memproses...");
        showDialog();

        APIInterface req = APIClient.getRetrofit().create(APIInterface.class);
        final Call<PojoLogin> call = req.login(email, password);
        call.enqueue(new Callback<PojoLogin>() {
            @Override
            public void onResponse(Call<PojoLogin> call, Response<PojoLogin> response) {
                try {
                    List<User> users = response.body().getUser();
                    for (int i = 0; i < users.size(); i++) {
                        User user = users.get(i);
                        sessionManager.setUserID(user.getStatus());
                    }
                    sessionManager.setLogin(true);
                    sessionManager.setUsername(email);
                    hideDialog();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } catch (Exception e) {
                    hideDialog();
                    Toast.makeText(LoginActivity.this, "maaf, server sedang bermasalah", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<PojoLogin> call, Throwable t) {
                hideDialog();
                if (t instanceof IOException) {
                    Utils.failAlert(LoginActivity.this, "No Internet Connection");
                } else {
                    Utils.failAlert(LoginActivity.this, "Username atau Kata sandi belum diisi");
                }
            }
        });
    }

    private void setview() {
        if (sessionManager.isLoggedIn()) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        textviewGoregister.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), RegisterActivity.class)));
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

    @OnClick(R.id.button_register)
    public void onViewClicked() {
        String email = edittextEmailLogin.getText().toString().trim();
        String password = edittextPasswordLogin.getText().toString().trim();
        if (!email.isEmpty() || !password.isEmpty()) {
            checkLogin(email, password);
        } else {
            Utils.failAlert(LoginActivity.this, "maaf, email atau password tidak cocok");
        }
    }
}

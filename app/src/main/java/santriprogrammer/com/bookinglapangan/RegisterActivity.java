package santriprogrammer.com.bookinglapangan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import santriprogrammer.com.bookinglapangan.retrofit.APIClient;
import santriprogrammer.com.bookinglapangan.retrofit.APIInterface;
import santriprogrammer.com.bookinglapangan.retrofit.PojoRegister;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.edittext_username)
    MaterialEditText edittextUsername;
    @BindView(R.id.edittext_email)
    MaterialEditText edittextEmail;
    @BindView(R.id.edittext_no_telp)
    MaterialEditText edittextNoTelp;
    @BindView(R.id.edittext_password)
    MaterialEditText edittextPassword;
    @BindView(R.id.edittext_password_konfirmasi)
    MaterialEditText edittextPasswordKonfirmasi;
    @BindView(R.id.button_register)
    Button buttonRegister;
    @BindView(R.id.textview_gologin)
    TextView textviewGologin;
    SessionManager sessionManager;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(getApplicationContext());
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
    }

    @OnClick(R.id.button_register)
    public void onButtonRegisterClicked() {
        String username = edittextUsername.getText().toString().trim();
        String email = edittextEmail.getText().toString().trim();
        String noTelp = edittextNoTelp.getText().toString().trim();
        String password = edittextPassword.getText().toString().trim();
        String passwordConfirmation = edittextPasswordKonfirmasi.getText().toString().trim();

        if (!username.isEmpty() || !email.isEmpty() || !noTelp.isEmpty() || !password.isEmpty() || !passwordConfirmation.isEmpty()) {
            if (password.equals(passwordConfirmation)) {
                registerRetrofit(username, email, noTelp, password);
            } else {
                Utils.failAlert(RegisterActivity.this, "Maaf, password konfirmasi anda belum cocok");
            }
        } else {
            Utils.failAlert(RegisterActivity.this, "Maaf, anda belum mengisi beberapa form tersebut");
        }
    }

    public void registerRetrofit(String username, String email, String noTelp, String password) {
        pDialog.setMessage("Memproses...");
        showDialog();

        APIInterface req = APIClient.getRetrofit().create(APIInterface.class);
        final Call<PojoRegister> call = req.register(email, password, username, noTelp, "1");
        call.enqueue(new Callback<PojoRegister>() {
            @Override
            public void onResponse(Call<PojoRegister> call, Response<PojoRegister> response) {
                try {
                    if (response.body().getResult() == false) {
                        hideDialog();
                        Toast.makeText(RegisterActivity.this, "Maaf, email anda sudah terdaftar", Toast.LENGTH_SHORT).show();
                    } else {
                        hideDialog();
                        Toast.makeText(RegisterActivity.this, "Registrasi selesai, Silahkan login terlebih dahulu", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<PojoRegister> call, Throwable t) {
                hideDialog();
                if (t instanceof IOException) {
                    Utils.failAlert(RegisterActivity.this, "No Internet Connection");
                } else {
                    Utils.failAlert(RegisterActivity.this, "Username atau Kata sandi belum diisi");
                }
            }
        });
    }

    @OnClick(R.id.textview_gologin)
    public void onTextviewGologinClicked() {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
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
}

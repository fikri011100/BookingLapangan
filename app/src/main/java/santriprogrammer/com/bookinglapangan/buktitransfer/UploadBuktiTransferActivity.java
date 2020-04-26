package santriprogrammer.com.bookinglapangan.buktitransfer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rengwuxian.materialedittext.MaterialEditText;

import net.gotev.uploadservice.MultipartUploadRequest;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import santriprogrammer.com.bookinglapangan.AppConfig;
import santriprogrammer.com.bookinglapangan.MainActivity;
import santriprogrammer.com.bookinglapangan.R;
import santriprogrammer.com.bookinglapangan.SessionManager;
import santriprogrammer.com.bookinglapangan.retrofit.APIClient;
import santriprogrammer.com.bookinglapangan.retrofit.APIInterface;
import santriprogrammer.com.bookinglapangan.retrofit.BookingTransfer;
import santriprogrammer.com.bookinglapangan.retrofit.PojoDeleteTicket;
import santriprogrammer.com.bookinglapangan.retrofit.PojoTransferBooking;

public class UploadBuktiTransferActivity extends AppCompatActivity {

    @BindView(R.id.edittext_bank)
    MaterialEditText edittextBank;
    @BindView(R.id.edittext_no_rek)
    MaterialEditText edittextNoRek;
    @BindView(R.id.edittext_nama)
    MaterialEditText edittextNama;
    @BindView(R.id.edittext_nominal)
    MaterialEditText edittextNominal;
    @BindView(R.id.image_upload)
    ImageView imageUpload;
    @BindView(R.id.button_upload)
    Button buttonUpload;
    APIInterface apiInterface;
    SessionManager sessionManager;
    @BindView(R.id.layout1)
    ConstraintLayout layout1;
    @BindView(R.id.layout2)
    ConstraintLayout layout2;
    @BindView(R.id.image_bukti_transfer)
    ImageView imageBuktiTransfer;
    @BindView(R.id.button_konfirmasi)
    Button buttonKonfirmasi;
    @BindView(R.id.textview_no_data)
    TextView textviewNoData;
    private int PICK_IMAGE_REQUEST = 1;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private Uri filePathAccount;
    private Bitmap bitmapAccount;
    String id_booking, status, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_bukti_transfer);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Bukti Transfer");
        sessionManager = new SessionManager(this);
        id_booking = getIntent().getStringExtra("id_booking");
        status = getIntent().getStringExtra("status");
        price = getIntent().getStringExtra("price");
        requestStoragePermission();
        apiInterface = APIClient.getRetrofit().create(APIInterface.class);
        if (status.equals("4") || status.equals("5")) {
            layout1.setVisibility(View.GONE);
            layout2.setVisibility(View.VISIBLE);
            Call<PojoTransferBooking> call = apiInterface.getTransferBooking(id_booking);
            call.enqueue(new Callback<PojoTransferBooking>() {
                @Override
                public void onResponse(Call<PojoTransferBooking> call, Response<PojoTransferBooking> response) {
                    final List<BookingTransfer> bookings = response.body().getBookingTransfer();
                    Glide.with(getApplicationContext())
                            .load(AppConfig.BASE_URL_IMG + bookings.get(0).getPhotoStruk())
                            .crossFade()
                            .placeholder(R.drawable.ic_newsfeed)
                            .into(imageBuktiTransfer);
                }

                @Override
                public void onFailure(Call<PojoTransferBooking> call, Throwable t) {

                }
            });
            if (sessionManager.getUserID().equals("0")) {
                buttonKonfirmasi.setOnClickListener(v -> getConfirmation());
            } else {
                buttonKonfirmasi.setVisibility(View.GONE);
            }
            if (status.equals("5")) {
                buttonKonfirmasi.setVisibility(View.GONE);
            }
        } else {
            if (sessionManager.getUserID().equals("0")) {
                textviewNoData.setVisibility(View.VISIBLE);
                imageBuktiTransfer.setVisibility(View.GONE);
                buttonKonfirmasi.setVisibility(View.GONE);
            } else {
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
            }
        }
        edittextNominal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals(price)) {
                    imageUpload.setOnClickListener(v -> {
                        setPhoto();
                        imageUpload.setEnabled(true);
                        buttonUpload.setEnabled(true);
                    });
                } else {
                    edittextNominal.setError("Nominal harus sesuai dengan harga booking");
                }
            }
        });
        buttonUpload.setOnClickListener(v ->

        {
            APIInterface req = APIClient.getRetrofit().create(APIInterface.class);
            final Call<PojoDeleteTicket> call = req.deleteTicket(id_booking, "4");
            call.enqueue(new Callback<PojoDeleteTicket>() {
                @Override
                public void onResponse(Call<PojoDeleteTicket> call, Response<PojoDeleteTicket> response) {
                }

                @Override
                public void onFailure(Call<PojoDeleteTicket> call, Throwable t) {
                    if (t instanceof IOException) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(this, "Berhasil Upload bukti transfer", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        });
    }

    private void getConfirmation() {
        APIInterface req = APIClient.getRetrofit().create(APIInterface.class);
        final Call<PojoDeleteTicket> call = req.deleteTicket(id_booking, "5");
        call.enqueue(new Callback<PojoDeleteTicket>() {
            @Override
            public void onResponse(Call<PojoDeleteTicket> call, Response<PojoDeleteTicket> response) {
                Toast.makeText(UploadBuktiTransferActivity.this, "Bukti Transfer telah dikonfirmasi", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PojoDeleteTicket> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null
                && data.getData() != null) {
            filePathAccount = data.getData();
            try {
                String uploadId = UUID.randomUUID().toString();
                String urlImageServer = "http://fikri.akudeveloper.com/bookinglapangan/uploadbuktitransfer.php";
//                String urlImageServer = AppConfig.BASE_URL + "upload_lapangan_gambar.php";
                new MultipartUploadRequest(this, uploadId, urlImageServer)
                        .addFileToUpload(getPath(filePathAccount), "photo_struk")
                        .addParameter("nama_bank", edittextBank.getText().toString())
                        .addParameter("no_rek", edittextNoRek.getText().toString())
                        .addParameter("nama", edittextNama.getText().toString())
                        .addParameter("nominal", edittextNominal.getText().toString())
                        .addParameter("id_booking", id_booking)
//                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(2)
                        .startUpload();
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                finish();
            } catch (Exception ignored) {
                Toast.makeText(this, edittextBank.getText().toString(), Toast.LENGTH_SHORT).show();
            }
            try {
                bitmapAccount = getBitmapFromUri(filePathAccount);
                imageUpload.setImageBitmap(bitmapAccount);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setPhoto() {
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, PICK_IMAGE_REQUEST);
    }

    private void requestStoragePermission() {
        if (ContextCompat
                .checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (ActivityCompat
                .shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {

        }
        ActivityCompat
                .requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        STORAGE_PERMISSION_CODE);
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    public String getPath(Uri uri) {
        Cursor cursor;
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        cursor = getContentResolver().query(
                uri,
                filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String path = cursor.getString(columnIndex);
        cursor.close();

        return path;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

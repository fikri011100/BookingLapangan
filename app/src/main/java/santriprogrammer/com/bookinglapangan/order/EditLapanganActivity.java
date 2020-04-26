package santriprogrammer.com.bookinglapangan.order;

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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rengwuxian.materialedittext.MaterialEditText;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import santriprogrammer.com.bookinglapangan.AppConfig;
import santriprogrammer.com.bookinglapangan.MainActivity;
import santriprogrammer.com.bookinglapangan.R;
import santriprogrammer.com.bookinglapangan.retrofit.APIClient;
import santriprogrammer.com.bookinglapangan.retrofit.APIInterface;
import santriprogrammer.com.bookinglapangan.retrofit.PojoDeleteTicket;

public class EditLapanganActivity extends AppCompatActivity {

    @BindView(R.id.textcaption)
    TextView textcaption;
    @BindView(R.id.edittext_lapangan)
    MaterialEditText edittextLapangan;
    @BindView(R.id.image_pick)
    ImageView imagePick;
    @BindView(R.id.button_logout)
    Button buttonLogout;
    APIInterface apiInterface;
    APIClient apiClient;
    private int PICK_IMAGE_REQUEST = 1;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private Uri filePathAccount;
    private Bitmap bitmapAccount;
    String fieldId, fieldName, fieldImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lapangan);
        ButterKnife.bind(this);
        fieldId = getIntent().getStringExtra("field_id");
        fieldName = getIntent().getStringExtra("field_name");
        fieldImage = getIntent().getStringExtra("field_image");
        edittextLapangan.setText(fieldName);
        Glide.with(getApplicationContext())
                .load(AppConfig.BASE_URL_IMG + fieldImage)
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(imagePick);
        requestStoragePermission();
        imagePick.setOnClickListener(v -> setPhoto());
        buttonLogout.setOnClickListener(v -> {
            if (edittextLapangan.getText().toString().equals("")) {

            } else {
                APIInterface req = APIClient.getRetrofit().create(APIInterface.class);
                final Call<PojoDeleteTicket> call = req.updateLapangan(fieldId, edittextLapangan.getText().toString());
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
            }
            Toast.makeText(this, "Berhasil Edit Lapangan", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                requestStoragePermission();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null
                && data.getData() != null) {
            filePathAccount = data.getData();
            try {
                String uploadId = UUID.randomUUID().toString();
                String urlImageServer = "http://api.santriprogrammer.com/bookinglapangan/editGambarLapangan.php";
//                String urlImageServer = AppConfig.BASE_URL + "upload_lapangan_gambar.php";
                new MultipartUploadRequest(this, uploadId, urlImageServer)
                        .addFileToUpload(getPath(filePathAccount), "field_image")
                        .addParameter("field_id", fieldId)
//                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(2)
                        .startUpload();
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                finish();
            } catch (Exception ignored) {

            }
            try {
                bitmapAccount = getBitmapFromUri(filePathAccount);
                imagePick.setImageBitmap(bitmapAccount);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
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
}

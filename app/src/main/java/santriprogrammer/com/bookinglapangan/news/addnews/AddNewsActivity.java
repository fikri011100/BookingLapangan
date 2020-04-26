package santriprogrammer.com.bookinglapangan.news.addnews;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import net.gotev.uploadservice.MultipartUploadRequest;

import java.io.IOException;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import santriprogrammer.com.bookinglapangan.MainActivity;
import santriprogrammer.com.bookinglapangan.R;
import santriprogrammer.com.bookinglapangan.SessionManager;

public class AddNewsActivity extends AppCompatActivity {

    @BindView(R.id.edittext_title)
    MaterialEditText edittextTitle;
    @BindView(R.id.edittext_kritik_saran)
    EditText edittextKritikSaran;
    @BindView(R.id.image_upload)
    ImageView imageUpload;
    @BindView(R.id.button_upload)
    Button buttonUpload;
    SessionManager sessionManager;
    private int PICK_IMAGE_REQUEST = 1;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private Uri filePathAccount;
    private Bitmap bitmapAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        requestStoragePermission();
        edittextKritikSaran.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                imageUpload.setOnClickListener(v -> {
                    setPhoto();
                    imageUpload.setEnabled(true);
                    buttonUpload.setEnabled(true);
                });
            }
        });
        buttonUpload.setOnClickListener(v -> {
            Toast.makeText(this, "Berhasil Upload Artikel", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null
                && data.getData() != null) {
            filePathAccount = data.getData();
            try {
                String uploadId = UUID.randomUUID().toString();
                String urlImageServer = "http://fikri.akudeveloper.com/bookinglapangan/uploadNews.php";
//                String urlImageServer = AppConfig.BASE_URL + "upload_lapangan_gambar.php";
                new MultipartUploadRequest(this, uploadId, urlImageServer)
                        .addFileToUpload(getPath(filePathAccount), "news_image")
                        .addParameter("news_title", edittextTitle.getText().toString())
                        .addParameter("news_desc", edittextKritikSaran.getText().toString())
//                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(2)
                        .startUpload();
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                finish();
            } catch (Exception ignored) {
                Toast.makeText(this, edittextTitle.getText().toString(), Toast.LENGTH_SHORT).show();
            }
            try {
                bitmapAccount = MediaStore.Images.Media.getBitmap(getContentResolver(), filePathAccount);
                imageUpload.setImageBitmap(bitmapAccount);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), PICK_IMAGE_REQUEST);
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

    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }
}

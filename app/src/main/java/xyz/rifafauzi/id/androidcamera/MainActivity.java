package xyz.rifafauzi.id.androidcamera;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ivAvatar)
    ImageView ivAvatar;
    @BindView(R.id.btnChangePhoto)
    Button btnChangePhoto;

    Unbinder unbinder;

    private static final int REQUEST_CHOOSE_IMAGE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        unbinder = ButterKnife.bind(this);

        btnChangePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Fungsi untuk memanggil library choose image
                 */
                EasyImage.openChooserWithGallery(MainActivity.this, "Choose Picture",
                        REQUEST_CHOOSE_IMAGE);
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // method untuk menghandle ketika user sudah memilih gambar.
        // ketika gambar sudah dipilih maka gambar akan di redirect ke activity
        // library android-image-picker
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                CropImage.activity(Uri.fromFile(imageFile))
                        //u can comment 3 lines of code for hide cropping circle image
//                        .setGuidelines(CropImageView.Guidelines.ON)
//                        .setCropShape(CropImageView.CropShape.OVAL)
//                        .setFixAspectRatio(true)
                        .start(MainActivity.this);
            }

            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                super.onImagePickerError(e, source, type);
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                super.onCanceled(source, type);
            }
        });
        // ----

        // Method ini berfungsi ketika sudah selesai dari activity android-image-picker
        // Jika result_ok maka gambar yang sudah di crop akan dimasukan kedalam imageview
        // yang kita olah menggunakan library glide.
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();

                Glide.with(this)
                        .load(new File(resultUri.getPath()))
                        //u can use this method for display circle picture
//                        .apply(new RequestOptions().circleCrop())
                        .apply(new RequestOptions())
                        .into(ivAvatar);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();

                Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
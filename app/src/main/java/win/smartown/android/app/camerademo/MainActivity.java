package win.smartown.android.app.camerademo;

import android.Manifest;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import win.smartown.android.library.certificateCamera.CameraActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.main_image);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0x13 && resultCode == 0x14) {
            imageView.setImageBitmap(BitmapFactory.decodeFile(data.getStringExtra("result")));
        }
    }

    private void takePhoto(int type) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0x12);
            return;
        }
        Intent intent = new Intent(this, CameraActivity.class);
        intent.putExtra("type", type);
        startActivityForResult(intent, 0x13);
    }

    public void 身份证正面(View view) {
        takePhoto(1);
    }

    public void 身份证反面(View view) {
        takePhoto(2);
    }

    public void 营业执照竖版(View view) {
        takePhoto(3);
    }

    public void 营业执照横版(View view) {
        takePhoto(4);
    }

}

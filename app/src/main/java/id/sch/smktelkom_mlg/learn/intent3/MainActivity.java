package id.sch.smktelkom_mlg.learn.intent3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView ivPhone = (ImageView) findViewById(R.id.imageViewPhone);
        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber("0341712500");
            }
        });

        ImageView ivMessage = (ImageView) findViewById(R.id.imageViewSMS);
        ivMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeSmsMessage("Pesan dari SMK Telkom Malang");
            }
        });

        ImageView ivBrowser = (ImageView) findViewById(R.id.imageViewBrowser);
        ivBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage("http://www.smktelkom-mlg.sch.id/");
            }
        });

        ImageView ivCamera = (ImageView) findViewById(R.id.imageViewCamera);
        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturePhoto();
            }
        });

        
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    public void capturePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager())!=null)
            startActivityForResult(intent,  REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK)
        {
            Bitmap bitmap = data.getParcelableExtra("data");
            ImageView iv = (ImageView) findViewById(R.id.imageViewCamera);
            iv.setImageBitmap(bitmap);
        }
    }

    public void openWebPage(String url)
    {
        Uri webpage = Uri.parse(url);
        Intent intet = new Intent(Intent.ACTION_VIEW, webpage);
        if (intet.resolveActivity(getPackageManager())!=null)
            startActivity(intet);

    }

    public void composeSmsMessage(String message) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain" );
        intent.putExtra("sms_body", message);
        if (intent.resolveActivity(getPackageManager()) !=null)
            startActivity(intent);
    }

    public void dialPhoneNumber(String phoneNumber)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel: "+phoneNumber));
        if (intent.resolveActivity(getPackageManager())!=null)
            startActivity(intent);
    }
}

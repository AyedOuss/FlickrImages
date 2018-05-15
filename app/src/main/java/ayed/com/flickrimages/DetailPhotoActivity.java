package ayed.com.flickrimages;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import ayed.com.flickrimages.sdk.util.DataBaseHelper;

public class DetailPhotoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    ImageView mDetail ;
    Button likeBtn ;
    Bitmap bmp ;
    DataBaseHelper dataBaseHelper ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_photo);
        toolbar = (Toolbar) findViewById(R.id.toolbarDetails);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        byte[] b = extras.getByteArray("byteArray");
        bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
        mDetail = (ImageView) findViewById(R.id.detailPhoto);
        mDetail.setImageBitmap(bmp );
        likeBtn = (Button) findViewById(R.id.favori);
        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper = new DataBaseHelper(DetailPhotoActivity.this);
                dataBaseHelper.insertFavorite(bmp);
                Intent i = new Intent(DetailPhotoActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}

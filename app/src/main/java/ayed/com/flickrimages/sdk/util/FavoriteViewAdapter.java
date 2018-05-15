package ayed.com.flickrimages.sdk.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import ayed.com.flickrimages.R;

/**
 * Created by oussama on 15/05/2018.
 */

public class FavoriteViewAdapter extends ArrayAdapter<byte[]> {

    public Context context;
    public int resource;
    public ArrayList<byte[]> Items;

    public FavoriteViewAdapter(Context context, int resource, ArrayList<byte[]> objects) {

        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.Items = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(resource, parent, false);
        ImageView imageView = (ImageView)view.findViewById(R.id.imageFavori);

        byte[] b = Items.get(position) ;
        Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
        imageView.setImageBitmap(bmp);

        return view;
    }

}

package ayed.com.flickrimages.sdk.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import ayed.com.flickrimages.R;
import ayed.com.flickrimages.sdk.core.ImageDownloader;
import ayed.com.flickrimages.sdk.models.Photo;
import ayed.com.flickrimages.singletons.PhotoCache;

import static java.lang.System.load;

/**
 * Created by oussama on 15/05/2018.
 */

public class SearchViewAdapter extends ArrayAdapter<Photo> {

protected ImageDownloader mImageDownloadHandler;

public SearchViewAdapter(Context context, List<Photo> objects, ImageDownloader downloader) {
        super(context, 0, objects);

        mImageDownloadHandler = downloader;
        }

@Override
public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        View view = convertView;
        if (view == null) {
                LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.search_view_adapter, parent, false);
                holder = new ViewHolder();
                assert view != null;

                holder.imageView = (ImageView) view.findViewById(R.id.imageView);

                holder.progressBar = (ProgressBar) view.findViewById(R.id.progress);

                view.setTag(holder);
        } else {
                holder = (ViewHolder) view.getTag();
        }
        Photo photo = getItem(position);
        final int radius = 10;
        final int margin = 5;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin);

        Picasso.with(getContext())
                .load(photo.getUrl())
                .transform(transformation)
                .resize(200, 200)
                .into(holder.imageView, new Callback() {

                        @Override
                        public void onSuccess() {
                                holder.imageView.setVisibility(View.VISIBLE);
                                holder.progressBar.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onError() {
                                holder.progressBar.setVisibility(View.VISIBLE);
                                holder.imageView.setVisibility(View.INVISIBLE);
                        }
                });

        return view;
}


static class ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;
}
}
package ayed.com.flickrimages.sdk.interfaces;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by Eric on 10/8/2014.
 */
public interface ImageDownloadCompleted {
    public void onImageDownloadCompleted(final ImageView imageView, final Bitmap bitmap);

}

package ayed.com.flickrimages.sdk.data;



import java.util.ArrayList;

import ayed.com.flickrimages.sdk.interfaces.PhotoListRequestCompleted;

/**
 * Created by Eric on 10/7/2014.
 */
public abstract class PhotoSource {

    protected PhotoListRequestCompleted mPhotoListRequestCompletedListener;
    protected int mPhotosPerPage = 50;


    public abstract void fetchDefaultPhotos(int page);

    /**
     * Search photos (if applicable)
     * @param query A search query string
     */
    public abstract void searchPhotos(String query, int page);


    public abstract void getLargePhoto(String identifier);

    /**
     * @return A human readable name identifying the PhotoSource (eg: 'Flickr')
     */
    public abstract String getPhotoSourceName();

    public boolean isSearchSupported() { return false; }

    public PhotoListRequestCompleted getPhotoListRequestCompletedListener() {
        return mPhotoListRequestCompletedListener;
    }
    public void setPhotoListRequestCompletedListener(PhotoListRequestCompleted photoListRequestCompletedListener) {
        mPhotoListRequestCompletedListener = photoListRequestCompletedListener;
    }
    public int getPhotosPerPage() {
        return mPhotosPerPage;
    }
    public void setPhotosPerPage(int photosPerPage) {
        mPhotosPerPage = photosPerPage;
    }
}

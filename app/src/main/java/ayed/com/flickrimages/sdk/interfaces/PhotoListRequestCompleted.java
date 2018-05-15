package ayed.com.flickrimages.sdk.interfaces;


import java.util.ArrayList;

import ayed.com.flickrimages.sdk.models.Photo;

/**
 * Created by Eric on 10/7/2014.
 */
public interface PhotoListRequestCompleted {
    public void onPhotoListRequestCompleted(ArrayList<Photo> photos);
}

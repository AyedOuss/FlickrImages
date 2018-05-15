package ayed.com.flickrimages.sdk.models;

import android.os.Bundle;


public class Photo {

    protected String mId;
    protected String mUrl;
    protected String mTitle;

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Bundle asBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("url", mUrl);
        bundle.putString("id", mId);
        bundle.putString("title", mTitle);

        return bundle;
    }
}

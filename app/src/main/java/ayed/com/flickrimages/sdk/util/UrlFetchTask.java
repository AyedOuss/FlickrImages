package ayed.com.flickrimages.sdk.util;

import android.os.AsyncTask;


import java.io.IOException;

import ayed.com.flickrimages.sdk.interfaces.UrlContentRetrieved;

/**
 * Created by Eric on 10/7/2014.
 */
public class UrlFetchTask extends AsyncTask<String, Void, String> {

    protected UrlContentRetrieved mListener;

    public UrlContentRetrieved getListener() {
        return mListener;
    }

    public void setListener(UrlContentRetrieved listener) {
        mListener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            String content = new String(Utility.getBytesForUrl(params[0]));
            return content;
        } catch(IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(mListener != null)
            mListener.onUrlContentRetrieved(s);
    }
}


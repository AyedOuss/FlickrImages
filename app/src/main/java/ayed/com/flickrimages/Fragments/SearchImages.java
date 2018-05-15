package ayed.com.flickrimages.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;



import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import ayed.com.flickrimages.DetailPhotoActivity;
import ayed.com.flickrimages.R;
import ayed.com.flickrimages.sdk.core.ImageDownloader;
import ayed.com.flickrimages.sdk.data.FlickrPhotoSource;
import ayed.com.flickrimages.sdk.data.PhotoSource;
import ayed.com.flickrimages.sdk.interfaces.ImageDownloadCompleted;
import ayed.com.flickrimages.sdk.interfaces.PhotoListRequestCompleted;
import ayed.com.flickrimages.sdk.util.SearchViewAdapter;


public class SearchImages extends Fragment implements PhotoListRequestCompleted, ImageDownloadCompleted {


    private EditText txtSearch ;
    private Button btnSearch ;
    private GridView mGridView;
    Bitmap bitmap = null;
    ProgressDialog pDialog;

    // Simple adapter used to format the images in the grid view
    private SearchViewAdapter mAdapter;

    // Interface for grabbing photos/data from a datasource (eg: local, Flickr, etc... )
    private PhotoSource mCurrentPhotoSource;

    // Handler used to download images
    private ImageDownloader mImageDownloadHandler;

    public SearchImages() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // set up the Handler we'll use to download the images
        mImageDownloadHandler = new ImageDownloader();
        mImageDownloadHandler.setListener(this);
        mImageDownloadHandler.getLooper();
        mImageDownloadHandler.start();

        // setup a default photo source (Flickr) and request a list of images to display by default
        mCurrentPhotoSource = new FlickrPhotoSource();
        mCurrentPhotoSource.setPhotoListRequestCompletedListener(this);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_images, container, false);
        btnSearch = (Button) view.findViewById(R.id.btnSearch);
        txtSearch = (EditText) view.findViewById(R.id.txtSearch);
        mGridView = (GridView) view.findViewById(R.id.gridViewList) ;

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                hideSoftKeyBoardOnTabClicked(v);

                mCurrentPhotoSource.searchPhotos(txtSearch.getText().toString(), 1);

            }
        });
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView img = (ImageView) view.findViewById(R.id.imageView);
                BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
                bitmap = drawable.getBitmap();
                Intent intent = new Intent(getActivity(), DetailPhotoActivity.class);
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent);
            }
        });

        return view;
    }


    @Override
    public void onImageDownloadCompleted(final ImageView imageView, final Bitmap bitmap) {

        // when the images have finished downloading, set the bitmap for the ImageView

    }

    @Override
    public void onPhotoListRequestCompleted(ArrayList<ayed.com.flickrimages.sdk.models.Photo> photos) {
        if (mAdapter == null) {
            mAdapter = new SearchViewAdapter(getActivity(), photos, mImageDownloadHandler);

            mGridView.setAdapter(mAdapter);
        } else {
            if(photos != null) {
                mAdapter.clear();
                mAdapter.addAll(photos);
            } else
                Toast.makeText(getActivity(), "Error retrieving results", Toast.LENGTH_SHORT);
        }

    }
    private void hideSoftKeyBoardOnTabClicked(View v) {
        if (v != null && getContext() != null) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}

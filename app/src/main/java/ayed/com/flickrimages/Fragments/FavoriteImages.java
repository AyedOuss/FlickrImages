package ayed.com.flickrimages.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import ayed.com.flickrimages.R;
import ayed.com.flickrimages.sdk.util.DataBaseHelper;
import ayed.com.flickrimages.sdk.util.FavoriteViewAdapter;

public class FavoriteImages extends Fragment{
    DataBaseHelper dataBaseHelper ;
    FavoriteViewAdapter favoriteViewAdapter ;
    ArrayList<byte[]> images ;
    GridView gridView ;
    public FavoriteImages() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBaseHelper = new DataBaseHelper(getContext());
        images = dataBaseHelper.getFavorites();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite_images, container, false);
        gridView = (GridView) view.findViewById(R.id.gridViewFavori);
        favoriteViewAdapter = new FavoriteViewAdapter(getContext(),R.layout.favorite_view_adpater,images);
        gridView.setAdapter(favoriteViewAdapter);

        return view ;
    }

}

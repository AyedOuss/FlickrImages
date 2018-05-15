package ayed.com.flickrimages.sdk.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by oussama on 15/05/2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper

{
    public static final String DATABASE_NAME = "FlickrImages";
    //Flickr Fields
    public static final String FLICKR_TABLE_NAME = "images";
    public static final String FLICKR_COLUMN_ID = "id";
    public static final String FLICKR_COLUMN_IMG = "img";





    public DataBaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //FlickrImages creation
        db.execSQL("create table IF NOT EXISTS "+FLICKR_TABLE_NAME+ "("+FLICKR_COLUMN_ID+" integer primary key autoincrement,"+FLICKR_COLUMN_IMG+" BLOB )");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop FlickrImages
        db.execSQL("DROP TABLE IF EXISTS "+FLICKR_TABLE_NAME);
        onCreate(db);
        db.close();

    }

    //FlickrImages Method Begin
    public boolean insertFavorite(Bitmap bitmap) {
        byte[] img ;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap .compress(Bitmap.CompressFormat.PNG, 100, bos);
        img = bos.toByteArray();
        contentValues.put(FLICKR_COLUMN_IMG,img);

        db.insert(FLICKR_TABLE_NAME, null, contentValues);
        System.out.println("UserCreated!!!");
        return true;
    }

    public ArrayList<byte[]> getFavorites()
    {
        ArrayList<byte[]> listI = new ArrayList<byte[]>();

        SQLiteDatabase  db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+FLICKR_TABLE_NAME ,null);
        res.moveToFirst();
        while (res.isAfterLast()== false)
        {
            listI.add(res.getBlob(res.getColumnIndex(FLICKR_COLUMN_IMG)));

            res.moveToNext();
        }





        return listI ;

    }

    //FlickrImages Method Ends




}

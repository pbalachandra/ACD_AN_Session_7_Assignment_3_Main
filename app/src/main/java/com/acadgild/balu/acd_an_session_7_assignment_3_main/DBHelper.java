package com.acadgild.balu.acd_an_session_7_assignment_3_main;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by BALU on 4/21/2016.
 */
public class DBHelper extends SQLiteOpenHelper
{
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "DB_EMPLOYEE";

    private static final String TABLE_NAME = "EMPLOYEE";
    private static final String COL_ID = "EMPLOYEE_ID";
    private static final String COL_NAME = "EMPLOYEE_NAME";
    private static final String COL_AGE = "EMPLOYEE_AGE";
    private static final String COL_PHOTO = "EMPLOYEE_PHOTO";

    public DBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String create_table = "CREATE TABLE " + TABLE_NAME + " ( "
                              + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                              + COL_NAME + " TEXT, "
                              + COL_AGE + " INTEGER, "
                              + COL_PHOTO + " BLOB )";

        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        this.onCreate(db);
    }

    public void add_employee_details(Employee employee)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_NAME, employee.getName());
        values.put(COL_AGE, employee.getAge());
        values.put(COL_PHOTO, getBytes(employee.getPhoto()));
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Employee get_employee_details()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String str_query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(str_query, null);

        if (cursor.moveToFirst())
        {
            Employee employee = new Employee();

            employee.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
            employee.setAge(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_AGE))));
            employee.setPhoto(getPhoto(cursor.getBlob(cursor.getColumnIndex(COL_PHOTO))));

            return employee;
        }
        return null;
    }

    public void clear_table()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME);

        db.close();

    }

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap getPhoto(byte[] image)
    {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}

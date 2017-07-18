package com.sds.dslibrary.test.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sds.dslibrary.lib.db.DsDBHelper;

/**
 * Created by sds on 2017-07-18.
 */

public class TestDBHelper extends SQLiteOpenHelper {

    public static final String TEST_DB_NAME = "Test";
    public static final int TEST_DB_VERSION = 1;

    private static TestDBHelper mInstance = null;

    public static TestDBHelper getInstance() {
        return mInstance;
    }

    public static TestDBHelper init(Context c, String name,
                            SQLiteDatabase.CursorFactory factory, int version,
                            DatabaseErrorHandler errorHandler) {

        if (mInstance == null) {
            synchronized (DsDBHelper.class) {
                if (mInstance == null) {
                    mInstance = new TestDBHelper(c, name, factory, version, errorHandler);

                    // force open
                    mInstance.getReadableDatabase();
                }
            }
        }

        return mInstance;
    }

    private OnDatabaseCallback mDataBaseCallback = null;

    public TestDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public void setOnDatabaseCallback(OnDatabaseCallback l) {
        mDataBaseCallback = l;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (mDataBaseCallback != null) {
            mDataBaseCallback.onCreate(db);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (mDataBaseCallback != null) {
            mDataBaseCallback.onUpgrade(db, oldVersion, newVersion);
        }
    }

    public interface OnDatabaseCallback {
        void onCreate(SQLiteDatabase db);
        void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
    }
}

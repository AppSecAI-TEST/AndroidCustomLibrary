package com.sds.dslibrary.lib.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sds on 2017-07-18.
 */

/**
 * Just example code. Do not extends
 */
public class DsDBHelper extends SQLiteOpenHelper {

    private static DsDBHelper mInstance = null;

    public static DsDBHelper getInstance() {
        return mInstance;
    }

    public static DsDBHelper init(Context c, String name,
                            SQLiteDatabase.CursorFactory factory, int version,
                            DatabaseErrorHandler errorHandler) {

        if (mInstance == null) {
            synchronized (DsDBHelper.class) {
                if (mInstance == null) {
                    mInstance = new DsDBHelper(c, name, factory, version, errorHandler);

                    // force open
                    mInstance.getReadableDatabase();
                }
            }
        }

        return mInstance;
    }

    public DsDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    /**
     * Database가 없을 때 한번 호출 됨
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // override
    }

    /**
     * 테이블 추가 및 드랍 등등
     * DBHelper를 생성할때 적용하는 version 값이 변경되면 호출 됨
     * if (oldVersion <= newVersion) { success; } else { crash; }
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // override
    }
}

package com.sds.dslibrary.test;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sds.dslibrary.R;
import com.sds.dslibrary.lib.activity.DsBaseActivity;
import com.sds.dslibrary.test.db.TestDBHelper;

/**
 * Created by sds on 2017-07-18.
 */

public class DatabaseActivity extends DsBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        initDBHelper();
    }

    private void initDBHelper() {
        TestDBHelper dbHelper = TestDBHelper.getInstance();

        if (dbHelper == null) {
            dbHelper = TestDBHelper.init(this, TestDBHelper.TEST_DB_NAME, null, TestDBHelper.TEST_DB_VERSION, null);
        }

        dbHelper.setOnDatabaseCallback(new TestDBHelper.OnDatabaseCallback() {
            @Override
            public void onCreate(SQLiteDatabase db) {
                // create table
                StringBuffer sb = new StringBuffer();
                sb.append(" CREATE TABLE TEST_TABLE ( ");
                sb.append(" _ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
                sb.append(" NAME TEXT, ");
                sb.append(" AGE INTEGER, ");
                sb.append(" PHONE TEXT ) ");

                db.execSQL(sb.toString());
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        });
    }
}

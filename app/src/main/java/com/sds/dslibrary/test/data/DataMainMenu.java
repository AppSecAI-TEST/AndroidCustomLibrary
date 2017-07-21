package com.sds.dslibrary.test.data;

/**
 * Created by sds on 2017-07-10.
 */

public class DataMainMenu {

    public enum Item {
        DEVICE_INFO("Device info"),
        INFINITE_VIEWPAGER("ViewPager - Infinite"),
        COORDINATOR("Coordinator"),
        TIME_SCHEDULE("Time Schedule"),
        VIDEO("Video"),
        DATABASE("Database - SQLite"),
        ;

        public String mTitle;

        Item(String strTitle) {
            mTitle = strTitle;
        }
    }
}

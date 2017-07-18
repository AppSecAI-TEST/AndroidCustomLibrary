package com.sds.dslibrary.test.data;

/**
 * Created by sds on 2017-07-10.
 */

public class DataMainMenu {

    public enum Item {
        INFINITE_VIEWPAGER("ViewPager - Infinite"),
        COORDINATOR("Coordinator"),
        TIME_SCHEDULE("Time Schedule"),
        VIDEO("Video"),
        ;

        public String mTitle;

        Item(String strTitle) {
            mTitle = strTitle;
        }
    }
}

package com.sds.dslibrary.lib.data;

/**
 * Created by sds on 2017-07-07.
 */

public class DsDataCommunication {

    public String mKey;
    public String mTag;
    public Object mObject;

    public DsDataCommunication(String key, String tag, Object object) {
        mKey = key;
        mTag = tag;
        mObject = object;
    }
}

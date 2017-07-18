package com.sds.dslibrary.lib.utils.communication;

import com.sds.dslibrary.lib.data.DsDataCommunication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sds on 2017-07-07.
 */

public class DsCommunicationManager {

    private final List<DsDataCommunication> mCommunicationList = new ArrayList<>();

    private static DsCommunicationManager mInstance = null;

    public static DsCommunicationManager getInstance() {
        if (mInstance == null) {
            synchronized (DsCommunicationManager.class) {
                if (mInstance == null) {
                    mInstance = new DsCommunicationManager();
                }
            }
        }

        return mInstance;
    }

    private DsCommunicationManager() {

    }

    public void pushCommunication(String key, String tag, Object object) {
        synchronized (mCommunicationList) {
            mCommunicationList.add(new DsDataCommunication(key, tag, object));
        }
    }

    public DsDataCommunication popCommunication(String key, String tag) {
        DsDataCommunication result = null;

        synchronized (mCommunicationList) {
            Iterator<DsDataCommunication> it = mCommunicationList.iterator();

            while (it.hasNext()) {
                DsDataCommunication data = it.next();

                if (data.mKey.equals(key) && data.mTag.equals(tag)) {
                    result = data;
                    it.remove();
                    break;
                }
            }
        }

        return result;
    }
}

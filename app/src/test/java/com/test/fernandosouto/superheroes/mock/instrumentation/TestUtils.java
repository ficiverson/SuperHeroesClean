package com.test.fernandosouto.superheroes.mock.instrumentation;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fernando souto on 31/01/2018.
 */

public class TestUtils {

    public static <T extends Parcelable> T testParcel(T item, Parcelable.Creator<T> creator) {
        Parcel parcel = Parcel.obtain();
        item.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        return creator.createFromParcel(parcel);
    }
}

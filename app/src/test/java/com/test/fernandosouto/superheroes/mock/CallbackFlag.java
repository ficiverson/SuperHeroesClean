package com.test.fernandosouto.superheroes.mock;

/**
 * Created by fernando souto on 31/01/2018.
 */

public class CallbackFlag {

    private boolean mExecuted = false;
    private int mAmount = 0;

    public static CallbackFlag newCallbackFlag(){
        return new CallbackFlag();
    }

    public void flagExecuted(){
        mExecuted = true;
        mAmount++;
    }

    public boolean isFlagged(){
        return mExecuted;
    }

    public int timesExecuted() {
        return mAmount;
    }
}

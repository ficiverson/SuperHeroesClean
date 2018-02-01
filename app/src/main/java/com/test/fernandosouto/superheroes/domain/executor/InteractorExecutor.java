package com.test.fernandosouto.superheroes.domain.executor;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.test.fernandosouto.superheroes.domain.data.Callback;
import com.test.fernandosouto.superheroes.domain.data.Result;
import com.test.fernandosouto.superheroes.domain.data.Status;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by fernando souto on 30/01/2018.
 */

public class InteractorExecutor<T> implements Cancelable, Executor<T> {

    private static final int KEEP_ALIVE_TIME = 1;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = NUMBER_OF_CORES + 1;
    private static final int MAXIMUM_POOL_SIZE = NUMBER_OF_CORES * 2 + 1;

    @Nullable
    private Callback<T> mCallback;
    private boolean mCancelled;
    @Nullable
    private InteractorExecutionCallback mExecutionCallback;
    @NonNull
    private Interactor<T> mInteractor;
    @NonNull
    private Handler mResultHandler;
    @NonNull
    private ThreadPoolExecutor mThreadPoolExecutor;

    public InteractorExecutor(@NonNull final Interactor<T> interactor,
                              @Nullable InteractorExecutionCallback executionCallback) {
        mInteractor = interactor;
        mExecutionCallback = executionCallback;

        final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        mThreadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, workQueue);

        if (Looper.myLooper() != null) {
            mResultHandler = new Handler(Looper.myLooper());
        }
    }

    public boolean isCancelled() {
        return mCancelled;
    }

    @NonNull
    @Override
    public final Cancelable execute(@Nullable final Callback<T> callback) {
        mCallback = callback;

        mThreadPoolExecutor.execute(new SafeRunnable<T>(mCallback) {
            @Override
            protected void safeRun() throws Exception {
                Result<T> resultingData = null;
                if (mExecutionCallback != null) {
                    mExecutionCallback.onPreExecute();
                }
                if (!isCancelled()) {
                    resultingData = mInteractor.executeInteractor();
                }
                if (mExecutionCallback != null) {
                    mExecutionCallback.onPostExecute();
                }
                if (!isCancelled()) {
                    notifyCallback(resultingData);
                }
            }
        });

        return this;
    }

    private void notifyCallback(@NonNull final Result<T> resultingData) {
        mResultHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mCallback != null) {
                    mCallback.onFinish(resultingData);
                }
            }
        });
    }

    @Override
    public void cancel() {
        mCancelled = true;
        mCallback = null;
    }


    private abstract static class SafeRunnable<T> implements Runnable {

        private Callback<T> mCallback;

        public SafeRunnable(@Nullable Callback<T> callback) {

            mCallback = callback;
        }

        @Override
        public void run() {
            wrapSafely();
        }

        private void wrapSafely() {
            try {
                safeRun();
            } catch (Exception e) { // Caught unexpected exceptions
                if (mCallback != null) {
                    Status status = Status.builder()
                            .error(e)
                            .build();
                    mCallback.onFinish(new Result<T>(status, null));
                }
            }
        }

        protected abstract void safeRun() throws Exception;
    }


    public interface Interactor<T> {
        @NonNull
        Result<T> executeInteractor() throws Exception;
    }

}

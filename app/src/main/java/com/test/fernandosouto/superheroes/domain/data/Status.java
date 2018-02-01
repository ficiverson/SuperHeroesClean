package com.test.fernandosouto.superheroes.domain.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by fernando souto on 30/01/2018.
 */

public class Status implements Parcelable {

    @IntDef({
            STATUS_UNKNOWN_ERROR,
            STATUS_OK,
            STATUS_CANCELED,
            STATUS_NOT_FOUND_ERROR
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorStatus {
    }

    /**
     * Data statuses.
     */
    @IntDef({
            STATUS_FRESH,
            STATUS_INCONSISTENT
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface DataStatus {
    }

    public static final int STATUS_FRESH = 0;
    public static final int STATUS_INCONSISTENT = 2;


    public static final int STATUS_UNKNOWN_ERROR = -1;
    public static final int STATUS_OK = 0;
    public static final int STATUS_CANCELED = 1;
    public static final int STATUS_NOT_FOUND_ERROR = 2;


    public static final Parcelable.Creator<Status> CREATOR = new Parcelable.Creator<Status>() {
        public Status createFromParcel(Parcel source) {
            return new Status(source);
        }

        public Status[] newArray(int size) {
            return new Status[size];
        }
    };

    private final Builder mBuilder;

    private Status(@NonNull Builder builder) {
        mBuilder = builder;
    }

    protected Status(Parcel in) {
        this.mBuilder = in.readParcelable(Builder.class.getClassLoader());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(@NonNull Builder builder) {
        return new Builder(builder);
    }

    public static Builder builder(@NonNull Status status) {
        return new Builder(status);
    }

    @NonNull
    public Builder modify() {
        return new Builder(mBuilder);
    }

    public boolean isOk() {
        return mBuilder.mErrorStatus == STATUS_OK && mBuilder.mDataStatus == STATUS_FRESH;
    }

    public boolean isCanceled() {
        return mBuilder.mErrorStatus == STATUS_CANCELED;
    }


    public boolean isNotFoundException() {
        return mBuilder.mErrorStatus == STATUS_NOT_FOUND_ERROR;
    }


    public boolean isError() {
        return mBuilder.mException != null;
    }

    public String getExceptionMessage() {
        if (isError()) {
            return mBuilder.mException.getMessage();
        }
        return null;
    }

    @Nullable
    public Exception exception() {
        return mBuilder.mException;
    }

    @DataStatus
    public int dataStatus() {
        return mBuilder.mDataStatus;
    }

    @ErrorStatus
    public int errorStatus() {
        return mBuilder.mErrorStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mBuilder, flags);
    }

    /**
     * The builder for the data status.
     */
    public static class Builder implements Parcelable {
        public static final Creator<Builder> CREATOR = new Creator<Builder>() {
            public Builder createFromParcel(Parcel source) {
                return new Builder(source);
            }

            public Builder[] newArray(int size) {
                return new Builder[size];
            }
        };

        @DataStatus
        private int mDataStatus;
        @ErrorStatus
        private int mErrorStatus;
        private Exception mException;


        private Builder() {
            mDataStatus = STATUS_FRESH;
            mErrorStatus = STATUS_OK;
        }

        private Builder(@NonNull Builder builder) {
            mDataStatus = builder.mDataStatus;
            mErrorStatus = builder.mErrorStatus;
            mException = builder.mException;
        }

        @SuppressWarnings("ResourceType")
        protected Builder(Parcel in) {
            this.mDataStatus = in.readInt();
            this.mErrorStatus = in.readInt();
            this.mException = (Exception) in.readSerializable();
        }

        protected Builder(Status status) {
            this.mDataStatus = status.dataStatus();
            this.mErrorStatus = status.errorStatus();
            this.mException = status.exception();
        }

        @NonNull
        public Builder dataInconsistent() {
            mDataStatus = STATUS_NOT_FOUND_ERROR;
            return this;
        }

        @NonNull
        public Builder cancel() {
            mDataStatus = STATUS_NOT_FOUND_ERROR;
            mErrorStatus = STATUS_CANCELED;
            return this;
        }


        public Builder error(@Nullable Exception e) {
            if (e != null) {
                mException = e;
                mErrorStatus = getStatusFromException(e);
                dataInconsistent();
            }
            return this;
        }


        @ErrorStatus
        private int getStatusFromException(@NonNull Exception e) {
            int status = STATUS_UNKNOWN_ERROR;
            if (e instanceof Exception) {
                status = STATUS_NOT_FOUND_ERROR;
            }
            return status;
        }

        public boolean isOk() {
            return mErrorStatus == STATUS_OK;
        }

        @NonNull
        public Status build() {
            if (mDataStatus == STATUS_FRESH && mErrorStatus != STATUS_OK) {
                throw new IllegalStateException("Invalid status");
            }
            return new Status(this);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.mDataStatus);
            dest.writeInt(this.mErrorStatus);
            dest.writeSerializable(this.mException);
        }
    }
}

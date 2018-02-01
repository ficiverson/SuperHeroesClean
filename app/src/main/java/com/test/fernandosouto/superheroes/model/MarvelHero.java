package com.test.fernandosouto.superheroes.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;

/**
 * Created by fernando souto on 30/01/2018.
 */

public class MarvelHero implements Parcelable {

    private String name;
    private String photo;
    private String realName;
    private String height;
    private String power;
    private String abilities;
    private String groups;

    private MarvelHero(@NonNull MarvelHero.Builder builder) {
        this.name = builder.name;
        this.photo = builder.photo;
        this.realName = builder.realName;
        this.height = builder.height;
        this.power = builder.power;
        this.abilities = builder.abilities;
        this.groups = builder.groups;
    }

    protected MarvelHero(Parcel in) {
        name = in.readString();
        photo = in.readString();
        realName = in.readString();
        height = in.readString();
        power = in.readString();
        abilities = in.readString();
        groups = in.readString();
    }

    public static final Creator<MarvelHero> CREATOR = new Creator<MarvelHero>() {
        @Override
        public MarvelHero createFromParcel(Parcel in) {
            return new MarvelHero(in);
        }

        @Override
        public MarvelHero[] newArray(int size) {
            return new MarvelHero[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(photo);
        dest.writeString(realName);
        dest.writeString(height);
        dest.writeString(power);
        dest.writeString(abilities);
        dest.writeString(groups);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    /**
     * The builder class.
     */
    @Keep
    public static class Builder implements IBuilder<MarvelHero> {

        private String name;
        private String photo;
        private String realName;
        private String height;
        private String power;
        private String abilities;
        private String groups;

        public Builder(String name) {
            this.name = name;
        }

        @NonNull
        public Builder withPhoto(@NonNull String photo) {
            this.photo = photo;
            return this;
        }

        @NonNull
        public Builder withRealName(@NonNull String realName) {
            this.realName = realName;
            return this;
        }

        @NonNull
        public Builder withHeiht(@NonNull String height) {
            this.height = height;
            return this;
        }

        @NonNull
        public Builder withPower(@NonNull String power) {
            this.power = power;
            return this;
        }

        @NonNull
        public Builder withAbilities(@NonNull String abilities) {
            this.abilities = abilities;
            return this;
        }

        @NonNull
        public Builder withGroups(@NonNull String groups) {
            this.groups = groups;
            return this;
        }

        @NonNull
        @Override
        public MarvelHero build() {
            return new MarvelHero(this);
        }
    }

}

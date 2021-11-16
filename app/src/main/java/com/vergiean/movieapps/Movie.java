package com.vergiean.movieapps;

import android.os.Parcel;
import android.os.Parcelable;





public class Movie implements Parcelable {
    private String title;
    private String release;
    private String description;
    private String imageURL;
    private String backdropURL;
    private String rating;
    private String viewer;
    private String language;


    public Movie(String title, String release, String description, String imageURL, String backdropURL, String rating, String viewer, String language) {
        this.title = title;
        this.release = release;
        this.description = description;
        this.imageURL = imageURL;
        this.backdropURL = backdropURL;
        this.rating = rating;
        this.viewer = viewer;
        this.language = language;
    }



    protected Movie(Parcel in) {
        title = in.readString();
        release = in.readString();
        description = in.readString();
        imageURL = in.readString();
        backdropURL = in.readString();
        rating = in.readString();
        viewer = in.readString();
        language = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getBackdropURL() {
        return backdropURL;
    }

    public void setBackdropURL(String backdropURL) {
        this.backdropURL = backdropURL;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getViewer() {
        return viewer;
    }

    public void setViewer(String rating) {
        this.viewer = viewer;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(release);
        dest.writeString(description);
        dest.writeString(imageURL);
        dest.writeString(backdropURL);
        dest.writeString(rating);
        dest.writeString(viewer);
        dest.writeString(language);
    }
}

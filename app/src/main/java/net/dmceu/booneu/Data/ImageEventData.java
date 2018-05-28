package net.dmceu.booneu.Data;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageEventData implements Parcelable {

    private String r4_id;
    private String country_id;
    private String country_name_en;
    private String center_id;
    private String center_name_en;
    private String event_id;
    private String event_name;
    private String media_url;

    public String getR4_id() {
        return r4_id;
    }

    public String getCountry_id() {
        return country_id;
    }

    public String getCountry_name_en() {
        return country_name_en;
    }

    public String getCenter_id() {
        return center_id;
    }

    public String getCenter_name_en() {
        return center_name_en;
    }

    public String getEvent_id() {
        return event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getMedia_url() {
        return media_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.r4_id);
        parcel.writeString(this.country_id);
        parcel.writeString(this.country_name_en);
        parcel.writeString(this.center_id);
        parcel.writeString(this.center_name_en);
        parcel.writeString(this.event_id);
        parcel.writeString(this.event_name);
        parcel.writeString(this.media_url);
    }

    public ImageEventData() {
    }

    protected ImageEventData(Parcel in) {
        this.r4_id = in.readString();
        this.country_id = in.readString();
        this.country_name_en = in.readString();
        this.center_id = in.readString();
        this.center_name_en = in.readString();
        this.event_id = in.readString();
        this.event_name = in.readString();
        this.media_url = in.readString();
    }

    public static final Parcelable.Creator<ImageEventData> CREATOR = new Parcelable.Creator<ImageEventData>() {
        @Override
        public ImageEventData createFromParcel(Parcel source) {
            return new ImageEventData(source);
        }

        @Override
        public ImageEventData[] newArray(int size) {
            return new ImageEventData[size];
        }
    };

}

package network.dhammakaya.booneu3.Data;

import android.os.Parcel;
import android.os.Parcelable;

public class EventData implements Parcelable {

    private String event_name;
    private String center_name_en;
    private String time_start;
    private String time_stop;
    private String image_url;

    public static final String BASE_URL = "http://booncalendar.dhammakaya.network/connect/";

    public String getEvent_name() {
        return event_name;
    }

    public String getCenter_name_en() { return center_name_en; }

    public String getTime_start() {
        return time_start;
    }

    public String getTime_stop() {
        return time_stop;
    }

    public String getImage_url() { return image_url; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.event_name);
        parcel.writeString(this.center_name_en);
        parcel.writeString(this.time_start);
        parcel.writeString(this.time_stop);
        parcel.writeString(this.image_url);
    }

    public EventData() {
    }

    protected EventData(Parcel in) {
        this.event_name = in.readString();
        this.center_name_en = in.readString();
        this.time_start = in.readString();
        this.time_stop = in.readString();
        this.image_url = in.readString();
    }

    public static final Parcelable.Creator<EventData> CREATOR = new Parcelable.Creator<EventData>() {
        @Override
        public EventData createFromParcel(Parcel source) {
            return new EventData(source);
        }

        @Override
        public EventData[] newArray(int size) {
            return new EventData[size];
        }
    };
}

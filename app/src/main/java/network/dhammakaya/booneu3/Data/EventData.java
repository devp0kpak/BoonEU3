package network.dhammakaya.booneu3.Data;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarUtils;

import java.util.Calendar;

public class EventData implements Parcelable {

    private String event_name;
    private String country_id;
    private String center_id;
    private String event_id;
    private String country_name_en;
    private String center_name_en;
    private String calendar_date;
    private String time_start;
    private String time_stop;
    private String image_url;

    public static final String BASE_URL = "http://booncalendar.dhammakaya.network/connect/";

    public String getEvent_name() {
        return event_name;
    }

    public String getCountry_id() {
        return country_id;
    }

    public String getCenter_id() {
        return center_id;
    }

    public String getEvent_id() {
        return event_id;
    }

    public String getCountry_name_en() {
        return country_name_en;
    }

    public String getCenter_name_en() { return center_name_en; }

    public String getCalendar_date() {
        return calendar_date;
    }

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
        parcel.writeString(this.event_id);
        parcel.writeString(this.country_id);
        parcel.writeString(this.center_id);
        parcel.writeString(this.country_name_en);
        parcel.writeString(this.center_name_en);
        parcel.writeString(this.calendar_date);
        parcel.writeString(this.time_start);
        parcel.writeString(this.time_stop);
        parcel.writeString(this.image_url);
    }

    public EventData(String event_name, String country_id, String center_id, String event_id, String country_name_en, String center_name_en, String calendar_date, String time_start, String time_stop, String image_url) {
        this.event_name = event_name;
        this.country_id = country_id;
        this.center_id = center_id;
        this.event_id = event_id;
        this.country_name_en = country_name_en;
        this.center_name_en = center_name_en;
        this.calendar_date = calendar_date;
        this.time_start = time_start;
        this.time_stop = time_stop;
        this.image_url = image_url;
    }

    protected EventData(Parcel in) {
        this.event_name = in.readString();
        this.event_id = in.readString();
        this.country_id = in.readString();
        this.center_id = in.readString();
        this.country_name_en = in.readString();
        this.center_name_en = in.readString();
        this.calendar_date = in.readString();
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

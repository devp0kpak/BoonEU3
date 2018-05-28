package net.dmceu.booneu.Data;

import android.os.Parcel;
import android.os.Parcelable;

public class UserData implements Parcelable{

    private String user_id;
    private String user_code;
    private String user_displayname;

    public String getUser_id() {
        return user_id;
    }

    public String getUser_code() {
        return user_code;
    }

    public String getUser_displayname() {
        return user_displayname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.user_id);
        parcel.writeString(this.user_code);
        parcel.writeString(this.user_displayname);
    }

    public UserData() {

    }

    protected UserData(Parcel in) {
        this.user_id = in.readString();
        this.user_code = in.readString();
        this.user_displayname = in.readString();
    }

    public static final Parcelable.Creator<UserData> CREATOR = new Parcelable.Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel source) {
            return new UserData(source);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };
}

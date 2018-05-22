package network.dhammakaya.booneu3.Data;

import android.os.Parcel;
import android.os.Parcelable;

public class ContactData implements Parcelable {

    private String country_name_en;
    private String center_name_en;
    private String contact_group;
    private String contact_data;

    public String getCountry_name_en() {
        return country_name_en;
    }

    public String getCenter_name_en() {
        return center_name_en;
    }

    public String getContact_group() {
        return contact_group;
    }

    public String getContact_data() {
        return contact_data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.country_name_en);
        parcel.writeString(this.center_name_en);
        parcel.writeString(this.contact_group);
        parcel.writeString(this.contact_data);
    }

    public ContactData() {
    }

    protected ContactData(Parcel in) {
        this.country_name_en = in.readString();
        this.center_name_en = in.readString();
        this.contact_group = in.readString();
        this.contact_data = in.readString();
    }

    public static final Parcelable.Creator<ContactData> CREATOR = new Parcelable.Creator<ContactData>() {
        @Override
        public ContactData createFromParcel(Parcel source) {
            return new ContactData(source);
        }

        @Override
        public ContactData[] newArray(int size) {
            return new ContactData[size];
        }
    };
}

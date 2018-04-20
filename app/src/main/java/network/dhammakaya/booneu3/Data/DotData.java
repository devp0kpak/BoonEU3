package network.dhammakaya.booneu3.Data;

public class DotData {

    private String r1_id;
    private String country_name_en;
    private String calendar_date;

    public DotData(String r1_id, String country_name_en, String calendar_date) {
        this.r1_id = r1_id;
        this.country_name_en = country_name_en;
        this.calendar_date = calendar_date;
    }

    public String getR1_id() {
        return r1_id;
    }

    public String getCountry_name_en() {
        return country_name_en;
    }

    public String getCalendar_date() {
        return calendar_date;
    }
}

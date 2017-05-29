package prefs;

import android.text.format.Time;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by TheSassyGourmet on 5/28/2017.
 */

public class Meeting {

    private Date date;
    private String from;
    private String to;
    private String objective;

    public Meeting() {
        super();
    }

    public Meeting(Date date, String from, String to, String objective) {
        super();
        this.date = date;
        this.from = from;
        this.to = to;
        this.objective = objective;
    }
    public Date getMeetingDate() {
        return date;
    }

    public void setMeetingDate(Date date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setObjective(String description) {
        this.objective = description;
    }

    public String getObjective() {
        return objective;
    }


    @Override
    public String toString() {
        return "Meeting [date=" + date + ", from=" + from + ", to="
                + to + ", objective=" + objective + "]";
    }
}

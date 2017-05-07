package Models;

/**
 * Created by Bittu on 15-04-2017.
 */

public class ReminderRecord {

    private String Name;
    private String Date;
    private String time;
    private int Priority;


    public ReminderRecord(String name,String date,String time,int priority){
        this.Name=name;
        this.Date=date;
        this.Priority=priority;
    }

    public ReminderRecord(){

    }



    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPriority() {
        return Priority;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }
}

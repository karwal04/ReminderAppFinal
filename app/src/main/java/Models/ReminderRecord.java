package Models;

/**
 * Created by Bittu on 15-04-2017.
 */

public class ReminderRecord {
    private  int _id;
    private String Name;
    private String Date;
    private boolean vibrate;
    private int Priority;


    public ReminderRecord(int id,String name,String date,boolean vibrate,int priority){
        this._id=id;
        this.Name=name;
        this.Date=date;
        this.vibrate=vibrate;
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

    public int getPriority() {
        return Priority;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public boolean isVibrate() {
        return vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
    }
}

package rtrk.pnrs1.ra198_2013.taskmanager;

import android.widget.Button;
import android.widget.ImageView;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

public class CharacterTask {
    public String tName;
    public String tDiscription;
    public boolean tDone;
    public int tReminde;
    public int tPriority;
    public int tTime;
    public String tDate;

    public CharacterTask(String  name, String date, int priority, int reminde){
        tName = name;
        tDate = date;
        tPriority = priority;
        tReminde = reminde;
    }

    public String getName(){
        return tName;
    }

    public void setName(String name){
        this.tName = name;
    }

    public String getDate(){
        return tDate;
    }

    public void setDate(String date){
        this.tDate = date;
    }

    public int getTime(){
        return tTime;
    }

    public void setTime(int time){
        this.tTime = time;
    }

    public String getDisc(){
        return tDiscription;
    }

    public void setDisc(String discription){
        this.tDiscription = discription;
    }

    public int getReminde(){
        return tReminde;
    }

    public void setReminde(int reminde){
        this.tReminde = reminde;
    }

    public int getPriority(){
        return tPriority;
    }

    public void setPriority(int priority){
        this.tPriority = priority;
    }

    public boolean getDone(){
        return tDone;
    }

    public void setDone(boolean done){
        this.tDone = done;
    }
}

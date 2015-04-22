package com.example.adriangracia.studybuddy.objects;

import java.io.Serializable;

/**
 * Created by jonathanmitchell on 4/20/15.
 */
public class TimeObject implements Serializable{

    private int hour;
    private int minute;

    private int hourNonMil;

    private boolean isAM = false;

    public TimeObject(Integer hour, Integer minute){
        this.hour=hour;
        this.minute=minute;

        if(hour < 12){
            isAM=true;
            hourNonMil = hour;
        } else{
            if(hour==12)
                hourNonMil=hour;
            else
            hourNonMil=hour-12;
        }

        if(isAM && hour==0){
            hourNonMil=12;
        }

    }


    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHourNonMil() {
        return hourNonMil;
    }

    public void setHourNonMil(int hourNonMil) {
        this.hourNonMil = hourNonMil;
    }

    public boolean isAM() {
        return isAM;
    }

    public void setAM(boolean isAM) {
        this.isAM = isAM;
    }

    public String toString(){
        String amOrPm;
        if(isAM){
            amOrPm="AM";
        } else {
            amOrPm="PM";
        }

        String minDigits;
        if(minute<10){
            minDigits = "0"+minute;
        } else {
            minDigits = minute+"";
        }





        return hourNonMil+":"+minDigits+" "+amOrPm;
    }
}

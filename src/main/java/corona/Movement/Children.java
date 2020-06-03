package corona.Movement;


import Movement.*;
import java.util.List;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Oliver
 */
public class Children extends Person {

    private final int twoWeeks = 14;
    private final int ageGroup = 0;
    private Integer ID;
    private int age;
    private Character gender;
    private String job;
    private int educationLevel;
    private double immunity, infectionRate;
    private Event[] myEvent;

    public Children(Integer ID, int age, Character gender) {
        super(ID, age, gender);
        this.ID = ID;
        this.age = age;
        this.gender = gender;
        myEvent = new Event[twoWeeks];
    }

    public void generateJob() throws Exception {
        if (age < 0) {
            throw new Exception("Invalid age");
        }
        if (age <= 5) {
            job = "Kid";
        } else if (age < 12) {
            job = "Pupil";
        } else {
            job = "Student";
        }
    }

    public void setEducationLevel() {
        Random r = new Random();
        if (age <= 15) {
            educationLevel = r.nextInt(5) + 1;
        } else {
            educationLevel = r.nextInt(10) + 5;
        }
    }

    public void setImmunity() {
        Random r = new Random();
        immunity = 1 - r.nextInt(31) / 100;
    }

    public void setInfectionRate() throws Exception {
        if (infectionRate < 0) {
            throw new Exception("Invalid infection rate");
        }
        infectionRate = 1 - (immunity + educationLevel / 500);
    }

    public Integer getID() {
        return ID;
    }

    public int getAge() {
        return age;
    }

    public Character getGender() {
        return gender;
    }

    public String getJob() {
        return job;
    }

    public int getEducationLevel() {
        return educationLevel;
    }

    public double getImmunity() {
        return immunity;
    }

    public int[] getEvent(int day_of_month) throws Exception {
        if (day_of_month >= 14 || day_of_month < 1) {
            throw new Exception("Invalid day_of_month");
        }
        return myEvent[day_of_month - 1].getEvent();
    }

    public String getEventToString() throws Exception {
        String str="";
       for(int i=0;i<myEvent.length;i++){
       str+=myEvent[i].showEventStr()+"\n";
       } 
       return str;
    }

    public void setEvent() {
        int day = 0, month = 2, year = 2020;

        for (int i = 0; i < myEvent.length; i++) {
            myEvent[i] = new Event(++day, month, year);
            myEvent[i].setEvent(ageGroup);
        }
    }

    public void showEventByDate(int day_of_month) throws Exception {
        if (day_of_month >= 14 || day_of_month < 1) {
            throw new Exception("Invalid day_of_month");
        }
        myEvent[day_of_month - 1].showEvent();
    }

    public void showEventAll() throws Exception {
        for (int i = 0; i < myEvent.length; i++) {
            myEvent[i].showEvent();
        }
    }
}

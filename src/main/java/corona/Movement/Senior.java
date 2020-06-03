package corona.Movement;


import Movement.*;
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
public class Senior extends Person {

    private final int ageGroup = 2;
    private final int twoWeeks = 14;
    private Integer ID;
    private int age;
    private Character gender;
    private String job;
    private int educationLevel;
    private double immunity, infectionRate;
    private Event[] myEvent;

    public Senior(Integer ID, int age, Character gender) {
        super(ID, age, gender);
        this.ID = ID;
        this.age = age;
        this.gender = gender;
        myEvent = new Event[twoWeeks];
    }

    public void generateJob() {
        Random r = new Random();
        if (age >= 70) {
            job = "Retired";
        } else {
            switch (r.nextInt(5)) {
                case 0:
                    job = "Retired";
                    break;
                case 1:
                    job = "Professor";
                    break;
                case 2:
                    job = "Self-employed";
                    break;
                case 3:
                    job = "Retired";
                    break;
                case 4:
                    job = "Retired";
                    break;
            }
        }
    }

    public void setEducationLevel() throws Exception {
        Random r = new Random();
        if (job == null) {
            throw new Exception("Define the job first");
        }
        if (job.compareTo("Retired") == 0
                || job.compareTo("Self-employed") == 0) {
            educationLevel = 8 + r.nextInt(10);
        } else {
            educationLevel = 10 + r.nextInt(10);
        }
    }

    public void setInfectionRate() throws Exception {
        if (infectionRate < 0) {
            throw new Exception("Invalid infection rate");
        }
        infectionRate = 1 - (immunity + educationLevel / 600);

    }

    public void setImmunity() {
        Random r = new Random();
        immunity = 1 - (10 + r.nextInt(60)) / 100;
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
        String str = "";
        for (int i = 0; i < myEvent.length; i++) {
            str += myEvent[i].showEventStr() + "\n";
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

    public void showEvent(int day_of_month) throws Exception {
        if (day_of_month >= 14 || day_of_month < 1) {
            throw new Exception("Invalid day_of_month");
        }
        myEvent[day_of_month - 1].showEvent();
    }
}

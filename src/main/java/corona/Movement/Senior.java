package corona.Movement;

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

    private static int ageGroup = 2;
    private final int twoWeeks = 14;
    private Integer ID;
    private int age;
    private Character gender;
    private String job;
    private int educationLevel;
    private double immunity;
    private double infectionRate;
    private Event[] myEvent;
    private int jobCase, personScore;

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
            jobCase = r.nextInt(2) == 0 ? 0 : 3;
            job =jobCase==0? "Retired Employee":"Retired Worker";
        } else {
            jobCase = r.nextInt(5);
            switch (jobCase) {
                case 0:
                    job = "Retired Employee";
                    break;
                case 1:
                    job = "Professor";
                    break;
                case 2:
                    job = "Self-employed";
                    break;
                case 3:
                    job = "Retired Worker";
                    break;
                case 4:
                    job = "Retail Owner";
                    break;
            }
        }
    }

    public void setEducationLevel() throws Exception {
        Random r = new Random();
        if (job == null) {
            throw new Exception("Define the job first");
        }
        switch (jobCase) {
            case 0:
                educationLevel = 9;
                break;
            case 1:
                educationLevel = 18;
                break;
            case 2:
                educationLevel = 8;
                break;
            case 3:
                educationLevel = 6;
                break;
            case 4:
                educationLevel = 10;
                break;
        }
    }

    public void setPersonScore() throws Exception {
        Random r = new Random();
        if (job == null) {
            throw new Exception("Define the job first");
        }
        switch (jobCase) {
            case 0:
                personScore = 6;
                break;
            case 1:
                personScore = 3;
                break;
            case 2:
                personScore = 7;
                break;
            case 3:
                personScore = 6;
                break;
            case 4:
                personScore = 9;
                break;
        }
    }

    public void setInfectionRate() throws Exception {
        if (personScore < 0||personScore>10||educationLevel<0||educationLevel>20) {
            throw new Exception("Invalid infection rate");
        }
        infectionRate = (1 - (immunity +((double)getEducationLevel()) / 500))*(1+((double)getPersonScore())/50);
        
    }

    public void setImmunity() {
        Random r = new Random();
        int randomRate=r.nextInt(60);
        immunity = (1 - (10 + (double)randomRate) / 100);
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

    public double getInfectionRate() {
        return infectionRate;
    }

    public int getPersonScore() {
        return personScore;
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
            myEvent[i].setEvent(ageGroup,jobCase);
        }
    }

    public void showEvent(int day_of_month) throws Exception {
        if (day_of_month >= 14 || day_of_month < 1) {
            throw new Exception("Invalid day_of_month");
        }
        myEvent[day_of_month - 1].showEvent();
    }
}

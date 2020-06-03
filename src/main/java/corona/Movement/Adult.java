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
public class Adult extends Person {

    private final int twoWeeks = 14;
    private Integer ID;
    private int age;
    private Character gender;
    private String job;
    private int educationLevel;
    private double immunity;
    private double infectionRate;
    private Event[] myEvent;
    private static int ageGroup = 1;

    public Adult(Integer ID, int age, Character gender) {
        super(ID, age, gender);
        this.ID = ID;
        this.age = age;
        this.gender = gender;
        myEvent = new Event[twoWeeks];
    }

    public void generateJob() {
        Random r = new Random();
        switch (r.nextInt(15)) {
            case 0:
                job = "Doctor";
                break;
            case 1:
                job = "Nurse";
                break;
            case 2:
                job = "Officer";
                break;
            case 3:
                job = "Crew Attenant";
                break;
            case 4:
                job = "Secretary";
                break;
            case 5:
                job = "Plumber";
                break;
            case 6:
                job = "Teacher";
                break;
            case 7:
                job = "Student";
                break;
            case 8:
                job = "Shopkeeper";
                break;
            case 9:
                job = "Bank Clark";
                break;
            case 10:
                job = "Waiter";
                break;
            case 11:
                job = "Chef";
                break;
            case 12:
                job = "Staff";
                break;
            case 13:
                job = "Engineer";
                break;
            case 14:
                job = "Worker";
                break;
        }
    }

    public void setEducationLevel() throws Exception {
        Random r = new Random();
        if (job == null) {
            throw new Exception("Define the job first");
        }
        if (job.compareTo("Plumber") == 0 || job.compareTo("Worker") == 0
                || job.compareTo("Chef") == 0 || job.compareTo("Waiter") == 0
                || job.compareTo("Student") == 0 || job.compareTo("Shopkeeper") == 0
                || job.compareTo("Staff") == 0 || job.compareTo("Crew Attendant") == 0) {
            educationLevel = r.nextInt(10) + 10;
        } else {
            educationLevel = r.nextInt(5) + 15;
        }
    }

    public void setImmunity() {
        Random r = new Random();
        immunity = 1 - (5 + r.nextInt(46)) / 100;
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

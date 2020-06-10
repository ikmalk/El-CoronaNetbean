package corona.Movement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import test.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Oliver
 */
public class RandomizeInitiation {

    private Map map;
    private final int numOfCitizens = 1000;
    Children[] child;
    Adult[] adult;
    Senior[] senior;
    private int numOfChildren, numOfAdult, numOfSenior;
    private int count = 1000;

    public RandomizeInitiation() throws Exception {
        Random r = new Random();
        numOfAdult = 450 + r.nextInt(100);
        numOfSenior = 250 + r.nextInt(100);
        numOfChildren = numOfCitizens - numOfAdult - numOfSenior;

        child = new Children[numOfChildren];
        adult = new Adult[numOfAdult];
        senior = new Senior[numOfSenior];

    }

    public void initiate() throws Exception {
        for (int i = 0; i < numOfChildren; i++) {
            ++count;
            child[i] = new Children(count, getAge(0), getGender());
            child[i].generateJob();
            child[i].setEducationLevel();
            child[i].setMap(getMap());
            child[i].setEvent();
            child[i].setImmunity();
            child[i].setPersonScore();
            child[i].setInfectionRate();
        }
        for (int i = 0; i < numOfAdult; i++) {
            ++count;
            adult[i] = new Adult(count, getAge(1), getGender());
            adult[i].generateJob();
            adult[i].setEducationLevel();
            adult[i].setMap(getMap());
            adult[i].setEvent();
            adult[i].setImmunity();
            adult[i].setPersonScore();
            adult[i].setInfectionRate();
        }

        for (int i = 0; i < numOfSenior; i++) {
            ++count;
            senior[i] = new Senior(count, getAge(2), getGender());
            senior[i].generateJob();
            senior[i].setEducationLevel();
            senior[i].setMap(getMap());
            senior[i].setEvent();
            senior[i].setImmunity();
            senior[i].setPersonScore();
            senior[i].setInfectionRate();
        }
    }

    public int getAge(int i) throws Exception {
        Random r = new Random();
        int age = -1;
        switch (i) {
            case 0://for children
                age = r.nextInt(18);
                break;
            case 1://for adult
                age = 18 + r.nextInt(43);
                break;
            case 2://for senior
                age = 60 + r.nextInt(25);
                break;
        }
        if (age == -1) {
            throw new Exception("Invalid age generation");
        }
        return age;
    }

    public Character getGender() {
        Random r = new Random();

        Character gender = r.nextInt(2) == 0 ? 'M' : 'F';
        return gender;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void writeLogFile() throws IOException, Exception {

        FileWriter myWriter1 = new FileWriter("child.txt");
        FileWriter myWriter2 = new FileWriter("adult.txt");
        FileWriter myWriter3 = new FileWriter("senior.txt");

        if (child != null) {
            for (int i = 0; i < this.child.length; i++) {
                myWriter1.write("\nID: " + child[i].getID() + " gender: " + child[i].getGender() + " age: "
                        + child[i].getAge() + " job: " + child[i].getJob() + " infection rate: " + child[i].getInfectionRate());
                myWriter1.write(child[i].getEventToString());
            }
        }
        if (adult != null) {
            for (int i = 0; i < this.adult.length; i++) {
                myWriter2.write("\nID: " + adult[i].getID() + " gender: " + adult[i].getGender() + " age: "
                        + adult[i].getAge() + " job: " + adult[i].getJob() + " infection rate: " + adult[i].getInfectionRate());
                myWriter2.write(adult[i].getEventToString());
            }
        }
        if (senior != null) {
            for (int i = 0; i < this.senior.length; i++) {
                myWriter3.write("\nID: " + senior[i].getID() + " gender: " + senior[i].getGender() + " age: "
                        + senior[i].getAge() + " job: " + senior[i].getJob() + " infection rate: " + senior[i].getInfectionRate());
                myWriter3.write(senior[i].getEventToString());
            }
        }
    }
}

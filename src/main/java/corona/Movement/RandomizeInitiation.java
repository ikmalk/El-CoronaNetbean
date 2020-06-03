package corona.Movement;


import Movement.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

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

        initiate();
    }

    public void initiate() throws Exception {
        for (int i = 0; i < numOfChildren; i++) {
            ++count;
            child[i] = new Children(count, getAge(0), getGender());
            child[i].generateJob();
            child[i].setEducationLevel();
            child[i].setEvent();
            child[i].setImmunity();
            child[i].setInfectionRate();
        }
        for (int i = 0; i < numOfAdult; i++) {
            ++count;
            adult[i] = new Adult(count, getAge(1), getGender());
            adult[i].generateJob();
            adult[i].setEducationLevel();
            adult[i].setEvent();
            adult[i].setImmunity();
            adult[i].setInfectionRate();
        }

        for (int i = 0; i < numOfSenior; i++) {
            ++count;
            senior[i] = new Senior(count, getAge(2), getGender());
            senior[i].generateJob();
            senior[i].setEvent();
            senior[i].setEducationLevel();
            senior[i].setImmunity();
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
                age = 60 + r.nextInt(30);
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

    public void writeLogFile() throws IOException, Exception {

        FileWriter myWriter1 = new FileWriter("child.txt");
        FileWriter myWriter2 = new FileWriter("adult");
        FileWriter myWriter3 = new FileWriter("senior");

        if (child != null) {
            for (int i = 0; i < this.child.length; i++) {
                myWriter1.write("ID: " + child[i].getID() );
                myWriter1.write(child[i].getEventToString());
            }
        }
        if (adult != null) {
            for (int i = 0; i < this.adult.length; i++) {
                myWriter2.write("ID: " + adult[i].getID() );
                myWriter2.write(adult[i].getEventToString());
            }
        }
        if (senior != null) {
            for (int i = 0; i < this.senior.length; i++) {
                myWriter2.write("ID: " + senior[i].getID() );
                myWriter2.write(senior[i].getEventToString());
            }
        }
    }
}

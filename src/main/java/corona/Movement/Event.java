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
public class Event {

    private static final int GO_HOME = 0;
    private static final int GO_TO_SCHOOL = 1;
    private static final int GO_TO_OFFICE = 2;
    private static final int GO_TO_HOSPITAL = 3;
    private static final int GO_TO_RESTUARANT = 4;
    private static final int GO_TO_BANK = 5;
    private static final int GO_TO_SUPERMARKET = 6;
    private static final int GO_SHOPPING = 7;
    private static final int GO_TO_AIRPORT = 8;
    private static final int GO_TO_PARK = 9;
    private static final int GO_TO_GYM = 10;
    private static final int SIZE = 3;
    private int day_of_month, month, year;
    private int[] event;

    public Event(int day_of_month, int month, int year) {
        this.day_of_month = day_of_month;
        this.month = month;
        this.year = year;
        event = new int[SIZE];
    }

    public int getDay_Of_Month() {
        return day_of_month;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setEvent(int ageGroup) {
        Random r = new Random();
        switch (ageGroup) {
            case 0:
                if (r.nextInt(2) == 0) {
                    event[0] = 1;
                    event[1] = r.nextInt(8) + 2;
                    event[2] = 0;
                } else {
                    event[1] = 1;
                    event[0] = r.nextInt(8) + 2;
                    event[2] = 0;
                }
                break;
            case 1:
                for (int i = 0; i < event.length - 1; i++) {
                    event[i] = r.nextInt(10) + 1;
                }
                event[SIZE - 1] = 0;
                break;
            case 2:
                int rand = r.nextInt(3);
                event[rand] = 6;
                for (int i = 0; i < event.length; i++) {
                    if (i != rand) {
                        event[i] = r.nextInt(11);
                    }
                }
                break;
        }
    }

    public int[] getEvent() {
        return event;
    }

    public void showEvent() throws Exception {
        if (event == null) {
            throw new Exception("Null Event Array");
        }
        String str;
        for (int i = 0; i < event.length; i++) {

            System.out.print(switchCaseGetStr(event[i]) + "--> ");
        }
        System.out.println("");
    }

    public String showEventStr() throws Exception {
        if (event == null) {
            throw new Exception("Null Event Array");
        }
        String str = "";
        for (int i = 0; i < event.length; i++) {

            str += "\n" + switchCaseGetStr(event[i]) + "--> ";
        }
        return str;
    }

    public String switchCaseGetStr(int ind) {
        String str = "";
        switch (ind) {
            case 0:
                str = "GO_HOME";
                break;
            case 1:
                str = "GO_TO_SCHOOL";
                break;
            case 2:
                str = "GO_TO_OFFICE";
                break;
            case 3:
                str = "GO_TO_HOSPITAL";
                break;
            case 4:
                str = "GO_TO_RESTUARANT";
                break;
            case 5:
                str = "GO_TO_BANK";
                break;
            case 6:
                str = "GO_TO_SUPERMARKET";
                break;
            case 7:
                str = "GO_SHOPPING";
                break;
            case 8:
                str = "GO_TO_AIRPORT";
                break;
            case 9:
                str = "GO_TO_PARK";
                break;
            case 10:
                str = "GO_TO_GYM";
                break;

        }
        return str;
    }
}

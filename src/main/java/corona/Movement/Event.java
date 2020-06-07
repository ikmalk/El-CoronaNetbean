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
public class Event {

    private static final int GO_HOME = 0;
    private static final int GO_TO_SCHOOL = 1;
    private static final int GO_TO_OFFICE = 2;
    private static final int GO_TO_HOSPITAL = 3;
    private static final int GO_TO_RESTUARANT = 4;
    private static final int GO_TO_BANK = 5;
    private static final int GO_TO_SUPERMARKET = 6;
    private static final int GO_TO_SHOPPING_MALL = 7;
    private static final int GO_TO_AIRPORT = 8;
    private static final int GO_TO_PARK = 9;
    private static final int GO_TO_POLICE_STATION = 10;
    private static final int GO_TO_GOVERNMENT_OFFICE = 11;
    private static final int GO_TO_GYM = 12;
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

    public void setEvent(int ageGroup, int jobCase) {
        Random r = new Random();
        switch (ageGroup) {
            case 0://child
                if (jobCase == 0) {//kid
                    for (int i = 0; i < event.length; i++) {
                        int randomNum = r.nextInt(5);
                        switch (randomNum) {
                            case 0:
                                event[i] = 0;
                                break;
                            case 1:
                                event[i] = 1;
                                break;
                            case 2:
                                event[i] = 3;
                                break;
                            case 3:
                                event[i] = 4;
                                break;
                            case 4:
                                event[i] = 9;
                                break;
                        }
                    }
                } else if (jobCase == 1) {//pupil
                    if (r.nextInt(2) == 0) {
                        event[0] = 1;
                        event[1] = r.nextInt(8) + 2;
                        event[2] = 0;
                    } else {
                        event[1] = 1;
                        event[0] = r.nextInt(8) + 2;
                        event[2] = 0;
                    }
                } else {//teen student
                    if (r.nextInt(2) == 0) {
                        event[0] = 1;
                        event[1] = r.nextInt(10) + 2;
                        event[2] = 0;
                    } else {
                        event[1] = 1;
                        event[0] = r.nextInt(11) + 2;
                        event[2] = 0;
                    }
                }
                break;
            case 1://adult
                switch (jobCase) {
                    case 0://doctor
                    case 1://nurse
                        if (r.nextInt(2) == 0) {//day shift
                            event[0] = 3;
                            for (int i = 1; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = r.nextInt(2) == 0 ? 0 : r.nextInt(12) + 1;
                        } else {//night shift
                            event[0] = 0;
                            for (int i = 1; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = 3;
                        }
                        break;
                    case 2://police
                        if (r.nextInt(2) == 0) {//day shift
                            event[0] = 10;
                            for (int i = 1; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = r.nextInt(2) == 0 ? 0 : 12;
                        } else {//night shift
                            event[0] = 0;
                            for (int i = 1; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = 10;
                        }
                        break;
                    case 3://crew attendant
                    case 19://pilot
                        if (r.nextInt(2) == 0) {//day shift
                            event[0] = 8;
                            for (int i = 1; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = r.nextInt(2) == 0 ? 0 : r.nextInt(12) + 1;
                        } else {//night shift
                            event[0] = 0;
                            for (int i = 1; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = 8;
                        }
                        break;
                    case 4://secretary
                    case 12://staff
                        event[0] = 2;
                        for (int i = 1; i < event.length - 1; i++) {
                            event[i] = r.nextInt(12) + 1;
                        }
                        event[SIZE - 1] = 0;
                        break;
                    case 5://plumber
                        int ran = r.nextInt(3);
                        if (ran == 0) {//day shift
                            event[0] = r.nextInt(2) == 0 ? 2 : 6;
                            for (int i = 1; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = 0;
                        } else if (ran == 1) {//evening shift
                            for (int i = 0; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = r.nextInt(2) == 0 ? 0 : 5;
                        } else {//night shift
                            event[0] = r.nextInt(2) == 0 ? 0 : r.nextInt(12) + 1;
                            for (int i = 1; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = r.nextInt(2) == 0 ? 4 : 5;
                        }
                        break;
                    case 6://teacher
                    case 7://college student
                        event[0] = 1;
                        for (int i = 1; i < event.length - 1; i++) {
                            event[i] = r.nextInt(12) + 1;
                        }
                        event[SIZE - 1] = 0;
                        break;
                    case 8://shopkeeper
                        if (r.nextInt(2) == 0) {//day shift
                            event[0] = 7;
                            for (int i = 1; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = r.nextInt(2) == 0 ? 0 : r.nextInt(12) + 1;
                        } else {//night shift
                            event[0] = 0;
                            for (int i = 1; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = 7;
                        }
                        break;
                    case 9://bank clark
                        event[0] = 5;
                        for (int i = 1; i < event.length - 1; i++) {
                            event[i] = r.nextInt(12) + 1;
                        }
                        event[SIZE - 1] = 0;
                        break;
                    case 10://waiter
                    case 11://chef
                        if (r.nextInt(2) == 0) {//day shift
                            event[0] = 4;
                            for (int i = 1; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = r.nextInt(2) == 0 ? 0 : r.nextInt(12) + 1;
                        } else {//night shift
                            event[0] = 0;
                            for (int i = 1; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = r.nextInt(2) == 0 ? 4 : 6;
                        }
                        break;
                    case 13://engineer
                        event[0] = r.nextInt(2) == 0 ? 2 : 1;
                        for (int i = 1; i < event.length - 1; i++) {
                            event[i] = r.nextInt(12) + 1;
                        }
                        event[SIZE - 1] = 0;
                        break;
                    case 14:
                        int ran1 = r.nextInt(3);
                        if (ran1 == 0) {//day shift
                            event[0] = 3;
                            for (int i = 1; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = r.nextInt(2) == 0 ? 0 : r.nextInt(12) + 1;
                        } else if (ran1 == 1) {//evening
                            for (int i = 0; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = 0;
                        } else {//night shift
                            event[0] = r.nextInt(2) == 0 ? 0 : r.nextInt(12) + 1;
                            for (int i = 1; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = 3;
                        }
                        break;
                    case 15://businessman
                        if (r.nextInt(2) == 0) {//day shift
                            event[0] = 5;
                            for (int i = 1; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = r.nextInt(2) == 0 ? 0 : r.nextInt(12) + 1;
                        } else {//night shift
                            event[0] = r.nextInt(2) == 0 ? 0 : r.nextInt(12) + 1;
                            for (int i = 1; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = r.nextInt(2) == 0 ? 8 : 11;
                        }
                        break;
                    case 18://courier
                    case 17://bus driver
                        int ran2 = r.nextInt(3);
                        if (ran2 == 0) {//day shift
                            event[0] = r.nextInt(2) == 0 ? 1 : 7;
                            for (int i = 1; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = r.nextInt(2) == 0 ? 0 : r.nextInt(12) + 1;
                        } else if (ran2 == 1) {//noon shift
                            for (int i = 0; i < event.length; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                        } else {//night shift
                            event[0] = r.nextInt(2) == 0 ? 0 : r.nextInt(12) + 1;
                            for (int i = 1; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = r.nextInt(2) == 1 ? 2 : 9;
                        }
                        break;
                    case 16://guard
                        if (r.nextInt(2) == 0) {//day shift
                            event[0] = r.nextInt(2) == 0 ? 1 : 5;
                            for (int i = 1; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = r.nextInt(2) == 0 ? 0 : r.nextInt(12) + 1;
                        } else {//night shift
                            event[0] = r.nextInt(2) == 0 ? 0 : r.nextInt(12) + 1;
                            for (int i = 1; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = r.nextInt(2) == 0 ? 8 : 11;
                        }
                        break;
                    case 20://politician
                        event[0] = 11;
                        for (int i = 1; i < event.length - 1; i++) {
                            event[i] = r.nextInt(12) + 1;
                        }
                        event[SIZE - 1] = 0;
                        break;
                }
                break;
            case 2://senior
                switch (jobCase) {
                    case 0:
                    case 3:
                        int rand = r.nextInt(3);
                        event[rand] = r.nextInt(2) == 0 ? 6 : 9;
                        for (int i = 0; i < event.length; i++) {
                            if (i != rand) {
                                event[i] = r.nextInt(13);
                            }
                        }
                        break;
                    case 1://professor
                        event[0] = r.nextInt(2) == 0 ? 1 : 11;
                        for (int i = 1; i < event.length - 1; i++) {
                            event[i] = r.nextInt(12) + 1;
                        }
                        event[SIZE - 1] = 0;
                        break;
                    case 2://self-employed
                    case 4://retail owner
                        if (r.nextInt(2) == 0) {//day shift
                            event[0] = r.nextInt(2) == 0 ? 6 : 7;
                            for (int i = 1; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = r.nextInt(2) == 0 ? 0 : r.nextInt(12) + 1;
                        } else {//night shift
                            event[0] = 0;
                            for (int i = 1; i < event.length - 1; i++) {
                                event[i] = r.nextInt(12) + 1;
                            }
                            event[SIZE - 1] = r.nextInt(2) == 0 ? 5 : 11;
                        }
                        break;
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
                str = "GO_TO_SHOPPING_MALL";
                break;
            case 8:
                str = "GO_TO_AIRPORT";
                break;
            case 9:
                str = "GO_TO_PARK";
                break;
            case 10:
                str = "GO_TO_POLICE_STATION";
                break;
            case 11:
                str="GO_TO_GOVERNMENT_OFFICE";
                break;
            case 12:
                str="GO_TO_GYM";

        }
        return str;
    }
}

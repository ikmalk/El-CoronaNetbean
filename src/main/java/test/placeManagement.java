/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author farha
 */
public class placeManagement<T extends Comparable<T>> {
    
    private static final int SIZE = 14;
    private String nameofPlace;
    Visitors[] visitorByDate;
    
    public placeManagement(String nameofPlace) {
        this.nameofPlace = nameofPlace;
        visitorByDate = new Visitors[SIZE];
        for (int i = 0; i < SIZE; i++) {
            visitorByDate[i] = new Visitors();
        }
    }
    
    public String getNameofPlace() {
        return nameofPlace;
    }
    
    public void setNameofPlace(String nameofPlace) {
        this.nameofPlace = nameofPlace;
    }
    
    public void setVisitorByDate(int date, T visitorID) throws Exception {
        if (date < 0 || date >= SIZE) {
            throw new Exception("Date index out of bound ");
        }
        visitorByDate[date].addVisitor(visitorID);
    }
    
    public T getVisitorByDate(int date) throws Exception {
        if (date < 0 || date >= SIZE) {
            throw new Exception("Date index out of bound ");
        }
        return (T) visitorByDate[date].list;
    }
    
    public boolean containsVisitorByDate(int date, T visitorID) throws Exception {
        if (date < 0 || date >= SIZE) {
            throw new Exception("Date index out of bound ");
        }
        return visitorByDate[date].contains(visitorID);
    }
    
    public int getIndexByDate(int date, T visitorID) throws Exception {
        if (date < 0 || date >= SIZE) {
            throw new Exception("Date index out of bound ");
        }
        return visitorByDate[date].getIndex(visitorID);
    }

    public void displayVisitorsByDate(int date) throws Exception {
        if (date < 0 || date >= SIZE) {
            throw new Exception("Date index out of bound ");
        }
        for (int i = 0; i < visitorByDate[date].length(); i++) {
            System.out.print(visitorByDate[date].getVisitorID(i)+" -->");
        }
        System.out.println("");
    }

    @Override
    public String toString() {
        
        return nameofPlace;
    }
    
}
